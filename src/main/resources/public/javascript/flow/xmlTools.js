/**
 * 获取当前工作流的所有节点工作状态
 * @param {当前工作节点基本信息} info 
 * @returns 
 */
function GetFlowNodesState(info) {
  var nowFlowNodesState = {}; // 1启动 2完成 3暂停 4终止
  $.ajax({
    type: 'POST',
    contentType: 'application/json;charset=UTF-8',
    url: '/s/projectFlowCell/queryProjectFlowCellState',
    async: false,
    data: JSON.stringify({
      f_type: info.f_type,
      f_project_id: info.f_project_id,
      f_task_id: info.f_task_id,
      f_subdata_id: info.f_subdata_id,
      f_abnormal_id: info.f_abnormal_id
    }),
    success: function (data) {
      if (data.result == 'success') {
        nowFlowNodesState = data.list;
      }
      if (data.result == 'error') {
        toastr.error(data.error);
      }
    },
    error: function (e) {
      toastr.error(e.status);
      toastr.error(e.responseText);
    }
  });
  return nowFlowNodesState;
}
/**
 * 获取指定ID节点
 * @param {当前工作节点基本信息} info 
 * @param {参数所属节点id} nodeId 
 * @returns 
 */
function GetNode(info, nodeId) {
  var node;
  $(info.MainXML).find('mxCell[id=' + nodeId + ']').each(function() {
    node = $(this);
  });
  return node;
}
/**
 * 获取值(选择器)
 * @param {当前工作节点基本信息} info 
 * @param {变量所属节点id} nodeId 
 * @param {变量id} id 
 * @returns 
 */
function GetNodeValue(info, nodeId, id) {
  var obj;
  $(info.MainXML).find('mxCell[id=' + nodeId + ']').find('Object[f_id=' + id + ']').each(function() {
    obj = $(this);
  });
  var value;
  if (obj) {
    if (obj.attr('f_type') == 1) { // 方法
      value = GetNodeActionValue(info, nodeId, id);
    }
    if (obj.attr('f_type') == 2) { // 变量
      value = GetNodeVariableValue(info, nodeId, id);
    }
  }
  return value;
}
/**
 * 获取节点变量值
 * @param {当前工作节点基本信息} info 
 * @param {变量所属节点id} nodeId 
 * @param {变量id} id 
 * @returns 
 */
function GetNodeVariableValue(info, nodeId, id) {
  var value;
  $(info.MainXML).find('mxCell[id=' + nodeId + ']').find('Object[f_id=' + id + ']').each(function() {
    var obj = $(this);
    value = { value:obj.attr('f_value'), str: obj.attr('f_return_type') == 1 ? obj.attr('f_value') : '"' + obj.attr('f_value') + '"' };
  });
  return value;
}
/**
 * 获取节点变量方法计算结果
 * @param {当前工作节点基本信息} info 
 * @param {变量所属节点id} nodeId 
 * @param {变量id} id 
 * @returns 
 */
function GetNodeActionValue(info, nodeId, id) {
  var obj;
  $(info.MainXML).find('mxCell[id=' + nodeId + ']').find('Object[f_id=' + id + ']').each(function() {
    obj = $(this);
  });
  var returnValue;
  if (obj) {
    // 获取方法主体
    var f_function = obj.attr('f_function');
    // 获取方法参数
    var returnStr = '';
    obj.find('Array[as=f_input_parameter]').find('Object').each(function() {
      var parameter = $(this);
      var value = GetNodeValue(info, parameter.attr('f_node_id'), parameter.attr('f_variable_id'));
      if (value) {
        returnStr += ',' + value.str;
      } else {
        returnStr += ',null';
      }
    });
    if (returnStr.length > 0) {
      returnStr = returnStr.substring(1);
    }
    // 执行方法
    returnValue = eval("(" + f_function + ")(" + returnStr + ")");
    returnValue = { value:returnValue, str: obj.attr('f_return_type') == 1 ? returnValue : '"' + returnValue + '"' };
  }
  return returnValue;
}

/**
 * 验证节点是否可以启动
 * @param {当前工作节点基本信息} info 
 * @param {变量所属节点id} nodeId 
 * @returns 
 */
 function StartCheckNodeGo(info, nodeId) {
  // 获取所有节点工作状态
  var nowFlowNodesState = GetFlowNodesState(info);
  // 获取节点
  var nowNode = GetNode(info, nodeId);
  // 获取节点完成条件
  var go = true;
  nowNode && nowNode.find('Array[as=nodeStartData]').find('Object').each(function() {
    var nodeStartDataRow = $(this);
    if (nodeStartDataRow.attr('f_type') == 1) { // 节点
      if (nowFlowNodesState[nodeStartDataRow.attr('f_node')] != nodeStartDataRow.attr('f_node_state')) {
        go = false;
      }
    }
    if (nodeStartDataRow.attr('f_type') == 2) { // 方法
      var f_function = nodeStartDataRow.attr('f_function');
      var returnStr = '';
      nodeStartDataRow.find('Array[as=f_input_parameter]').find('Object').each(function() {
        var inputParameterRow = $(this);
        var value = GetNodeValue(info, inputParameterRow.attr('f_node_id'), inputParameterRow.attr('f_variable_id'));
        returnStr += ',' + value.str;
      });
      if (returnStr.length > 0) {
        returnStr = returnStr.substring(1);
      }
      try {
        if (!eval("(" + f_function + ")(" + returnStr + ")")) {
          go = false;
        }
      } catch(err) {
        go = false;
        toastr.error(err.message);
      }
    }
  });
  return go;
}

/**
 * 验证节点是否可以完成
 * @param {当前工作节点基本信息} info 
 * @param {变量所属节点id} nodeId 
 * @returns 
 */
function EndCheckNodeGo(info, nodeId) {
  // 获取所有节点工作状态
  var nowFlowNodesState = GetFlowNodesState(info);
  // 获取节点
  var nowNode = GetNode(info, nodeId);
  // 获取节点完成条件
  var go = true;
  nowNode && nowNode.find('Array[as=nodeEndData]').find('Object').each(function() {
    var nodeEndDataRow = $(this);
    if (nodeEndDataRow.attr('f_type') == 1) { // 节点
      if (nowFlowNodesState[nodeEndDataRow.attr('f_node')] != nodeEndDataRow.attr('f_node_state')) {
        go = false;
      }
    }
    if (nodeEndDataRow.attr('f_type') == 2) { // 方法
      var f_function = nodeEndDataRow.attr('f_function');
      var returnStr = '';
      nodeEndDataRow.find('Array[as=f_input_parameter]').find('Object').each(function() {
        var inputParameterRow = $(this);
        var value = GetNodeValue(info, inputParameterRow.attr('f_node_id'), inputParameterRow.attr('f_variable_id'));
        returnStr += ',' + value.str;
      });
      if (returnStr.length > 0) {
        returnStr = returnStr.substring(1);
      }
      try {
        if (!eval("(" + f_function + ")(" + returnStr + ")")) {
          go = false;
        }
      } catch(err) {
        go = false;
        toastr.error(err.message);
      }
    }
  });
  return go;
}