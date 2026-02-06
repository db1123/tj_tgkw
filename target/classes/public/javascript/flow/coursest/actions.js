function myActionsCourse(that) {
  var ui = that.editorUi;
  var editor = ui.editor;
  var graph = editor.graph;

  /**
   * 新建按钮
   */
  that.addAction('courseDataNew...', function()
  {
    // 空面板XML字符串
    var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
    // 判断是否编辑过
    if (nowFlowCourseSaveOp === 1) {
      var dlg = new TipsDialog(ui, mxUtils.bind(this, function()
      {
        // 生成空白面板
        var doc = mxUtils.parseXml(emptyStr);
        editor.setGraphXml(doc.documentElement);
        // 清空参数项
        nowFlowCourseID = '';
        nowFlowCourseName = '';
        nowFlowCourseExplain = '';
        nowFlowCourseSaveOp = 1;
        // 生成课程图
        openTrainingProgramCourse(nowFlowCourseTpID);
      }), '是否放弃当前编辑内容吗?');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      // 生成空白面板
      var doc = mxUtils.parseXml(emptyStr);
      editor.setGraphXml(doc.documentElement);
      // 清空参数项
      nowFlowCourseID = '';
      nowFlowCourseName = '';
      nowFlowCourseExplain = '';
      nowFlowCourseSaveOp = 1;
      // 生成课程图
      openTrainingProgramCourse(nowFlowCourseTpID);
    }
  });

  /**
   * 打开按钮
   */
	that.addAction('courseDataOpen...', function()
	{
    // 判断是否需要提示保存
    if (nowFlowSaveOp == 1) { // 已改动
      var dlg = new TipsDialog(ui, mxUtils.bind(this, function()
      {
        var dlg = new FlowCourseTableDialog(ui);
        ui.showDialog(dlg.container, 1000, 500, true, true);
        dlg.init();
      }), '当前编辑内容尚未保存，如果打开其它流程图会使当前编辑内容丢失，是否继续?');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      var dlg = new FlowCourseTableDialog(ui);
      ui.showDialog(dlg.container, 1000, 500, true, true);
      dlg.init();
    }
  });

  /**
   * 保存按钮
   */
  that.addAction('courseDataSave', function() {
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
      alert(1)
      if (nowFlowCourseSaveOp == 1) { // 已改动
        if (nowFlowCourseID == '') { // 新建
          // 打开面板
          var dlg = new FlowCourseEditDialog(ui, mxUtils.bind(this, function()
          {
            alert(2)
            // 加载面板
            $("body").mLoading("show");
            // 延时执行
            setTimeout(function(){
              // 获取当前面板XML
              xmlString = mxUtils.getXml(encoder.encode(graph.getModel()));
              // 处理JSON字符串
              var jsonData = form2Json('flowCourseForm');
              jsonData['tpid'] = nowFlowCourseTpID;
              jsonData['f_xml'] = xmlString;
              jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
              // 添加数据
              $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/s/trainingprogramflow/addFlow', // 调用地址
                data: JSON.stringify(jsonData),
                async: false,
                success: function(data) {
                  $("body").mLoading("hide");
                  if (data.result == "success") {
                    nowFlowCourseID = data.key;
                    nowFlowCourseName = jsonData['f_name'];
                    nowFlowCourseExplain = jsonData['f_explain'];
                    nowFlowCourseSaveOp = 0;
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
            }, 100);
          }), 1);
          ui.showDialog(dlg.container, 310, 200, true, true);
          dlg.init();
        } else { // 修改
          // 加载面板
          $("body").mLoading("show");
          setTimeout(function(){
            // 获取当前面板XML
            xmlString = mxUtils.getXml(encoder.encode(graph.getModel()));
            // 生成参数
            var jsonData = {};
            jsonData['f_key_id'] = nowFlowCourseID;
            jsonData['f_name'] = nowFlowCourseName;
            jsonData['f_explain'] = nowFlowCourseExplain;
            jsonData['f_xml'] = xmlString;
            jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
            // 更新数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/trainingprogramflow/updateFlow', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  nowFlowCourseSaveOp = 0;
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
          }, 100);
        }
      }
    }
  });

  /**
   * 编辑保存
   */
  that.addAction('courseDataSaveEdit', function() {
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
      if (nowFlowCourseID == '') { // 新建
        // 打开面板
        var dlg = new FlowCourseEditDialog(ui, mxUtils.bind(this, function()
        {
          // 加载面板
          $("body").mLoading("show");
          setTimeout(function(){
            // 处理JSON字符串
            var jsonData = form2Json('flowCourseForm');
            jsonData['tpid'] = nowFlowCourseTpID;
            jsonData['f_xml'] = xmlString;
            jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
            // 添加数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/trainingprogramflow/addFlow', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  nowFlowCourseID = data.key;
                  nowFlowCourseName = jsonData['f_name'];
                  nowFlowCourseExplain = jsonData['f_explain'];
                  nowFlowCourseSaveOp = 0;
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
        ParameterJSON['f_name'] = nowFlowCourseName;
        ParameterJSON['f_explain'] = nowFlowCourseExplain;
        // 打开面板
        var dlg = new FlowCourseEditDialog(ui, mxUtils.bind(this, function()
        {
          // 加载面板
          $("body").mLoading("show");
          setTimeout(function(){
            // 处理JSON字符串
            var jsonData = form2Json('flowCourseForm');
            jsonData['f_key_id'] = nowFlowCourseID;
            jsonData['f_xml'] = xmlString;
            jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
            // 更新数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/trainingprogramflow/updateFlow', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  nowFlowCourseName = jsonData['f_name'];
                  nowFlowCourseExplain = jsonData['f_explain'];
                  nowFlowCourseSaveOp = 0;
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
  });

  /**
   * 另存为
   */
  that.addAction('courseDataSaveAs...', function() {
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
    } else if (nowFlowCourseID == '') {
      var dlg = new TipsDialog(ui, null, '数据未保存，无需另存为！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      // 打开面板
      var dlg = new FlowCourseEditDialog(ui, mxUtils.bind(this, function()
      {
        // 加载面板
        $("body").mLoading("show");
        setTimeout(function(){
          // 处理JSON字符串
          var jsonData = form2Json('flowCourseForm');
          jsonData['tpid'] = nowFlowCourseTpID;
          jsonData['f_xml'] = xmlString;
          jsonData['f_json'] = JSON.stringify(formatCells(graph.getModel()['cells']));
          // 添加数据
          $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/s/trainingprogramflow/addFlow', // 调用地址
            data: JSON.stringify(jsonData),
            async: false,
            success: function(data) {
              $("body").mLoading("hide");
              if (data.result == "success") {
                nowFlowCourseID = data.key;
                nowFlowCourseName = jsonData['f_name'];
                nowFlowCourseExplain = jsonData['f_explain'];
                nowFlowCourseSaveOp = 0;
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
}