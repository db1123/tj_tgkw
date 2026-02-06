// 打开指定流程图(原始)
async function openFlowGraph(id) {
  if (id == null) {
    cleanFlowGraph();
  } else {
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/s/flow/queryFlowInfo', // 调用地址
        data: JSON.stringify({
          id: id
        }),
        async: false,
        success: function(data) {
          $('body').mLoading('hide');
          if (data.result == 'success') {
            openFlowXml(data['info']['f_xml']);
            toastr.success('加载成功！');
          } else {
            toastr.error(data.error);
          }
        },
        error : function(e){
          $('body').mLoading('hide');
          toastr.error(e.status);
          toastr.error(e.responseText);
        }
      });
    }, 200);
  }
}

// 打开指定流程图(项目-工作节点)
async function openProjectFlowGraph(projectId, path) {
  if (projectId == null) {
    cleanFlowGraph();
  } else {
    var cell;
    $('body').mLoading('show');
    await sleep(500);
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/projectFlowCell/queryProjectFlowCellInfo', // 调用地址
      data: JSON.stringify({
        id: projectId
      }),
      async: false,
      success: function(data) {
        $('body').mLoading('hide');
        if (data.result == 'success') {
          openFlowXml(data['info']['MainXML']);
          if (path) {
            projectPath = path;
            cell = markWorkNode(path);
          }
          toastr.success('加载成功！');
        } else {
          toastr.error(data.error);
        }
      },
      error : function(e){
        $('body').mLoading('hide');
        toastr.error(e.status);
        toastr.error(e.responseText);
      }
    });
    return cell;
  }
}

// 打开指定流程图(项目-所有节点状态)
async function openProjectFlowGraphAll(projectId) {
  if (projectId == null) {
    cleanFlowGraph();
  } else {
    $('body').mLoading('show');
    await sleep(500);
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/projectFlowCell/queryProjectFlowCellInfo', // 调用地址
      data: JSON.stringify({
        id: projectId
      }),
      async: false,
      success: function(data) {
        $('body').mLoading('hide');
        if (data.result == 'success') {
          openFlowXml(data['info']['MainXML']);
          toastr.success('加载成功！');
        } else {
          toastr.error(data.error);
        }
      },
      error : function(e){
        $('body').mLoading('hide');
        toastr.error(e.status);
        toastr.error(e.responseText);
      }
    });
  }
}

// 保存流程图信息
function saveProjcetFlow(op, data) {
  // 清空节点运行环境
  cleanExamRunNodesTemporaryData();
  // 保存前处理所有节点的附件
  handleNodesFilesForUI();
  // 保存流程图
  var returnMgs = '';
  var graph = mainEditorUi.editor.graph;
  var encoder = new mxCodec();
  var node = encoder.encode(graph.getModel());
  var xmlString = mxUtils.getXml(node);
  var jsonData = data;
  jsonData['type'] = 1;
  jsonData['f_xml'] = xmlString;
  jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
  if (op == 1) { // 新建
    // 添加数据
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/flow/addFlow', // 调用地址
      data: JSON.stringify(jsonData),
      async: false,
      success: function(data) {
        if (data.result == "success") {
          returnMgs = 'ok';
        } else {
          returnMgs = data.error;
        }
      },
      error : function(e){
        returnMgs = e.responseText;
      }
    });
  } else { // 修改
    // 更新数据
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/flow/updateFlow', // 调用地址
      data: JSON.stringify(jsonData),
      async: false,
      success: function(data) {
        if (data.result == "success") {
          returnMgs = 'ok';
        } else {
          returnMgs = data.error;
        }
      },
      error : function(e){
        returnMgs = e.responseText;
      }
    });
  }
  return returnMgs;
}

