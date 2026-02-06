function exam (mode) {
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  var timeOP = false; // 模拟运行开关
  var minute = 0, second = 0; // 时间默认值 
  var timerClock, renderNode, timerNode1, timerNode2, timerNode3, timerList = {}; // 线程
  var nowRunNode = []; // 待执行列表
  var nowRenderNode = []; // 待刷新节点
  var log = []; // 日志总览
  var dir = STENCIL_PATH; // 静态文件路径
  var mainThat = this;
  var minWidth = 392; // 工具栏最小宽度
  if (mode && mode == 'view') {
    minWidth = 318;
  }
  // 开启提示
  graph.setTooltips(true);
  // 屏蔽编辑区域
  // if (!mode) {
    // mainEditorUi.menubarContainer.style.height = 0;
    // mainEditorUi.menubarContainer.style.display = 'none'; // 屏蔽菜单
    // mainEditorUi.toolbarContainer.style.height = 0;
    // mainEditorUi.toolbarContainer.style.display = 'none'; // 屏蔽工具栏
    // mainEditorUi.sidebarContainer.style.width = 0;
    // mainEditorUi.sidebarContainer.style.display = 'none'; // 屏蔽节点面板
    // mainEditorUi.outlineContainer.style.width = 0;
    // mainEditorUi.outlineContainer.style.display = 'none'; // 屏蔽缩略图面板
    // mainEditorUi.hsplit.style.display = 'none'; // 屏蔽拖拽条
    // mainEditorUi.toggleFormatPanel(true); // 屏蔽右侧参数面板
    // graph.setEnabled(false); // 禁止流程图编辑
  // }
  // 清空线程(方法)
  this.clearWaitAll = function() {
    timeOP = false; // 模拟器停止运行
    clearInterval(timerClock); // 清除计时器
    timerClock = null; // 清除计时器
    clearInterval(renderNode); // 清除渲染器
    renderNode = null; // 清除渲染器
    clearInterval(timerNode1); // 清除节点
    timerNode1 = null; // 清除节点
    clearInterval(timerNode2); // 清除节点
    timerNode2 = null; // 清除节点
    clearInterval(timerNode3); // 清除节点
    timerNode3 = null; // 清除节点
    minute = 0, second = 0; // 重置时钟
    for (var name in timerList) {
      clearInterval(timerList[name]); // 定时器清除
    }
    timerList = {}; // 清空定时器列表 
    nowRunNode = []; // 清空执行队列
  }
  // 普通线条样式(方法)
  this.edgeDefaultStyle = function(edge) {
    edge.setStyle('edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;');
    var state = graph.view.getState(edge);
    state.shape.node.getElementsByTagName('path')[0].setAttribute('visibility', 'hidden');
    state.shape.node.getElementsByTagName('path')[0].removeAttribute('stroke-width');
    state.shape.node.getElementsByTagName('path')[0].setAttribute('stroke', '#000000');
    state.shape.node.getElementsByTagName('path')[1].removeAttribute('class');
  }
  // 运行中线条样式(方法)
  this.edgeRunStyle = function(edge) {
    edge.setStyle('strokeWidth=3;endArrow=block;endSize=2;endFill=1;strokeColor=black;rounded=1;');
    var state = graph.view.getState(edge);
    state.shape.node.getElementsByTagName('path')[0].removeAttribute('visibility');
    state.shape.node.getElementsByTagName('path')[0].setAttribute('stroke-width', '6');
    state.shape.node.getElementsByTagName('path')[0].setAttribute('stroke', 'lightGray');
    state.shape.node.getElementsByTagName('path')[1].setAttribute('class', 'flow-line');
  }
  // 清空节点历史记录
  this.nodeClean = function () {
    cleanExamRunNodesTemporaryData();
  }
  // 写日志
  this.writeLog = function(node, List, rowData) {
    var msg = {
      key: rowData.key ? rowData.key : guid(),
      c_date: rowData.c_date ? rowData.c_date : nowDate(),
      type: rowData.type ? rowData.type : '无',
      node_id: node && node['id'] ? node['id'] : -1,
      node_name: node && node['value'] ? cleanHTML(node['value']) : '',
      note: rowData.note ? rowData.note : ''
    };
    // 判断是否有脉冲计数
    if (node['pulseCount']) {
      msg['note'] = '第' + node['pulseCount'] + '次脉冲：' + msg['note'];
    }
    // 添加到节点日志
    List.push(msg);
    // 添加到汇总日志
    log.push(msg);
    // 刷新汇总日志列表
    $('#examLogTable').dataTable().fnClearTable();
    $('#examLogTable').dataTable().fnAddData(log, true);
  }
  // 添加节点到待执行列表中
  this.addNodeToList = function(list, node) {
    var op = true;
    // 判断是否可以添加
    list.forEach(item => {
      if (item.id == node.id) { // 如果列表中存在该项则不能添加
        op = false;
      }
    });
    // 添加数据
    if (op) {
      list.push(node);
    }
  }
  // 主面板
  var content = document.createElement('div');
  content.style.padding = '4px';
  // 生成控制面板
  var toolbar = document.createElement('div');
  var tb = new mxToolbar(toolbar);
  // 开始执行
  this.startAction = function() {
    if (timeOP == true) return; // 如果处于运行中则什么也不执行
    timeOP = true; // 计时器开关
    $('#timeTextDiv').html('00:00'); // 重置时间
    mainThat.nodeClean(); // 清空节点历史记录
    var cells = graph.getModel()['cells']; // 获取所有节点
    // 计时器线程
    var waitClock = function(dtd) {
      timerClock = setInterval(function() {
        second++;
        if (second >= 60) { // 秒钟进位
          second = 0;
          minute++;
        }
        var minuteView = minute, secondView = second;
        if (minute <= 9) minuteView = '0' + minute;
        if (second <= 9) secondView = '0' + second;
        $('#timeTextDiv').html(minuteView + ':' + secondView);
        // 模拟器停止运行
        if (timeOP == false) {
          dtd.resolve(); // 结束线程
        }
      }, 1000);
      return dtd.promise();
    };
    $.Deferred(waitClock)
      .done(function(){})
      .fail(function(){});
    // 渲染节点线程
    var waitRenderNode = function(dtd) {
      renderNode = setInterval(function() {
        var nodeItem = null;
        var node = null;
        var type = null;
        // 提取数组中的首个元素
        if (nowRenderNode[0]) {
          nodeItem = nowRenderNode[0];
          node = nodeItem.node;
          type = nodeItem.type;
          // 删除数组中的首个元素
          nowRenderNode.shift();
          // 判断渲染方式
          switch (type) {
            case 1: // 可开启节点
              // 修改节点前连线样式为普通线条
              node['edges'] && node['edges'].forEach(edge => {
                if (edge.target && edge.target.id == node.id) {
                  mainThat.edgeDefaultStyle(edge);
                }
              });
              // 设置节点状态
              node['run']['state'] = 'working';
              // 修改节点样式为开始执行
              graph.removeCellOverlays(node);
              var overlay = new mxCellOverlay(
                new mxImage('/plugins/mxgraph/images/overlaysBig/037bk.gif', 32, 32),
                '开始执行');
              graph.addCellOverlay(node, overlay);
              // 增加图标单击事件
              overlay.addListener(mxEvent.CLICK, function(sender, evt){
                var dlg = new NodeRunLogDialog(mainEditorUi);
                mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
                dlg.init(node['run']['infoList']);
              });
              break;
          
            case 2: // 可完成节点
              // 节点状态变为完成
              node['run']['state'] = 'finish';
              // 当前节点样式变为完成
              graph.removeCellOverlays(node);
              var overlay = new mxCellOverlay(
                new mxImage('/plugins/mxgraph/images/overlaysBig/Finished_32px.png', 32, 32),
                '完成');
              graph.addCellOverlay(node, overlay);
              // 增加图标单击事件
              overlay.addListener(mxEvent.CLICK, function(sender, evt){
                var dlg = new NodeRunLogDialog(mainEditorUi);
                mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
                dlg.init(node['run']['infoList']);
              });
              // 遍历节点下所有的线
              node['edges'] && node['edges'].forEach(edge => {
                if (edge.source && edge.source.id == node.id) { // 取出所有以当前节点为源的线
                  var target = edge.target; // 取出目标节点
                  // 目标节点内增加来源ID记录
                  if (!target['fromNodeIds']) {
                    target['fromNodeIds'] = {};
                  }
                  target['fromNodeIds'][node.id] = node.id;
                  // 判断是否有脉冲计数
                  if (node['pulseCount'] != null) {
                    target['pulseCount'] = node['pulseCount'];
                  }
                  nowRunNode.push(target); // 目标节点加入执行队列中继续执行
                  mainThat.edgeRunStyle(edge); // 修改节点后连线样式为开始执行
                  // 修改目标节点样式为开始执行
                  graph.removeCellOverlays(target);
                  var overlay = new mxCellOverlay(
                    new mxImage('/plugins/mxgraph/images/overlaysBig/037bk.gif', 32, 32),
                    '开始执行');
                  graph.addCellOverlay(target, overlay);
                  // 增加图标单击事件
                  overlay.addListener(mxEvent.CLICK, function(sender, evt){
                    var dlg = new NodeRunLogDialog(mainEditorUi);
                    mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
                    dlg.init(target['run']['infoList']);
                  });
                }
              });
              break;
          
            case 3: // 不可开启
              // 更新运行属性信息
              node['run']['state'] = 'warning';
              node['run']['errorNum'] = node['run']['errorNum'] + 1;
              // 修改启动节点样式为异常
              graph.removeCellOverlays(node);
              var overlay = new mxCellOverlay(
                new mxImage('/plugins/mxgraph/images/overlaysBig/Warning_32px.png', 32, 32),
                '异常');
              graph.addCellOverlay(node, overlay);
              // 增加警告图标单击事件
              overlay.addListener(mxEvent.CLICK, function(sender, evt){
                var dlg = new NodeRunLogDialog(mainEditorUi);
                mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
                dlg.init(node['run']['errorList']);
              });
              break;
          
            case 4: // 出错
              // 错误信息更新
              node['run']['state'] = 'warning';
              node['run']['errorNum'] = node['run']['errorNum'] + 1;
              // 修改启动节点样式为异常
              graph.removeCellOverlays(node);
              var overlay = new mxCellOverlay(
                new mxImage('/plugins/mxgraph/images/overlaysBig/Warning_32px.png', 32, 32),
                '异常');
              graph.addCellOverlay(node, overlay);
              // 增加警告图标单击事件
              overlay.addListener(mxEvent.CLICK, function(sender, evt){
                var dlg = new NodeRunLogDialog(mainEditorUi);
                mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
                dlg.init(node['run']['errorList']);
              });
              break;
          
            default:
              break;
          }
        }
        // 模拟器停止运行
        if (timeOP == false) {
          dtd.resolve(); // 结束线程
        }
      }, 300);
      return dtd.promise();
    };
    $.Deferred(waitRenderNode)
      .done(function(){})
      .fail(function(){});
    // 节点线程
    var waitNode = function(dtd) {
      // 将线程本身存入变量方便调用
      var that = this;
      // 递归获取方法计算结果
      this.getFunctionReturnValue = function(editorUi, nowRow) {
        var graph = editorUi.editor.graph;
        var cells = graph.getModel().cells;
        var returnStr = '';
        if (nowRow['f_input_parameter']) {
          nowRow['f_input_parameter'].forEach((item)=>{
            var cell = cells[item.f_node_id];
            if (cell) {
              var dataArr = cell['variableData'];
              if (dataArr) {
                for (var i = 0, l = dataArr.length; i < l; i++) {
                  var row = dataArr[i];
                  if (row['f_id'] == item.f_variable_id) { // 确定是输入参数后继续执行
                    if (row['f_type'] == 1) { // 方法
                      if (!row['tempValue']) { // 没有值的时候赋Null
                        row['tempValue'] = null;
                      }
                      if (row['f_return_type'] == 1) { // 数值
                        returnStr += ',' + row['tempValue'];
                      } else if (row['f_return_type'] == 2) { // 字符串
                        returnStr += ',"' + row['tempValue'] + '"';
                      }
                    } else if (row['f_type'] == 2) { // 常量
                      if (row['f_return_type'] == 1) { // 数值
                        returnStr += ',' + row['f_value'];
                      } else if (row['f_return_type'] == 2) { // 字符串
                        returnStr += ',"' + row['f_value'] + '"';
                      }
                    }
                    break;
                  }
                }
              }
            }
          });
        }
        if (returnStr.length > 0) {
          returnStr = returnStr.substring(1);
        }
        var value;
        try {
          value = eval('(' + nowRow['f_function'] + ')(' + returnStr + ')');
          var node = cells[nowRow['f_node_id']];
          if (node && node['run'] && node['run']['infoList']) {
            var infoList = node['run']['infoList'];
            mainThat.writeLog(node, infoList, {type: '方法执行', note: '方法【' + nowRow['f_name'] + '】执行结果为:' + value});
          }
        } catch(err) {
          alert("方法执行运行异常：" + err.message);
        }
        return value;
      }
      // 节点运行函数
      this.nodeRunAction = function() {
        var node = null;
        var pulseCount = null;
        // 提取数组中的首个元素
        if (nowRunNode[0]) {
          node = nowRunNode[0];
          // 删除数组中的首个元素
          nowRunNode.shift();
          // 取出脉冲次数
          if (node['pulseCount']) {
            pulseCount = node['pulseCount'];
          }
          // 初始化节点状态
          if (!node['run']) {
            node['run'] = { state: 'working', infoList: [], errorNum: 0 , errorList: [] };
          }
          // 生成日志存储变量
          var infoList = node['run']['infoList'];
          var errorList = node['run']['errorList'];
          // 如果当前节点不是错误状态才可以执行
          if (node['run']['state'] != 'error') {
            // 状态开关
            var op = true;
            // ********判断节点是否可以开始执行********
            if (node['nodeStartData'] && node['nodeStartData'].length > 0) {
              node['nodeStartData'].forEach(item => {
                switch (item['f_type']) {
                  case 1: // 节点
                    var f_node = cells[item['f_node']]; // 获取条件节点
                    if (f_node['run'] && f_node['run']['state']) {
                      var run_state = f_node['run']['state'];
                      if (item['f_node_state'] == 1 && run_state == 'working') { // 开始
                        mainThat.writeLog(node, infoList, {type: '启动条件', note: '节点【' + cleanHTML(f_node['value']) + '】状态为开始与判断条件相符'});
                      } else if (item['f_node_state'] == 2 && run_state == 'finish') { // 完成
                        mainThat.writeLog(node, infoList, {type: '启动条件', note: '节点【' + cleanHTML(f_node['value']) + '】状态为完成与判断条件相符'});
                      } else { // 未知节点状态视为异常
                        op = false;
                        var stateName = '';
                        switch (run_state) {
                          case 'working': stateName = '开始'; break;
                          case 'finish': stateName = '完成'; break;
                          case 'warning': stateName = '异常'; break;
                          case 'error': stateName = '错误'; break;
                          default: stateName = '无'; break;
                        }
                        var nodeState = '';
                        switch (item['f_node_state']) {
                          case 1: nodeState = '开始'; break;
                          case 2: nodeState = '完成'; break;
                          default: nodeState = '无'; break;
                        }
                        mainThat.writeLog(node, errorList, {type: '启动条件', note: '节点【' + cleanHTML(f_node['value']) + '】状态为(' + stateName + '),与完成条件设定状态(' + nodeState + ')不符'});
                      }
                    } else { // 无运行状态视为异常
                      op = false;
                      mainThat.writeLog(node, errorList, {type: '启动条件', note: '节点【' + cleanHTML(f_node['value']) + '】无状态参数'});
                    }
                    break;
                
                  case 2: // 方法
                    var go = true;
                    var returnStr = '';
                    item['f_input_parameter'].forEach((parameter)=>{
                      // 判断是否为来源节点的条件
                      if (!node['fromNodeIds'][parameter.f_node_id]) {
                        go = false;
                      }
                      if (go) {
                        var cell = cells[parameter.f_node_id];
                        var dataArr = cell['variableData'];
                        for (var i = 0, l = dataArr.length; i < l; i++) {
                          var row = dataArr[i];
                          if (row['f_id'] == parameter.f_variable_id) { // 确定是输入参数后继续执行
                            if (row['f_type'] == 1) { // 方法
                              if (!row['tempValue']) { // 没有值的时候赋Null
                                row['tempValue'] = null;
                              }
                              if (row['f_return_type'] == 1) { // 数值
                                returnStr += ',' + row['tempValue'];
                              } else if (row['f_return_type'] == 2) { // 字符串
                                returnStr += ',"' + row['tempValue'] + '"';
                              }
                            } else if (row['f_type'] == 2) { // 常量
                              if (row['f_return_type'] == 1) { // 数值
                                returnStr += ',' + row['f_value'];
                              } else if (row['f_return_type'] == 2) { // 字符串
                                returnStr += ',"' + row['f_value'] + '"';
                              }
                            }
                            break;
                          }
                        }
                      }
                    });
                    // 判断是否为来源节点
                    if (go) {
                      if (returnStr.length > 0) {
                        returnStr = returnStr.substring(1);
                      }
                      try {
                        if (eval("(" + item['f_function'] + ")(" + returnStr + ")")) {
                          mainThat.writeLog(node, infoList, {type: '启动条件', note: '方法【' + item['f_name'] + '】执行通过'});
                        } else {
                          op = false;
                          mainThat.writeLog(node, errorList, {type: '启动条件', note: '方法【' + item['f_name'] + '】执行未通过'});
                        }
                      } catch(err) {
                        op = false;
                        mainThat.writeLog(node, errorList, {type: '启动条件', note: '方法【' + item['f_name'] + '】异常(' + err.message + ')'});
                      }
                    }
                    break;

                  default: // 未知条件一概不能通过
                    op = false;
                    mainThat.writeLog(node, errorList, {type: '启动条件', note: '方法【' + item['f_name'] + '】异常(' + err.message + ')'});
                    break;
                }
              });
            } else { // 无条件要求直接通过
              mainThat.writeLog(node, infoList, {type: '启动条件', note: '未设置启动条件,直接通过'});
            }
            // ******根据启动条件判定结果进行下一步操作******
            if (op) { // 可开启节点
              nowRenderNode.push({ node:node, type:1 });
              // ********执行当前节点的方法变量并保存********
              var variableData = node['variableData'];
              if (variableData) {
                for (var i = 0, l = variableData.length; i < l; i++) {
                  var row = variableData[i];
                  if (row['f_type'] == 1) { // 方法
                    if (row['f_action'] == 2) { // 执行结果累加
                      if (!row['tempValue']) {
                        if (row['f_return_type'] == 1) { // 数值
                          row['tempValue'] = 0;
                        } else if (row['f_return_type'] == 2) { // 字符串
                          row['tempValue'] = '';
                        }
                      }
                      row['tempValue'] = row['tempValue'] + that.getFunctionReturnValue(mainEditorUi, row);
                      mainThat.writeLog(node, infoList, {type: '节点变量', note: '以累加形式生成结果：' + row['tempValue']});
                    } else { // 其他情况下当前值覆盖原有值
                      row['tempValue'] = that.getFunctionReturnValue(mainEditorUi, row);
                      mainThat.writeLog(node, infoList, {type: '节点变量', note: '生成结果：' + row['tempValue']});
                    }
                  }
                }
              }
              // ********判断节点是否可以完成********
              op = true;
              if (node['nodeEndData'] && node['nodeEndData'].length > 0) {
                node['nodeEndData'].forEach(item => {
                  switch (item['f_type']) {
                    case 1: // 节点
                      var f_node = cells[item['f_node']]; // 获取条件节点
                      if (f_node['run'] && f_node['run']['state']) {
                        var run_state = f_node['run']['state'];
                        if (item['f_node_state'] == 1 && run_state == 'working') { // 开始
                          mainThat.writeLog(node, infoList, {type: '完成条件', note: '节点【' + cleanHTML(f_node['value']) + '】状态为开始与判断条件相符'});
                        } else if (item['f_node_state'] == 2 && run_state == 'finish') { // 完成
                          mainThat.writeLog(node, infoList, {type: '完成条件', note: '节点【' + cleanHTML(f_node['value']) + '】状态为完成与判断条件相符'});
                        } else { // 位置节点状态视为异常
                          op = false;
                          var stateName = '';
                          switch (run_state) {
                            case 'working': stateName = '开始'; break;
                            case 'finish': stateName = '完成'; break;
                            case 'warning': stateName = '异常'; break;
                            default: stateName = '无'; break;
                          }
                          var nodeState = '';
                          switch (item['f_node_state']) {
                            case 1: nodeState = '开始'; break;
                            case 2: nodeState = '完成'; break;
                            default: nodeState = '无'; break;
                          }
                          mainThat.writeLog(node, errorList, {type: '完成条件', note: '节点【' + cleanHTML(f_node['value']) + '】状态为(' + stateName + '),与完成条件设定状态(' + nodeState + ')不符'});
                        }
                      } else { // 无运行状态视为异常
                        op = false;
                        mainThat.writeLog(node, errorList, {type: '完成条件', note: '节点【' + cleanHTML(f_node['value']) + '】无状态参数'});
                      }
                      break;
                  
                    case 2: // 方法
                      var go = true;
                      var returnStr = '';
                      item['f_input_parameter'].forEach((parameter)=>{
                        // 判断是否为来源节点的条件
                        if (!node['fromNodeIds'][parameter.f_node_id]) {
                          go = false;
                        }
                        if (go) {
                          var cell = cells[parameter.f_node_id];
                          if (cell) {
                            var dataArr = cell['variableData'];
                            if (dataArr) {
                              for (var i = 0, l = dataArr.length; i < l; i++) {
                                var row = dataArr[i];
                                if (row['f_id'] == parameter.f_variable_id) { // 确定是输入参数后继续执行
                                  if (row['f_type'] == 1) { // 方法
                                    if (!row['tempValue']) { // 没有值的时候赋Null
                                      row['tempValue'] = null;
                                    }
                                    if (row['f_return_type'] == 1) { // 数值
                                      returnStr += ',' + row['tempValue'];
                                    } else if (row['f_return_type'] == 2) { // 字符串
                                      returnStr += ',"' + row['tempValue'] + '"';
                                    }
                                  } else if (row['f_type'] == 2) { // 常量
                                    if (row['f_return_type'] == 1) { // 数值
                                      returnStr += ',' + row['f_value'];
                                    } else if (row['f_return_type'] == 2) { // 字符串
                                      returnStr += ',"' + row['f_value'] + '"';
                                    }
                                  }
                                  break;
                                }
                              }
                            }
                          }
                        }
                      });
                      // 判断是否为来源节点
                      if (go) {
                        if (returnStr.length > 0) {
                          returnStr = returnStr.substring(1);
                        }
                        try {
                          if (eval("(" + item['f_function'] + ")(" + returnStr + ")")) {
                            mainThat.writeLog(node, infoList, {type: '完成条件', note: '方法【' + item['f_name'] + '】执行通过'});
                          } else {
                            op = false;
                            mainThat.writeLog(node, errorList, {type: '完成条件', note: '方法【' + item['f_name'] + '】执行未通过'});
                          }
                        } catch(err) {
                          op = false;
                          mainThat.writeLog(node, errorList, {type: '完成条件', note: '方法【' + item['f_name'] + '】异常(' + err.message + ')'});
                        }
                      }
                      break;

                    default: // 未知条件一概不能通过
                      op = false;
                      mainThat.writeLog(node, errorList, {type: '完成条件', note: '节点完成条件类型未知'});
                      break;
                  }
                });
              } else { // 无条件要求直接通过
                mainThat.writeLog(node, infoList, {type: '完成条件', note: '未设置完成条件,直接通过'});
              }
              // ******根据完成条件判定结果进行下一步操作******
              if (op) { // 可完成节点
                nowRenderNode.push({ node:node, type:2 });
              } else { // 出错
                nowRenderNode.push({ node:node, type:4 });
                // 连续报错5次
                // if (node['run']['errorNum'] >= 5) {
                //   node['run']['state'] = 'error';
                //   // 修改节点样式为异常
                //   graph.removeCellOverlays(node);
                //   var overlay = new mxCellOverlay(
                //     new mxImage('/plugins/mxgraph/images/overlaysBig/Warning_32px.png', 32, 32),
                //     '错误');
                //   graph.addCellOverlay(node, overlay);
                //   // 增加警告图标单击事件
                //   overlay.addListener(mxEvent.CLICK, function(sender, evt){
                //     var dlg = new NodeRunLogDialog(mainEditorUi);
                //     mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
                //     dlg.init(node['run']['errorList']);
                //   });
                // } else {
                //   // 当前节点加入执行队列中继续执行
                //   nowRunNode.push(node);
                // }
              }
            } else { // 不可开启 
              nowRenderNode.push({ node:node, type:3 });
              // 连续报错5次
              // if (node['run']['errorNum'] >= 5) {
              //   node['run']['state'] = 'error';
              //   // 修改启动节点样式为异常
              //   graph.removeCellOverlays(node);
              //   var overlay = new mxCellOverlay(
              //     new mxImage('/plugins/mxgraph/images/overlaysBig/Warning_32px.png', 32, 32),
              //     '错误');
              //   graph.addCellOverlay(node, overlay);
              //   // 增加警告图标单击事件
              //   overlay.addListener(mxEvent.CLICK, function(sender, evt){
              //     var dlg = new NodeRunLogDialog(mainEditorUi);
              //     mainEditorUi.showDialog(dlg.container, 800, 360, true, true);
              //     dlg.init(node['run']['errorList']);
              //   });
              // } else {
              //   // 当前节点加入执行队列中继续执行
              //   nowRunNode.push(node);
              // }
            }
            // 清空来源
            node['fromNodeIds'] = {};
          }
        }
        // 模拟器停止运行
        if (timeOP == false) {
          dtd.resolve(); // 结束线程
        }
      };
      // 运行节点
      timerNode1 = setInterval(function() { that.nodeRunAction(); }, 300);
      timerNode2 = setInterval(function() { that.nodeRunAction(); }, 500);
      timerNode3 = setInterval(function() { that.nodeRunAction(); }, 700);
      return dtd.promise();
    };
    $.Deferred(waitNode)
      .done(function(){})
      .fail(function(){});
    // 添加启动节点到队列中
    for (var name in cells) {
      var element = cells[name];
      // 获取启动节点
      if (element['simulationId'] && (element['simulationId'] == 'start' || element['simulationId'] == 'startPulse')) {
        // 将启动节点放入开始执行列表
        nowRunNode.push(element);
      }
    }
    // 添加脉冲节点
    for (var name in cells) {
      var element = cells[name];
      // 获取启动节点
      if (element['simulationId'] && element['simulationId'] == 'startPulse') {
        // 生成唯一标识(每个start一个标识，即每条流程线路一个标识)
        var key = guid();
        // 启动线程
        var waitNode = function(dtd) {
          // 将线程本身存入变量方便调用
          var that = this;
          // 生成线程唯一ID
          this.key = key;
          // 脉冲次数
          this.count = 0;
          // 存储当前脉冲节点
          this.node = element;
          // 设置定时执行工具类
          timerList[this.key] = setInterval(function() {
            // 将启动节点放入开始执行列表
            nowRunNode.push(that.node);
            // 脉冲次数+1
            that.count = that.count + 1;
            // 脉冲次数写入节点
            that.node['pulseCount'] = that.count;
          }, element['startPulseTime'] ? element['startPulseTime']*1000 : 3000);
          return dtd.promise();
        };
        $.Deferred(waitNode)
          .done(function(){})
          .fail(function(){});
      }
    }
  };
  // 开始按钮
  tb.addItem('开始', dir+'/simulation/start_32px.png', function(evt)
  {
    mainThat.startAction();
  });
  // 停止按钮
  tb.addItem('停止', dir+'/simulation/stop_32px.png', function(evt)
  {
    mainThat.clearWaitAll();
  });
  // 计时器
  var timeText = document.createElement('div');
  timeText.style.display = 'inline-block';
  timeText.style.verticalAlign = 'middle';
  timeText.style.width = '80px';
  timeText.style.height = '30px';
  timeText.style.marginTop = '-40px';
  timeText.style.fontSize = '30px';
  timeText.setAttribute('id', 'timeTextDiv');
  mxUtils.write(timeText, '00:00');
  toolbar.appendChild(timeText);
  // 日志
  tb.addItem('日志', dir+'/simulation/log_viewer_query_32px.png', function(evt)
  {
    if (wnd.table.style.height == '66px') {
      wnd.setSize(750, 400);
      $('#placeholderLogDiv').css('width','395px');
    } else {
      wnd.setSize(minWidth, 66);
      $('#placeholderLogDiv').css('width','37px');
    }
  });
  // 放大
  tb.addItem('放大', dir+'/simulation/zoom_in32.png', function(evt)
  {
    graph.zoomIn();
  });
  // 缩小
  tb.addItem('缩小', dir+'/simulation/zoom_out32.png', function(evt)
  {
    graph.zoomOut();
  });
  // 实际尺寸
  tb.addItem('实际尺寸', dir+'/simulation/view_1_132.png', function(evt)
  {
    graph.zoomActual();
  });
  // 占位
  if (!mode || mode == 'edit') {
    var placeholderLogDiv = document.createElement('div');
    placeholderLogDiv.style.display = 'inline-block';
    placeholderLogDiv.style.verticalAlign = 'middle';
    placeholderLogDiv.style.width = '37px';
    placeholderLogDiv.style.height = '30px';
    placeholderLogDiv.style.marginTop = '-40px';
    placeholderLogDiv.setAttribute('id', 'placeholderLogDiv');
    toolbar.appendChild(placeholderLogDiv);
  }
  // 退出按钮
  if (!mode || mode == 'edit') {
    tb.addItem('退出', dir+'/simulation/system_exit.png', function(evt)
    {
      // mainEditorUi.menubarContainer.style.height = '30px';
      // mainEditorUi.menubarContainer.style.display = ''; // 恢复菜单
      // mainEditorUi.toolbarContainer.style.height = '38px';
      // mainEditorUi.toolbarContainer.style.display = ''; // 恢复工具栏
      // mainEditorUi.sidebarContainer.style.width = '240px';
      // mainEditorUi.sidebarContainer.style.display = ''; // 恢复节点面板
      // mainEditorUi.outlineContainer.style.width = '240px';
      // mainEditorUi.outlineContainer.style.display = ''; // 恢复缩略图面板
      // mainEditorUi.hsplit.style.display = ''; // 恢复拖拽条
      // mainEditorUi.toggleFormatPanel(false); // 恢复右侧参数面板
      graph.setTooltips(false); // 关闭提示
      wnd.setVisible(false); // 关闭弹出窗
      mainThat.clearWaitAll(); // 停止所有线程
      $('#timeTextDiv').remove(); // 删除时间层
      $('#examLogTable').remove(); // 删除日志表格
      $('#placeholderLogDiv').remove(); // 删除占位符
      mainThat.nodeClean(); // 清空节点历史记录
    });
  }
  // 工具条写入控制面板
  content.appendChild(toolbar);
  // 日志表格
  var LogDiv = document.createElement('div');
  LogDiv.style.borderTop = '1px solid rgb(169, 169, 169)';
  LogDiv.style.paddingTop = '5px';
  LogDiv.style.marginTop = '-3px';
  var DateTable = document.createElement('table');
  DateTable.style.width = '100%';
  DateTable.style.color = 'rgb(112, 112, 112)';
  DateTable.setAttribute('id', 'examLogTable');
  DateTable.setAttribute('class', 'display compact');
  LogDiv.appendChild(DateTable);
  content.appendChild(LogDiv);
  // 预览控制面板窗口
  var wnd = new mxWindow('预览控制面板', content, document.body.offsetWidth/3.5, 150, minWidth, 66, false);
  wnd.setMaximizable(false);
  wnd.setScrollable(false);
  wnd.setResizable(false);
  wnd.setClosable(false);
  wnd.setVisible(true);
  // 初始化表格
  $('#examLogTable').DataTable({
    dom: 'Bfrtip',
    buttons: [
      {extend: 'excel', text: '导出Escel', className: 'geBtn geInfoBtn geBtnMini2'},
      {extend: 'print', text: '打印', className: 'geBtn geInfoBtn geBtnMini2'}
    ],
    data: log,
    columns: [
      { title: '节点', data: 'node_name', width: 90 },
      { title: '条件类型', data: 'type', width: 60 },
      { title: '说明', data: 'note', orderable: false},
      { title: '时间', data: 'c_date', width: 120 }
    ],
    paging: true, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: true, // 是否允许Datatables开启本地搜索
    ordering: true, // 是否允许Datatables开启排序
    order: [[3, "desc"]], // 默认按时间倒序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  });
}
// 清空节点运行环境
function cleanExamRunNodesTemporaryData() {
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  // 清空记录
  for (var name in cells) {
    var element = cells[name];
    delete element['run']; // 删除属性
    // 清除提示图标
    graph.removeCellOverlays(element);
    // 修改线条样式,恢复成为普通线条
    element['edges'] && element['edges'].forEach(edge => {
      edge.setStyle('edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;');
      var state = graph.view.getState(edge);
      state.shape.node.getElementsByTagName('path')[0].setAttribute('visibility', 'hidden');
      state.shape.node.getElementsByTagName('path')[0].removeAttribute('stroke-width');
      state.shape.node.getElementsByTagName('path')[0].setAttribute('stroke', '#000000');
      state.shape.node.getElementsByTagName('path')[1].removeAttribute('class');
    });
    // 清除临时计算结果
    var dataArr = element['variableData'];
    if (dataArr) {
      for (var i = 0, l = dataArr.length; i < l; i++) {
        delete dataArr[i]['tempValue']; // 删除临时计算结果
      }
    }
    // 清除脉冲次数
    delete element['pulseCount'];
  }
}