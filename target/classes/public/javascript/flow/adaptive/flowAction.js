/**
 * 流程图编辑器-自适应设计
 */
function EditorUiAdaptiveDesign() {
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  graph.setEnabled(false); // 禁止流程图编辑
  control_menu_op = 1; // 菜单面板
  control_toolbar_op = 0; // 工具栏面板 
  control_sidebar_op = 0; // 左侧图形面板
  control_outline_op = 0; // 缩略图面板
  control_format_op = 0; // 右侧样式面板
  control_logic_op = 0; // 右侧业务属性面板
  control_adaptive_op = 1; // 右侧自适应设计属性面板
  function_open_basic_op = 1; // 打开普通流程图
  function_open_adaptive_op = 1; // 打开自适应模型
  function_all_op = 1; // 所有功能是否可编辑
  function_draw_edit_op = 0; // 绘图区是否可编辑
  function_undo_redo_op = 0; // 撤销 恢复
  mainEditorUi.refresh();
}
/**
 * 按节点路径渲染有缘节点
 * @param {节点} cell 
 * @param {寻址方向} toType 1.source 2.target
 */
var AdaptiveRelationshipSelectNodes = {}; // 所有选中的显示关系记录节点
var AdaptiveRelationshipSelNodesSubNodes = {}; // 所有选中节点呈现的关系记录
function createAdaptiveRelationshipView(cell, toType) {
  cell['edges'] && cell['edges'].forEach(edge => {
    // 以当前节点为源
    if (toType == 1) { // source
      if (edge.source && edge.source.id == cell.id) {
        if (edge.target) {
          // 存入选中节点记录数据中
          AdaptiveRelationshipSelectNodes[nowSelectNode.id]['relationCells'][edge.target.id] = edge.target;
          // 目标染色
          setAdaptiveNodeColor(edge.target, 'lightblue');
          // 选中节点影响的目标节点原始颜色修正
          if (SaveSelectNodeTargetNodeColor[edge.target.id]) {
            if (AdaptiveRelationshipSelectNodes[edge.target.id]) {
              SaveSelectNodeTargetNodeColor[edge.target.id] = 'cadetblue';
            } else {
              SaveSelectNodeTargetNodeColor[edge.target.id] = 'lightblue';
            }
          }
          // 存入呈现关系列表
          if (!AdaptiveRelationshipSelNodesSubNodes[edge.target.id]) {
            AdaptiveRelationshipSelNodesSubNodes[edge.target.id] = {cell: edge.target, source: {}, target: {}};
          }
          if (!AdaptiveRelationshipSelNodesSubNodes[edge.target.id]['source'][nowSelectNode.id]) {
            AdaptiveRelationshipSelNodesSubNodes[edge.target.id]['source'][nowSelectNode.id] = nowSelectNode['adaptiveData']['fname'];
          }
          // 处理目标节点
          createAdaptiveRelationshipView(edge.target, 1);
        }
      }
    }
    // 以当前节点为目标
    if (toType == 2) { // target
      if (edge.target && edge.target.id == cell.id) {
        if (edge.source) {
          // 存入选中节点记录数据中
          AdaptiveRelationshipSelectNodes[nowSelectNode.id]['relationCells'][edge.source.id] = edge.source;
          // 源染色
          setAdaptiveNodeColor(edge.source, 'lightblue');
          // 选中节点影响的目标节点原始颜色修正
          if (SaveSelectNodeTargetNodeColor[edge.source.id]) {
            if (AdaptiveRelationshipSelectNodes[edge.source.id]) {
              SaveSelectNodeTargetNodeColor[edge.source.id] = 'cadetblue';
            } else {
              SaveSelectNodeTargetNodeColor[edge.source.id] = 'lightblue';
            }
          }
          // 存入呈现关系列表 
          if (!AdaptiveRelationshipSelNodesSubNodes[edge.source.id]) {
            AdaptiveRelationshipSelNodesSubNodes[edge.source.id] = {cell: edge.source, source: {}, target: {}};
          }
          if (!AdaptiveRelationshipSelNodesSubNodes[edge.source.id]['target'][nowSelectNode.id]) {
            AdaptiveRelationshipSelNodesSubNodes[edge.source.id]['target'][nowSelectNode.id] = nowSelectNode['adaptiveData']['fname'];
          }
          // 处理源节点
          createAdaptiveRelationshipView(edge.source, 2);
        }
      }
    }
  });
}
/**
 * 生成节点描述
 * @param {参数对象} item 
 */
function createAdaptiveCellView(item, adaptiveValue) {
  var value = (!adaptiveValue || adaptiveValue == '') ? '值' : adaptiveValue;
  var valueStr = '';
  valueStr += '<font style="font-size: 14px"><b>' + item.fname + '</b></font>';
  if (item['Classify']) {
    switch (parseInt(item['Classify'])) {
      case 1: // 普通参数
        switch (parseInt(item['ValueType'])) {
          case 1: // 关系
            break;
        
          case 2: // 固定值
            valueStr += '<br/><font style="color: darkslategrey">(' + value + item['ValueUnit'] + ')</font>';
            break;
      
          case 3: // 区间
            valueStr += '<br/><font style="color: darkslategrey">(' + value + item['ValueUnit'] + ')</font>';
            break;
    
          case 4: // 输入值
            valueStr += '<br/><font style="color: darkslategrey">(' + value + item['ValueUnit'] + ')</font>';
            break;
                  
          default:
            break;
        }
        break;

      case 4: // 公式&约束
        valueStr += '<br/><font style="color: darkslategrey">(' + value + item['ValueUnit'] + ')</font>';
        break;

      default:
        break;
    }
  }
  return valueStr;
}
/**
 * 获取节点参数值
 * @param {节点} cell 
 */
