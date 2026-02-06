function myActionsBasics(that) {
	var ui = that.editorUi;
	var editor = ui.editor;
	var graph = editor.graph;
  /**
   * 新建按钮
   */
  that.addAction('new...', function()
  {
    // 启用流程图编辑
    EditorUiEdit();
    // 空面板XML字符串
    var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
    // 获取当前面板XML
    var encoder = new mxCodec();
    var node = encoder.encode(graph.getModel());
    var xmlString = mxUtils.getXml(node);
    // 鼠标拖拽图层-关闭
    graph.panningHandler.ignoreCell = false;
    // 判断是否编辑过
    if (emptyStr != xmlString) {
      var dlg = new TipsDialog(ui, mxUtils.bind(this, function()
      {
        // 生成空白面板
        var doc = mxUtils.parseXml(emptyStr);
        editor.setGraphXml(doc.documentElement);
        // 清空参数项
        nowFlowID = '';
        nowFlowName = '';
        nowFlowExplain = '';
        nowFlowSaveOp = 0;
        nowFlowState = 0;
        saveXMLArray = []; // 顶级-二级-三级...
        // 恢复编辑视图
        EditorUiEdit();
        // 无需保存提交状态
        EditorMenuNoSaveNoSubmit();
      }), '是否放弃当前编辑内容吗?');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      // 生成空白面板
      var doc = mxUtils.parseXml(emptyStr);
      editor.setGraphXml(doc.documentElement);
      // 清空参数项
      nowFlowID = '';
      nowFlowName = '';
      nowFlowExplain = '';
      nowFlowSaveOp = 0;
      nowFlowState = 0;
      saveXMLArray = []; // 顶级-二级-三级...
      // 恢复编辑视图
      EditorUiEdit();
      // 无需保存提交状态
      EditorMenuNoSaveNoSubmit();
    }
  });

  /**
   * 打开按钮
   */
	that.addAction('open...', function()
	{
    // 判断是否需要提示保存
    if (nowFlowSaveOp == 1) { // 已改动
      var dlg = new TipsDialog(ui, mxUtils.bind(this, function()
      {
        var dlg = new FlowTableDialog(ui);
        ui.showDialog(dlg.container, 1000, 500, true, true);
        dlg.init();
      }), '当前编辑内容尚未保存，如果打开其它流程图会使当前编辑内容丢失，是否继续?');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      var dlg = new FlowTableDialog(ui);
      ui.showDialog(dlg.container, 1000, 500, true, true);
      dlg.init();
    }
  });

  /**
   * 保存按钮
   */
  that.addAction('save', function() {
    // 空面板XML字符串
    var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
    // 获取当前面板XML
    var encoder = new mxCodec();
    var node = encoder.encode(graph.getModel());
    var xmlString = mxUtils.getXml(node);
    // 判断是否可以保存
    if (emptyStr == xmlString) {
      var dlg = new TipsDialog(ui, null, '操作面板无数据，无法保存！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      if (nowFlowSaveOp == 1) { // 已改动
        if (nowFlowID == '') { // 新建
          // 打开面板
          var dlg = new FlowEditDialog(ui, mxUtils.bind(this, function()
          {
            // 加载面板
            $("body").mLoading("show");
            // 延时执行
            setTimeout(function(){
              var dateTemp = function() {
                var jsonData = form2Json('flowForm');
                jsonData['type'] = 2;
                return JSON.stringify(jsonData);
              } ();
              $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/s/flow/addFlow', // 调用地址
                data: dateTemp,
                async: false,
                success: function(data) {
                  if (data.result == "success") {
                    // 清空节点运行环境
                    cleanExamRunNodesTemporaryData();
                    // 保存前处理所有节点的附件
                    handleNodesFilesForUI();
                    // 获取当前面板XML
                    xmlString = mxUtils.getXml(encoder.encode(graph.getModel()));
                    // 判断是否处于子流程图中
                    if (saveXMLArray.length > 0) {
                      savaParent(); // 逐级保存修改内容
                      xmlString = saveXMLArray[0].xml;
                    }
                    // 处理JSON字符串
                    var jsonData = form2Json('flowForm');
                    jsonData['type'] = 1;
                    jsonData['f_type'] = 2;
                    jsonData['f_xml'] = xmlString;
                    jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
                    // 添加数据
                    $.ajax({
                      type: 'POST',
                      contentType: 'application/json',
                      url: '/s/flow/addFlow', // 调用地址
                      data: JSON.stringify(jsonData),
                      async: false,
                      success: function(data) {
                        $("body").mLoading("hide");
                        if (data.result == "success") {
                          nowFlowID = data.key;
                          nowFlowName = jsonData['f_name'];
                          nowFlowExplain = jsonData['f_explain'];
                          nowFlowSaveOp = 0;
                          nowFlowState = 0;
                          EditorMenuSubmit(); // 可提交状态 
                          toastr.success('保存成功！');
                          ui.hideDialog();
                        } else {
                          toastr.error(data.error);
                        }
                      },
                      error : function(e){
                        $("body").mLoading("hide");
                        toastr.error(e.status);
                        toastr.error(e.responseText);
                      }
                    });
                  } else {
                    $("body").mLoading("hide");
                    toastr.error(data.error);
                  }
                },
                error : function(e){
                  $("body").mLoading("hide");
                  toastr.error(e.status);
                  toastr.error(e.responseText);
                }
              });
            }, 100);
          }), 1);
          ui.showDialog(dlg.container, 310, 200, true, true);
          dlg.init();
        } else { // 修改
          // 加载面板
          $("body").mLoading("show");
          setTimeout(function(){
            var dateTemp = function() {
              var jsonData = form2Json('flowForm');
              jsonData['type'] = 2;
              jsonData['f_key_id'] = nowFlowID;
              jsonData['f_name'] = nowFlowName;
              return JSON.stringify(jsonData);
            } ();
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/flow/updateFlow', // 调用地址
              data: dateTemp,
              async: false,
              success: function(data) {
                if (data.result == "success") {
                  // 清空节点运行环境
                  cleanExamRunNodesTemporaryData();
                  // 保存前处理所有节点的附件
                  handleNodesFilesForUI();
                  // 获取当前面板XML
                  xmlString = mxUtils.getXml(encoder.encode(graph.getModel()));
                  // 判断是否处于子流程图中
                  if (saveXMLArray.length > 0) {
                    savaParent(); // 逐级保存修改内容
                    xmlString = saveXMLArray[0].xml;
                  }
                  // 生成参数
                  var jsonData = {};
                  jsonData['type'] = 1;
                  jsonData['f_type'] = 2;
                  jsonData['f_key_id'] = nowFlowID;
                  jsonData['f_name'] = nowFlowName;
                  jsonData['f_explain'] = nowFlowExplain;
                  jsonData['f_xml'] = xmlString;
                  jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
                  // 更新数据
                  $.ajax({
                    type: 'POST',
                    contentType: 'application/json',
                    url: '/s/flow/updateFlow', // 调用地址
                    data: JSON.stringify(jsonData),
                    async: false,
                    success: function(data) {
                      $("body").mLoading("hide");
                      if (data.result == "success") {
                        nowFlowSaveOp = 0;
                        nowFlowState = 0;
                        EditorMenuSubmit(); // 可提交状态 
                        toastr.success('保存成功！');
                      } else {
                        toastr.error(data.error);
                      }
                    },
                    error : function(e){
                      $("body").mLoading("hide");
                      toastr.error(e.status);
                      toastr.error(e.responseText);
                    }
                  });
                } else {
                  $("body").mLoading("hide");
                  toastr.error(data.error);
                }
              },
              error : function(e){
                $("body").mLoading("hide");
                toastr.error(e.status);
                toastr.error(e.responseText);
              }
            });
          }, 100);
        }
      }
    }
  });

  /**
   * 编辑保存
   */
  that.addAction('saveEdit', function() {
    // 空面板XML字符串
    var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
    // 获取当前面板XML
    var encoder = new mxCodec();
    var node = encoder.encode(graph.getModel());
    var xmlString = mxUtils.getXml(node);
    // 判断是否可以保存
    if (emptyStr == xmlString) {
      var dlg = new TipsDialog(ui, null, '操作面板无数据，无需保存！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      if (nowFlowSaveOp == 1) { // 已改动
        if (nowFlowID == '') { // 新建
          // 打开面板
          var dlg = new FlowEditDialog(ui, mxUtils.bind(this, function()
          {
            // 加载面板
            $("body").mLoading("show");
            setTimeout(function(){
              // 处理JSON字符串
              var jsonData = form2Json('flowForm');
              jsonData['f_type'] = 2;
              jsonData['f_xml'] = xmlString;
              jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
              // 添加数据
              $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/s/flow/addFlow', // 调用地址
                data: JSON.stringify(jsonData),
                async: false,
                success: function(data) {
                  $("body").mLoading("hide");
                  if (data.result == "success") {
                    nowFlowID = data.key;
                    nowFlowName = jsonData['f_name'];
                    nowFlowExplain = jsonData['f_explain'];
                    nowFlowSaveOp = 0;
                    nowFlowState = 0;
                    EditorMenuSubmit(); // 可提交状态 
                    toastr.success('保存成功！');
                    ui.hideDialog();
                  } else {
                    toastr.error(data.error);
                  }
                },
                error : function(e){
                  $("body").mLoading("hide");
                  toastr.error(e.status);
                  toastr.error(e.responseText);
                }
              });
            }, 200);
          }), 1);
          ui.showDialog(dlg.container, 310, 200, true, true);
          dlg.init();
        } else { // 修改
          var ParameterJSON = {};
          ParameterJSON['f_name'] = nowFlowName;
          ParameterJSON['f_explain'] = nowFlowExplain;
          // 打开面板
          var dlg = new FlowEditDialog(ui, mxUtils.bind(this, function()
          {
            // 加载面板
            $("body").mLoading("show");
            setTimeout(function(){
              // 处理JSON字符串
              var jsonData = form2Json('flowForm');
              jsonData['f_type'] = 2;
              jsonData['f_key_id'] = nowFlowID;
              jsonData['f_xml'] = xmlString;
              jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
              // 更新数据
              $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/s/flow/updateFlow', // 调用地址
                data: JSON.stringify(jsonData),
                async: false,
                success: function(data) {
                  $("body").mLoading("hide");
                  if (data.result == "success") {
                    nowFlowName = jsonData['f_name'];
                    nowFlowExplain = jsonData['f_explain'];
                    nowFlowSaveOp = 0;
                    nowFlowState = 0;
                    EditorMenuSubmit(); // 可提交状态 
                    toastr.success('保存成功！');
                    ui.hideDialog();
                  } else {
                    toastr.error(data.error);
                  }
                },
                error : function(e){
                  $("body").mLoading("hide");
                  toastr.error(e.status);
                  toastr.error(e.responseText);
                }
              });
            }, 200);
          }), 2, ParameterJSON);
          ui.showDialog(dlg.container, 310, 200, true, true);
          dlg.init();
        }
      }
    }
  });

  /**
   * 另存为
   */
  that.addAction('saveAs...', function() {
    // 空面板XML字符串
    var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
    // 获取当前面板XML
    var encoder = new mxCodec();
    var node = encoder.encode(graph.getModel());
    var xmlString = mxUtils.getXml(node);
    // 判断是否可以保存
    if (emptyStr == xmlString) {
      var dlg = new TipsDialog(ui, null, '操作面板无数据，无法另存为！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else if (nowFlowID == '') {
      var dlg = new TipsDialog(ui, null, '数据未保存，无需另存为！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      // 打开面板
      var dlg = new FlowEditDialog(ui, mxUtils.bind(this, function()
      {
        // 加载面板
        $("body").mLoading("show");
        setTimeout(function(){
          // 处理JSON字符串
          var jsonData = form2Json('flowForm');
          jsonData['f_type'] = 2;
          jsonData['f_xml'] = xmlString;
          jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
          // 添加数据
          $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/s/flow/addFlow', // 调用地址
            data: JSON.stringify(jsonData),
            async: false,
            success: function(data) {
              $("body").mLoading("hide");
              if (data.result == "success") {
                EditorUiEdit(); // 启用流程图编辑
                nowFlowID = data.key;
                nowFlowName = jsonData['f_name'];
                nowFlowExplain = jsonData['f_explain'];
                nowFlowSaveOp = 0;
                nowFlowState = 0;
                EditorUiEdit(); // 启用流程图编辑
                EditorMenuSubmit(); // 可提交状态
                graph.panningHandler.ignoreCell = false; // 鼠标拖拽图层-关闭
                toastr.success('保存成功！');
                ui.hideDialog();
              } else {
                toastr.error(data.error);
              }
            },
            error: function(e){
              $("body").mLoading("hide");
              toastr.error(e.status);
              toastr.error(e.responseText);
            }
          });
        }, 200);
      }), 1);
      ui.showDialog(dlg.container, 310, 200, true, true);
      dlg.init();
    }
  });

  /**
   * 提交按钮
   */
  that.addAction('submit', function() {
    // 空面板XML字符串
    var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
    // 获取当前面板XML
    var encoder = new mxCodec();
    var node = encoder.encode(graph.getModel());
    var xmlString = mxUtils.getXml(node);
    // 判断是否可以保存
    if (emptyStr == xmlString) {
      var dlg = new TipsDialog(ui, null, '操作面板无数据，无法提交！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      if (nowFlowID == '' || nowFlowSaveOp == 1) { // 新建 或 修改后未保存
        var dlg = new TipsDialog(ui, null, '数据尚未保存，无法提交！');
        ui.showDialog(dlg.container, 300, 90, true, true);
      } else { // 可提交
        var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
        {
          // 加载面板
          $('body').mLoading('show');
          setTimeout(function(){
            $.ajax({
              type: 'POST',
              contentType: 'application/json;charset=UTF-8',
              url: '/s/flow/stateFlow',
              data: JSON.stringify({
                id: nowFlowID,
                state: 0
              }),
              success: function(data) {
                $('body').mLoading('hide');
                if(data.result == 'success'){
                  nowFlowState = 1;
                  EditorUiSubmit(); // 禁止流程图编辑
                  EditorMenuNoSaveNoSubmit(); // 无需保存提交状态
                  toastr.success('提交成功！')
                }if(data.result == 'error') {
                  toastr.error(data.error)
                }
              },
              error: function(e){
                $('body').mLoading('hide');
                toastr.error(e.status);
                toastr.error(e.responseText);
              }
            });
          }, 200);
        }), '确定要提交当前流程图吗，提交后记录将无法修改?', 1);
        mainEditorUi.showDialog(dlg.container, 320, 80, true, true);
      }
    }
  });

  /**
   * 模拟运行
   */
  that.addAction('exam', function() {
    exam();
  });

  /**
   * 模拟工期
   */
  that.addAction('workTime', function() {
    var dlg = new WorkStartTimeDialog(mainEditorUi, mxUtils.bind(this, function()
    {
      // 加载面板
      $('body').mLoading('show');
      // 延时执行
      setTimeout(function(){
        var editor = mainEditorUi.editor;
        var graph = editor.graph;
        var mainXML;
        if (saveXMLArray.length > 0) {
          savaParent(); // 逐级保存修改内容
          mainXML = saveXMLArray[0].xml;
        } else {
          // 获取当前子流程图xml字符串
          var encoder = new mxCodec();
          var node = encoder.encode(graph.getModel());
          mainXML = mxUtils.getXml(node);
        }
        // 计算设置
        var xml = updateWorkTime(mainXML, $('#work_start_time').attr('date_value'));
        // 打开流程图
        editor.setGraphXml(xml.XML.documentElement);
        // 关闭遮罩
        $('body').mLoading('hide');
        toastr.success('计算完成！');
        mainEditorUi.hideDialog();
      }, 100);
    }));
    mainEditorUi.showDialog(dlg.container, 220, 82, true, true);
    dlg.init();
  });

  /**
   * 脚本浏览
   */
  that.addAction('script', function() {
    var dlg = new ScriptTableDialog(ui);
    ui.showDialog(dlg.container, 900, 500, true, true);
    dlg.init();
  });
}