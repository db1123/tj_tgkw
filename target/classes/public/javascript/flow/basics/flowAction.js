/**
 * 流程图编辑器-编辑状态
 */
function EditorUiEdit() {
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  graph.setEnabled(true); // 启用流程图编辑
  control_menu_op = 1; // 菜单面板
  control_toolbar_op = 1; // 工具栏面板 
  control_sidebar_op = 1; // 左侧图形面板
  control_outline_op = 1; // 缩略图面板
  control_format_op = 1; // 右侧样式面板
  control_logic_op = 1; // 右侧业务属性面板
  control_adaptive_op = 0; // 右侧自适应设计属性面板
  function_open_basic_op = 1; // 打开普通流程图
  function_open_adaptive_op = 1; // 打开自适应模型
  function_all_op = 1; // 所有功能是否可编辑
  function_draw_edit_op = 1; // 绘图区是否可编辑
  function_undo_redo_op = 1; // 撤销 恢复
  mainEditorUi.refresh();
}

/**
 * 流程图编辑器-提交状态
 */
function EditorUiSubmit() {
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  graph.setEnabled(false); // 禁止流程图编辑
  control_menu_op = 1; // 菜单面板
  control_toolbar_op = 0; // 工具栏面板 
  control_sidebar_op = 0; // 左侧图形面板
  control_outline_op = 0; // 缩略图面板
  control_format_op = 0; // 右侧样式面板
  control_logic_op = 1; // 右侧业务属性面板
  control_adaptive_op = 0; // 右侧自适应设计属性面板
  function_open_basic_op = 1; // 打开普通流程图
  function_open_adaptive_op = 1; // 打开自适应模型
  function_all_op = 0; // 所有功能是否可编辑
  function_draw_edit_op = 0; // 绘图区是否可编辑
  function_undo_redo_op = 0; // 撤销 恢复
  mainEditorUi.refresh();
}

/**
 * 菜单显示控制-无需保存提交状态
 */
function EditorMenuNoSaveNoSubmit() {
  mainEditorUi.actions.get('save').setEnabled(false); // 禁用保存
  mainEditorUi.actions.get('saveEdit').setEnabled(false); // 禁用编辑保存
  mainEditorUi.actions.get('saveAs').setEnabled(true); // 启用另存为
  mainEditorUi.actions.get('submit').setEnabled(false); // 禁用提交
}

/**
 * 菜单显示控制-可保存状态
 */
function EditorMenuSave() {
  mainEditorUi.actions.get('save').setEnabled(true); // 启用保存
  mainEditorUi.actions.get('saveEdit').setEnabled(true); // 启用编辑保存
  mainEditorUi.actions.get('saveAs').setEnabled(true); // 启用另存为
  mainEditorUi.actions.get('submit').setEnabled(false); // 禁用提交
}

/**
 * 菜单显示控制-可提交状态
 */
function EditorMenuSubmit() {
  mainEditorUi.actions.get('save').setEnabled(false); // 禁用保存
  mainEditorUi.actions.get('saveEdit').setEnabled(false); // 禁用编辑保存
  mainEditorUi.actions.get('saveAs').setEnabled(true); // 启用另存为
  mainEditorUi.actions.get('submit').setEnabled(true); // 启用提交
}

/**
 * 流程图回退面板
 */
function flowBack() {
  // 主面板
  var content = document.createElement('div');
  content.style.padding = '4px';
  // 生成控制面板
  var toolbar = document.createElement('div');
  var tb = new mxToolbar(toolbar);
  // 回退按钮
  tb.addItem('返回上一级', STENCIL_PATH+'/simulation/return_48px.png', function(evt)
  {
    if (saveXMLArray.length > 0) { // 先处理返回操作，再清除已返回元素
      savaParent(); // 逐级保存修改内容
      var editor = mainEditorUi.editor;
      var doc = mxUtils.parseXml(saveXMLArray[saveXMLArray.length-1].xml);
      editor.setGraphXml(doc.documentElement); // 打开流程图
      saveXMLArray.length = saveXMLArray.length - 1; // 删除最后一个数组元素
      // 判断是否需要高亮显示
      if (projectPath.length > 0) {
        var graph = editor.graph;
        var cell = findNodeById(projectPath[saveXMLArray.length]);
        var highlight = new mxCellHighlight(graph, 'red', 6);
        highlight.highlight(graph.view.getState(cell));
      }
    }
    if (saveXMLArray.length == 0) { // 返回目标为顶层
      wnd.setVisible(false); // 关闭弹出窗
      // 返回主流程后启用部分功能
      var editor = mainEditorUi.editor;
      var graph = editor.graph;
      graph.setEnabled(true); // 启用流程图编辑
      control_toolbar_op = 1; // 工具栏面板 
      control_sidebar_op = 1; // 左侧图形面板
      control_outline_op = 1; // 缩略图面板
      function_draw_edit_op = 1; // 绘图区启用编辑
      function_undo_redo_op = 1; // 撤销 恢复 功能启用
      function_fileUpLoad_op = 1; // 附件编辑功能启用
      mainEditorUi.refresh();
    }
  });
  // 工具条写入控制面板
  content.appendChild(toolbar);
  // 预览控制面板窗口
  var wnd = new mxWindow('返回上一级', content, document.body.offsetWidth/1.35, 75, 68, 80, false);
  wnd.setMaximizable(false);
  wnd.setScrollable(false);
  wnd.setResizable(false);
  wnd.setClosable(false);
  wnd.setVisible(true);
}

