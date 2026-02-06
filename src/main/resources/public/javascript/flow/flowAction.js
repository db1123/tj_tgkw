/**
 * 禁用编辑状态的节点点击事件
 * @param {当前节点} cell 
 * @param {绘图区域是否可编辑} draw_edit_op 
 */
var SaveSelectNodeTargetNodeColor = {}; // 选中节点影响的目标节点原始颜色
function onFlowDisableNodeClickAction(cell, draw_edit_op) {
  var noSelect = false; // 是否未选中节点
  var graph = mainEditorUi.editor.graph;
  var cells = graph.getModel()['cells'];
  if (cell) {
    if (cell['vertex'] && cell['vertex'] == 1
        && (
          !cell['classifyId'] // 普通节点
            ||
          (cell['classifyId']&&cell['classifyId']!='group'&&cell['classifyId']!='paramNote') // 特殊节点[业务节点、参数节点]
        )) { // 节点
      nowSelectNode = cell;
      // 显示选中节点图标
      if (draw_edit_op == 0) { // 禁用状态时启用节点标注
        graph.clearCellOverlays();
        var overlay = new mxCellOverlay(
          new mxImage('/plugins/mxgraph/images/overlays/check.png', 16, 16),
          '选中');
        graph.addCellOverlay(cell, overlay);
      }
      // 业务面板处理
      setTimeout(function(){
        if (control_logic_op == 1) {
          mainEditorUi.logic.Refresh(cell); // 业务属性面板刷新
        }
        if (control_adaptive_op == 1) {
          mainEditorUi.adaptiveDesign.Refresh(cell); // 自适应设计面板刷新
        }
      }, 1);
    } else { // 其他
      noSelect = true;
    }
  } else { // 点击绘图区
    noSelect = true;
  }
  if (noSelect) { // 未选中情况下的处理
    // 清空选择
    nowSelectNode = null;
    // 清空图标
    graph.clearCellOverlays();
    // 清空业务属性面板
    if (control_logic_op == 1) {
      mainEditorUi.logic.Clear();
    }
    // 自适应设计面板刷新
    if (control_adaptive_op == 1) {
      mainEditorUi.adaptiveDesign.Refresh(null);
    }
  } else { // 已选择情况下的处理
    // 还原上次节点颜色
    for (var id in SaveSelectNodeTargetNodeColor) {
      var bgColor = SaveSelectNodeTargetNodeColor[id];
      var elementCell = cells[id];
      if (elementCell) {
        // 染色
        setAdaptiveNodeColor(elementCell, bgColor);
      }
    }
    // 清空选中节点影响的目标节点原始颜色列表
    SaveSelectNodeTargetNodeColor = {};
    // 节点染色
    control_adaptive_op == 1 && setTimeout(function(){
      // 记录要变色的节点
      nowSelectNode['edges'] && nowSelectNode['edges'].forEach(edge => {
        // 以当前节点为源
        if (edge.source && edge.source.id == nowSelectNode.id) {
          if (edge.target) {
            // 记录目标节点颜色
            var style = edge.target.getStyle();
            var fillColorStr = style.match(/fillColor=(\S*);/);
            var bgColor = '';
            if (fillColorStr) { // 有背景色
              bgColor = fillColorStr[0].replace('fillColor=', '').replace(';', '');
            } else { // 无色
              bgColor = 'white'
            }
            SaveSelectNodeTargetNodeColor[edge.target.id] = bgColor;
            // 目标染色
            setAdaptiveNodeColor(edge.target, 'antiquewhite');
          }
        }
        // 以当前节点为目标
        if (edge.target && edge.target.id == nowSelectNode.id) {
          if (edge.source) {
            // 记录源节点颜色
            var style = edge.source.getStyle();
            var fillColorStr = style.match(/fillColor=(\S*);/);
            var bgColor = '';
            if (fillColorStr) { // 有背景色
              bgColor = fillColorStr[0].replace('fillColor=', '').replace(';', '');
            } else { // 无色
              bgColor = 'white'
            }
            SaveSelectNodeTargetNodeColor[edge.source.id] = bgColor;
            // 源染色
            setAdaptiveNodeColor(edge.source, 'antiquewhite');
          }
        }
      });
    }, 1);
  }
}