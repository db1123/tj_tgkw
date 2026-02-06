// 获取可用的默认变量名
function getDefaultVariableName(table) {
  var returnStr = '';
  var data = table.fnGetData();
  for (var index=0; index<VariableName.length; index++) {
    var op = true;
    // 循环判断是否在参数表已经使用
    for (var i=0; i<data.length; i++) {
      var row = data[i];
      // 如果使用中就退出循环并进入下一个变量的比较
      if (row['f_variable_name'] == VariableName[index]) {
        op = false;
        break;
      }
    }
    // 如果没被赋值false则说明未遇到相同的值
    if (op) { // 通过则赋值并退出默认变量列表
      returnStr = VariableName[index];
      break;
    }
  }
  return returnStr;
}
// 检查参数是否重复
function checkParamName(table, key) {
  var op = true;
  var data = table.fnGetData();
  // 循环判断是否在参数表已经使用
  for (var i=0; i<data.length; i++) {
    var row = data[i];
    // 如果使用中就退出循环
    if (row['key'] == key) {
      op = false;
      break;
    }
  }
  return op;
}
// 检查变量名是否重复
function checkVariableName(table, variableName, nowRowKey) {
  var op = true;
  var data = table.fnGetData();
  // 循环判断是否在参数表已经使用
  for (var i=0; i<data.length; i++) {
    var row = data[i];
    // 如果使用中就退出循环
    if (row['f_variable_name'] == variableName) {
      if (nowRowKey) {
        if (nowRowKey !== row['key']) {
          op = false;
          break;
        }
      } else {
        op = false;
        break;
      }
    }
  }
  return op;
}
// 获取参数表格中的变量名称字符串
function getFunctionParamStrs(table) {
  var returnStr = '';
  var data = table.fnGetData();
  for (var i=0; i<data.length; i++) {
    var row = data[i];
    returnStr +=  ',' + row['f_variable_name'];
  }
  if (returnStr.length > 0) {
    returnStr = returnStr.substring(1);
  }
  return returnStr;
}
// 获取标准查询结果,返回JSON形式,例如：{cols:[列名1,列名2], returnData:[{列名1:数据,列名2:数据},...]}
function getStandardData(row, type) {
  var d; // 结果
  $.ajax({
    type: 'POST',
    contentType: 'application/json',
    url: '/s/param/queryGetSQLData', // 调用地址
    data: JSON.stringify({
      type,
      row
    }),
    async: false,
    success: function(data) {
      if (data.result == 'success') {
        d = data;
      } else if (data.result == 'error') {
        top.toastr.error(data.error);
      }
    },
    error : function(e){
      top.toastr.error(e.status);
      top.toastr.error(e.responseText);
    }
  });
  return d; // 返回结果
}
// 获取自定义查询结果,返回JSON形式,例如：{cols:[列名1,列名2], returnData:[{列名1:数据,列名2:数据},...]}
function getCustomSQLData(row, type) {
  var d; // 结果
  $.ajax({
    type: 'POST',
    contentType: 'application/json',
    url: '/s/param/queryGetSQLData', // 调用地址
    data: JSON.stringify({
      type,
      row
    }),
    async: false,
    success: function(data) {
      if (data.result == 'success') {
        d = data;
      } else if (data.result == 'error') {
        top.toastr.error(data.error);
      }
    },
    error : function(e){
      top.toastr.error(e.status);
      top.toastr.error(e.responseText);
    }
  });
  return d; // 返回结果
}
// 获取脚本执行结果
function getJSCodeData(row) {
  var d = {}; // 结果
  var models = row['models']; // 获取模型中的参数列表
  // 判断是否可以在数据库中获取脚本内容
  if (row['key']) {
    $.ajax({
      type: 'POST',
      contentType: 'application/json;charset=UTF-8',
      url: "/s/param/queryParamInfo",
      data: JSON.stringify({
        id: row['key']
      }),
      async: false, // false代表只有在等待ajax执行完毕后才执行后续代码
      success: function(data) {
        if (data.result == 'success') {
          if (!models) {
            models = data.info.models;
          }
        }
        if (data.result == 'error') {
          top.toastr.error(data.error)
        }
      },
      error: function(e){
        top.toastr.error(e.status);
        top.toastr.error(e.responseText);
      }
    });
  }
  // 变量名字符串
  var paramStrs = '';
  // 变量值字符串
  var valueStrs = '';
  // 计算
  var variableNames = []; // 变量名称(列表)
  var variableValues = {}; // 变量值(json格式)
  for (var i=0; i<models.length; i++) {
    variableNames.push(models[i]['f_variable_name']);
    switch (models[i]['f_classify']) {
      case 1: // 普通参数
        var type = models[i]['f_value_type'];
        var value = {};
        if (type == 2) { // 固定值
          value['returnData'] = models[i]['f_value'];
        } else if (type == 3) { // 区间
          value['min'] = models[i]['f_min_value'];
          value['max'] = models[i]['f_max_value'];
        }
        variableValues[models[i]['f_variable_name']] = value;
        break;

      case 2: // 标准查询
        variableValues[models[i]['f_variable_name']] = getStandardData(models[i], 1);
        break;
    
      case 3: // 自定义查询
        variableValues[models[i]['f_variable_name']] = getCustomSQLData(models[i], 1);
        break;
  
      case 4: // JS代码
        variableValues[models[i]['f_variable_name']] = getJSCodeData(models[i]);
        break;

      case 5: // 引用
        variableValues[models[i]['f_variable_name']] = getURLData(models[i]);
        break;
                    
      default:
        break;
    }
  }
  d['variableNames'] = variableNames;
  d['variableValues'] = variableValues;
  // 获取变量名字符串
  paramStrs = variableNames.join(",");
  // 获取变量值字符串
  if (variableNames && variableNames.length > 0) {
    for(i=0; i<variableNames.length; i++) {
      valueStrs += ',' + JSON.stringify(variableValues[variableNames[i]]);
    };
  }
  if (valueStrs.length > 0) {
    valueStrs = valueStrs.substring(1);
  }
  // 方法字符串
  var functionRunStr = '(function(' + paramStrs + '){' + row['f_calculation'] + '})(' + valueStrs + ')';
  // 执行
  var returnData = getJavaScriptRun(functionRunStr);
  d['returnData'] = returnData;
  // 返回结果
  return d;
}
// 获取引用执行结果
function getURLData(row) {
  var d = {}; // 结果
  d['returnData'] = row['f_url'];
  return d; // 返回结果
}
// 自动判断获取结果方式
function getAutoData(row) {
  var d;
  switch (row['f_classify']) {
    case 2: // 标准查询
      d = getStandardData(row, 1);
      break;
  
    case 3: // 自定义查询
      d = getCustomSQLData(row, 1);
      break;

    case 4: // JS代码
      d = getJSCodeData(row);
      break;

    case 5: // 引用
      d = getURLData(row);
      break;
                  
    default:
      break;
  }
  return d;
}
// 自动判断展示方式
function getAutoShow(row, data, place) {
  if (data) {
    var returnData = data.returnData;
    switch (row['f_classify']) {
      case 2: // 标准查询
      case 3: // 自定义查询
        place.layer.open({
          title: '查询结果展示',
          content: 
            '<div id="serverParamModel-json"></div>' +
            '<div class="Huialert Huialert-info">' +
              '<h5><i class="fas fa-info"></i> <b>单次运行时间:</b> <span id="serverParamModel-time"></span>毫秒</h5>' +
              '<div style="color: red; font-size: 8px; font-weight: 600;">' +
                '注：连续执行的情况下除首个参数获取较慢其它后续参数获取会比单次运行时间少1秒左右' +
              '</div>' +
            '</div>',
          area: ['800px', '400px'],
          scrollbar: true,
        });
        var json = {cols: data.cols, returnData};
        place.$('#serverParamModel-json').JSONView(json, {collapsed: true, nl2br: true});
        place.$('#serverParamModel-time').html(data.time);
        break;

      case 4: // JS代码
        var variableNames = data.variableNames;
        var variableValues = data.variableValues;
        place.layer.open({
          title: '执行结果展示',
          content: 
            '<div id="paramModal-param" class="row cl" style="margin-top: 0;"></div>' +
            '<div style="margin-top: 10px;">' +
              '<div class="Huialert Huialert-info">' +
                '<h5><i class="fas fa-info"></i> <b>返回值:</b></h5>' +
                '<div style="height: 100px; overflow-x: hidden; overflow-y: auto;">' +
                  '<div id="return-value"></div>' +
                '</div>' +
              '</div>' +
            '</div>',
          area: ['100%', '100%'],
          scrollbar: true,
        });
        // 参数列表面板
        for (var i=0; i<variableNames.length; i++) {
          var variableName = variableNames[i];
          place.$('#paramModal-param').append(
            '<div class="col-xs-12 col-sm-3">' +
              '<div class="panel panel-default">' +
                '<div class="panel-header"><i class="fas fa-globe"></i> ' + variableName + '</div>' +
                '<div class="panel-body" style="overflow-x: hidden; overflow-y: auto; height: 220px;">' +
                  '<div id="param-' + variableName + '"></div>' +
                '</div>' +
              '</div>' +
            '</div>'
          );
          place.$('#param-' + variableName).JSONView(variableValues[variableName], {collapsed: true, nl2br: true});
        }
        // 执行结果面板
        if (isJSON(returnData) == true) {
          place.$('#return-value').JSONView(returnData, {collapsed: true, nl2br: true});
        } else {
          place.$('#return-value').html(String(returnData));
        }
        break;

      case 5: // 引用
        place.layer.open({
          type: 2,
          area: ['1000px', '580px'],
          fix: false, //不固定
          shade: 0.4,
          title: '运行结果展示',
          content: returnData
        });
        break;
                    
      default:
        break;
    }
  }
}
// 执行JavaScript方法
function getJavaScriptRun(javascript) {
  // 执行方法
  var returnData;
  try {
    returnData = eval(javascript);
  } catch(err) {
    top.toastr.error(err.message);
  }
  // 返回结果
  return returnData;
}
// 获取脚本计算结果(兼容老版本系统)
function getCalculationRun(paramId) {
  var value;
  $.ajax({
    type: 'POST',
    contentType: 'application/json',
    url: '/s/param/queryParamInfo', // 调用地址
    data: JSON.stringify({
      id: paramId
    }),
    async: false,
    success: function(data) {
      value = getJSCodeData(data.info)['returnData'];
    },
    error : function(e) {
      toastr.error(e.status);
      toastr.error(e.responseText);
    }
  });
  return value;
}