function getAdaptiveValue(cell) {
  // 返回值
  var value = {};
  if (cell) {
    value['returnData'] = cell['adaptiveValue'];
    // 获取参数信息
    var adaptiveData = cell['adaptiveData'];
    // 获取参数类型
    var Classify = parseInt(adaptiveData['Classify']);
    // 函数计算
    if (Classify == 4) {
      // 获取节点列表
      var graph = mainEditorUi.editor.graph;
      var cells = graph.getModel()['cells'];
      // 是否允许计算
      var isCompute = true;
      // 获取引入参数列表
      var ParamRelation = adaptiveData['ParamRelation'];
      // 获取引入参数结果
      var paramStr = '';
      var returnStr = '';
      ParamRelation && ParamRelation.forEach(relation => {
        // 获取变量名
        paramStr += ',' + relation.FVaribleName;
        // 获取节点
        var paramCell = cells[adaptiveDesignJSON[relation.FSubID]];
        var paramValue = getAdaptiveValue(paramCell);
        returnStr += ',' + JSON.stringify(paramValue);
        // 判断是否计算
        if (!paramValue['returnData'] || paramValue['returnData'] == '') {
          isCompute = false;
        }
      });
      // 计算
      if (isCompute) {
        // 处理引入参数字符串
        if (paramStr.length > 0) {
          paramStr = paramStr.substring(1);
        }
        if (returnStr.length > 0) {
          returnStr = returnStr.substring(1);
        }
        // 执行函数
        value['returnData'] = eval('(function(' + paramStr + '){' + adaptiveData['Calculation'] + '})(' + returnStr + ')');
        // 设置为可保存状态
        nowFlowAdaptiveSaveDataSaveOp = 1;
      }
    }
  } else {
    value['returnData'] = '';
  }
  return value;
}
/**
 * 遍历所有参数节点为无值节点设置警告
 */
function checkAdaptiveNodeComputeWarning() {
  var graph = mainEditorUi.editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  // 验证参数是否有值
  adaptiveDesignList.forEach(item => {
    if (item['Classify'] && (item['Classify'] == 1 || item['Classify'] == 4)) {
      // 获取节点
      var cell = cells[adaptiveDesignJSON[item.id]];
      // 判断是否无值
      if (!cell.adaptiveValue || cell.adaptiveValue == '') {
        // 设置警告
        var overlay = new mxCellOverlay(graph.warningImage, '未计算');
        overlay.addListener(mxEvent.CLICK, function(sender, evt){
            mxUtils.alert('未计算');
        }); 
        graph.addCellOverlay(cell, overlay);
      }
    }
  });
}
/**
 * 节点上色
 * @param {节点} cell 
 * @param {颜色} color 
 */
function setAdaptiveNodeColor(cell, color) {
  var graph = mainEditorUi.editor.graph;
  var style = cell.getStyle();
  var fillColorStr = style.match(/fillColor=(\S*);/);
  if (fillColorStr) { // 有背景色
    style = style.replace(fillColorStr[0], 'fillColor=' + color + ';');
  } else { // 无色
    style += style + 'fillColor=' + color + ';';
  }
  cell.setStyle(style);
  graph.refresh(cell);
}
/**
 * 清除所有节点的颜色
 */
function cleanAdaptiveNodeColor() {
  var graph = mainEditorUi.editor.graph;
  // 获取参数节点并生成控件
  var cells = graph.getModel()['cells'];
  for (var name in cells) {
    var element = cells[name];
    if (element['classifyId'] && element['classifyId']!='group' && element['classifyId']!='paramNote') {
      var style = element.getStyle();
      var fillColorStr = style.match(/fillColor=(\S*);/);
      if (fillColorStr && fillColorStr[0] != 'fillColor=white;') { // 有背景色
        style = style.replace(fillColorStr[0], 'fillColor=white;');
        element.setStyle(style);
        graph.refresh(element);
      }
    }
  }
}
/**
 * 获取参数数据
 */
var AdaptiveSaveNames = ['adaptiveBedeutung', 'adaptiveKosten', 'adaptiveValue'];
function getAdaptiveSaveData() {
  var data = {};
  var graph = mainEditorUi.editor.graph;
  var cells = graph.getModel()['cells'];
  for (var id in cells) {
    var cell = cells[id];
    var saveData = {};
    for (var i=0; i<AdaptiveSaveNames.length; i++) {
      // 判断是否需要存储
      if (cell[AdaptiveSaveNames[i]] && cell[AdaptiveSaveNames[i]] != '') {
        // 存储
        saveData[AdaptiveSaveNames[i]] = cell[AdaptiveSaveNames[i]];
      }
    }
    // 判断是否存入
    if (!$.isEmptyObject(saveData)) {
      saveData['key'] = cell['adaptiveKey']
      data[id] = saveData;
    }
  }
  return data;
}