// 保存前处理所有节点的附件
function handleNodesFilesForUI() {
  var cells = mainEditorUi.editor.graph.getModel()['cells'];
  for(var id in nowNodeFileUploatSteps) {
    var cell = cells[id] // 获取节点
    var steps = nowNodeFileUploatSteps[id]; // 操作步骤
    if(steps['add'].length > 0) { // 添加
      var addList = steps['add'];
      for (var i = 0, l = addList.length; i < l; i++) {
        var addItem = addList[i]; // 添加对象
        $.ajax({
          url: '/s/flow/addNodeFile',
          type: 'POST',
          data: addItem['form'],
          async: false,
          cache: false,
          processData: false,
          contentType: false,
          success: function(data) {
            var d = JSON.parse(data);
            var dataArr = cell['fileUploadData'];
            for (var k = 0, j = dataArr.length; k < j; k++) {
              var row = dataArr[k];
              if (row['f_id'] == addItem['id']) { // 判断是否为编辑行
                row['f_path'] = d.path;
                row['f_filename'] = d.filename;
                row['f_ext'] = d.ext;
                cell['fileUploadData'].splice(k, 1, row);
              }
            }
          },
          error: function(e) {
            toastr.error(e.status);
            toastr.error(e.responseText);
          }
        });
      }
    }
    if(steps['update'].length > 0) { // 修改
      var updateList = steps['update'];
      for (var i = 0, l = updateList.length; i < l; i++) {
        var updateItem = updateList[i]; // 修改对象
        // 获取修改记录
        var updateRow, index = -1;
        var dataArr = cell['fileUploadData'];
        for (var k = 0, j = dataArr.length; k < j; k++) {
          index = k;
          var row = dataArr[k];
          if (row['f_id'] == updateItem['id']) { // 判断是否为编辑行
            updateRow = row;
            break;
          }
        }
        if (updateRow) {
          var form = updateItem['form'];
          form.append('f_path', updateRow['f_path']);
          $.ajax({
            url: '/s/flow/updateNodeFile',
            type: 'POST',
            data: form,
            async: false,
            cache: false,
            processData: false,
            contentType: false,
            success: function(data) {
              var d = JSON.parse(data);
              updateRow['f_path'] = d.path;
              updateRow['f_filename'] = d.filename;
              updateRow['f_ext'] = d.ext;
              cell['fileUploadData'].splice(index, 1, updateRow);
            },
            error: function(e) {
              toastr.error(e.status);
              toastr.error(e.responseText);
            }
          });
        }
      }
    }
    if(steps['del'].length > 0) { // 删除
      var delList = steps['del'];
      for (var i = 0, l = delList.length; i < l; i++) {
        var delItem = delList[i]; // 删除对象
        var row = delItem['row']; // 删除行数据
        $.ajax({
          type: 'POST',
          contentType: 'application/json;charset=UTF-8',
          url: '/s/flow/delNodeFile',
          data: JSON.stringify({
            f_path: row['f_path']
          }),
          async: false,
          success: function(data) {
            if(data.result == 'success'){
              // 无操作
            }if(data.result == 'error') {
              toastr.error(data.error)
            }
          },
          error: function(e){
            toastr.error(e.status);
            toastr.error(e.responseText);
          }
        });
      }
    }
  }
  // 清空所有节点操作步骤
  nowNodeFileUploatSteps = {};
}
// function handleNodesFilesForXML(xmlString) {
//   var xml = $.parseXML(xmlString);
//   for(var id in nowNodeFileUploatSteps) {
//     var steps = nowNodeFileUploatSteps[id]; // 操作步骤
//     if(steps['add'].length > 0) { // 添加
//       var addList = steps['add'];
//       for (var i = 0, l = addList.length; i < l; i++) {
//         var addItem = addList[i]; // 添加对象
//         $.ajax({
//           url: '/s/flow/addNodeFile',
//           type: 'POST',
//           data: addItem['form'],
//           async: false,
//           cache: false,
//           processData: false,
//           contentType: false,
//           success: function(data) {
//             var d = JSON.parse(data);
//             $(xml).find('mxCell[id="' + id + '"]').find('Array[as=fileUploadData]').find('Object').each(function() {
//               var $this = $(this); // 当前标签
//               if ($this.attr('f_id') == addItem['id']) { // 判断是否为编辑行
//                 $this.attr('f_path', d.path);
//                 $this.attr('f_filename', d.filename);
//                 $this.attr('f_ext', d.ext);
//               }
//             });
//           },
//           error: function(e) {
//             $('body').mLoading('hide');
//             toastr.error(e.status);
//             toastr.error(e.responseText);
//           }
//         });
//       }
//     }
//     if(steps['update'].length > 0) { // 修改
//       var updateList = steps['update'];
//       for (var i = 0, l = updateList.length; i < l; i++) {
//         var updateItem = updateList[i]; // 修改对象
//         // 获取修改记录
//         var updateRow;
//         $(xml).find('mxCell[id="' + id + '"]').find('Array[as=fileUploadData]').find('Object[f_id='+updateItem['id']+']').each(function() {
//           updateRow = $(this); // 当前标签
//         });
//         if (updateRow) {
//           var form = updateItem['form'];
//           form.append('f_path', updateRow['f_path']);
//           $.ajax({
//             url: '/s/flow/updateNodeFile',
//             type: 'POST',
//             data: form,
//             async: false,
//             cache: false,
//             processData: false,
//             contentType: false,
//             success: function(data) {
//               var d = JSON.parse(data);
//               updateRow.attr('f_path', d.path);
//               updateRow.attr('f_filename', d.filename);
//               updateRow.attr('f_ext', d.ext);
//             },
//             error: function(e) {
//               toastr.error(e.status);
//               toastr.error(e.responseText);
//             }
//           });
//         }
//       }
//     }
//     if(steps['del'].length > 0) { // 删除
//       var delList = steps['del'];
//       for (var i = 0, l = delList.length; i < l; i++) {
//         var delItem = delList[i]; // 删除对象
//         var row = delItem['row']; // 删除行数据
//         $.ajax({
//           type: 'POST',
//           contentType: 'application/json;charset=UTF-8',
//           url: '/s/flow/delNodeFile',
//           data: JSON.stringify({
//             f_path: row['f_path']
//           }),
//           async: false,
//           success: function(data) {
//             if(data.result == 'success'){
//               // 无操作
//             }if(data.result == 'error') {
//               toastr.error(data.error)
//             }
//           },
//           error: function(e){
//             toastr.error(e.status);
//             toastr.error(e.responseText);
//           }
//         });
//       }
//     }
//   }
//   return mxUtils.getXml(xml.documentElement);
// }