/**
 * 逐级保存修改内容
 */
function savaParent() {
  var graph = mainEditorUi.editor.graph;
  // 获取当前子流程图xml字符串
  var encoder = new mxCodec();
  var node = encoder.encode(graph.getModel());
  var xmlString = mxUtils.getXml(node);
  // 当前xml字符串
  var nowXml = xmlString;
  // 循环拾取面包屑(祖先流程逐级循环)
  for (var i=saveXMLArray.length-1; i>=0; i--) {
    var nodeId = saveXMLArray[i].nodeId;
    var xml = $.parseXML(saveXMLArray[i].xml);
    $(xml).find('mxCell[id="' + nodeId + '"]').find('Object[as=subprocess]').each(function() {
      var $this = $(this); // 当前标签
      $this.attr('subprocessXML', nowXml);
    });
    nowXml = saveXMLArray[i].xml = mxUtils.getXml(xml.documentElement);
  }
}

/**
 * 检查流程图是否完整
 */
function checkFlow() {
  if (checkFLowStartStopNode() && checkFLowAllLeader() && checkFLowAllWorkNum()) {
    return true;
  }
  return false;
}

/**
 * 检查是否存在开始结束节点
 * @returns 是否验证
 */
 function checkFLowStartStopNode() {
  var msg = '';
  var graph = mainEditorUi.editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  var start = false;
  var stop = false;
  for (var name in cells) {
    var element = cells[name];
    if (element['simulationId'] && element['simulationId'] == 'start') {
      start = true;
    }
    if (element['simulationId'] && element['simulationId'] == 'stop') {
      stop = true;
    }
  }
  if (!start || !stop) {
    toastr.error((start?'':'开始') + (stop?'':'结束') + '节点未设置');
    return false;
  }
  return true;
}

/**
 * 检查是否所有节点都设置工作周期(除开始[结束]节点)
 * @returns 是否验证
 */
function checkFLowAllWorkNum() {
  var msg = '';
  var graph = mainEditorUi.editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  for (var name in cells) {
    var element = cells[name];
    if (element['simulationId']
        && element['simulationId'] != 'start'
        // && element['simulationId'] != 'stop'
        && element['edge'] == false
        && !element['work_num']) {
      msg += ',' + (element['value'] == '' ? '[未命名]' : element['value']);
    }
  }
  if (msg.length > 0) {
    toastr.error('节点：' + msg.substring(1) + ' 未设置工作周期');
    return false;
  }
  return true;
}

/**
 * 检查是否所有节点都设置开始结束时间(除开始[结束]节点)
 * @returns 是否验证
 */
function checkFLowAllStartEndTime() {
  var msg = '';
  var graph = mainEditorUi.editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  for (var name in cells) {
    var element = cells[name];
    if (element['simulationId']
        && element['simulationId'] != 'start'
        // && element['simulationId'] != 'stop'
        && element['edge'] == false
        && (!element['start_date'] || !element['end_date'])) {
      msg += ',' + (element['value'] == '' ? '[未命名]' : element['value']);
    }
  }
  if (msg.length > 0) {
    toastr.error('节点：' + msg.substring(1) + ' 未设置起始时间');
    return false;
  }
  return true;
}

/**
 * 检查是否所有节点都设置团队负责人(除开始[结束]节点)
 * @returns 是否验证
 */
function checkFLowAllLeader() {
  var msg = '';
  var graph = mainEditorUi.editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  for (var name in cells) {
    var element = cells[name];
    if (element['simulationId']
        && element['simulationId'] != 'start'
        // && element['simulationId'] != 'stop'
        && element['edge'] == false
        && (!element['leaderUserData'] || element['leaderUserData'].size <= 0)) {
      msg += ',' + (element['value'] == '' ? '[未命名]' : element['value']);
    }
  }
  if (msg.length > 0) {
    toastr.error('节点：' + msg.substring(1) + ' 未设置负责人');
    return false;
  }
  return true;
}