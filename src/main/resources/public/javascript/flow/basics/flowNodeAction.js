// 标注当前工作中节点
function markWorkNode(path) {
  var cell;
  saveXMLArray = [];
  for (var i=0; i<path.length; i++) {
    // 选中节点
    var graph = mainEditorUi.editor.graph;
    cell = findNodeById(path[i]);
    var highlight = new mxCellHighlight(graph, 'red', 6);
    highlight.highlight(graph.view.getState(cell));
    // 判断是否存在下级节点
    if (path[i+1]) {
      var encoder = new mxCodec();
      var node = encoder.encode(graph.getModel());
      var xml = mxUtils.getXml(node);
      saveXMLArray.push({
        nodeId: cell.id,
        xml: xml
      });
      openFlowXml(cell['subprocess']['subprocessXML']);
      // 生成返回按钮
      if (saveXMLArray.length == 1) {
        flowBack();
      }
    }
  }
  return cell;
}

/**
 * 递归获取祖先节点中的变量列表
 * cell：当前节点对象
 * 需要初始化的变量：
 * nowNodeInputParameterData = []; nowNodeInputParameterCount = {};
 *  */
function recursionFlowCellVariable(cell) {
  var cells = mainEditorUi.editor.graph.getModel().cells;
  var edges = cell['edges'];
  if (edges) {
    for (var i=0; i<edges.length; i++) {
      var source = edges[i]['source'];
      var target = edges[i]['target'];
      if (target.id == cell.id && source) {
        // 将源节点放入计数器
        if (nowNodeInputParameterCount[source.id]) { // 计数
          nowNodeInputParameterCount[source.id] = nowNodeInputParameterCount[source.id] + 1;
        } else { // 初始化
          nowNodeInputParameterCount[source.id] = 1;
        }
        if (nowNodeInputParameterCount[source.id] >= 3) return; // 出现超过三次直接结束方法
        // 下拉列表处理代码
        var variableData = source['variableData'];
        if (variableData) {
          var children = [];
          variableData.forEach((item) => {
            var cell = cells[item['f_node_id']];
            var nodeValue = cell && cell.value ? cell.value : '';
            children.push({
              id: item['f_id'],
              text: '【' + cleanHTML(nodeValue) + '】【' + (item['f_type'] == 1 ? '方法' : '常量') + '】' + item['f_name'],
              f_node_id: item['f_node_id']
            });
          });
          if (children.length > 0) {
            var op = true;
            nowNodeInputParameterData.forEach((item) => {
              if (item.id == source.id) {
                op = false;
              }
            });
            if (op) {
              nowNodeInputParameterData.push({
                id: source.id,
                text: cleanHTML(source.value),
                children: children
              });
            }
          }
        }
        recursionFlowCellVariable(source);
      }
    }
  }
}

/**
 * 递归获取祖先节点列表
 * cell：当前节点对象  nodeSelect：下拉列表组件
 * 需要初始化的变量：
 * nowNodeInputParameterCount = {};
 *  */
function recursionFlowCell (cell, nodeSelect) {
  var edges = cell['edges'];
  if (edges) {
    for (var i=0; i<edges.length; i++) {
      var source = edges[i]['source'];
      var target = edges[i]['target'];
      if (target.id == cell.id && source) {
        // 将源节点放入计数器
        if (nowNodeInputParameterCount[source.id]) { // 计数
          nowNodeInputParameterCount[source.id] = nowNodeInputParameterCount[source.id] + 1;
        } else { // 初始化
          nowNodeInputParameterCount[source.id] = 1;
        }
        if (nowNodeInputParameterCount[source.id] >= 3) return; // 出现超过三次直接结束方法
        // 下拉列表处理代码
        var op = true;
        if (nodeSelect) {
          for (var j = 0; j < nodeSelect.options.length; j++) {
            if (nodeSelect.options[j].value == source.id) {
              op = false;
            }
          }
        } else {
          nowNodeInputParameterData.forEach((item) => {
            if (item.id == source.id) {
              op = false;
            }
          });
        }
        if (op) {
          if (nodeSelect) {
            nodeSelect.options.add(new Option(cleanHTML(source.value), source.id));
          } else {
            nowNodeInputParameterData.push({
              id: source.id,
              text: cleanHTML(source.value)
            });
          }
        }
        recursionFlowCell(source, nodeSelect);
      }
    }
  }
}

/**
 * 获取方法计算结果
 * nowRow 变量表的某一行
 *  */
function getFunctionReturnValue(nowRow) {
  var cells = mainEditorUi.editor.graph.getModel().cells;
  var returnStr = '';
  if (nowRow['f_input_parameter']) {
    nowRow['f_input_parameter'].forEach((item)=>{
      var cell = cells[item.f_node_id];
      if (cell) {
        var dataArr = cell['variableData'];
        if (dataArr) {
          for (var i = 0, l = dataArr.length; i < l; i++) {
            var row = dataArr[i];
            if (row['f_id'] == item.f_variable_id) {
              if (row['f_type'] == 1) { // 方法
                if (row['f_return_type'] == 1) { // 数值
                  returnStr += ',' + getFunctionReturnValue(row);
                } else if (row['f_return_type'] == 2) { // 字符串
                  returnStr += ',"' + getFunctionReturnValue(row) + '"';
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
  } catch(err) {
    alert("运行异常：" + err.message);
  }
  return value;
}

/**
 * 测试方法执行结果
 * inputSelect：输入参数下拉列表  functionStr：方法字符串
 */
function testFunctionAction(inputSelect, functionStr) {
  var returnStr = '';
  var graph = mainEditorUi.editor.graph;
  var cells = graph.getModel().cells;
  inputSelect.select2('data').forEach((item)=>{
    var cell = cells[item.f_node_id];
    if (cell) {
      var dataArr = cell['variableData'];
      if (dataArr) {
        for (var i = 0, l = dataArr.length; i < l; i++) {
          var row = dataArr[i];
          if (row['f_id'] == item.id) {
            if (row['f_type'] == 1) { // 方法
              if (row['f_return_type'] == 1) { // 数值
                returnStr += ',' + getFunctionReturnValue(row);
              } else if (row['f_return_type'] == 2) { // 字符串
                returnStr += ',"' + getFunctionReturnValue(row) + '"';
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
  if (returnStr.length > 0) {
    returnStr = returnStr.substring(1);
  }
  try {
    alert(eval("(" + functionStr + ")(" + returnStr + ")"));
  } catch(err) {
    alert("运行异常：" + err.message);
  }
}