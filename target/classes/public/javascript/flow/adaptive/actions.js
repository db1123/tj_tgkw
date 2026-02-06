function myActionsAdaptive(that) {
	var ui = that.editorUi;
	var editor = ui.editor;
	var graph = editor.graph;
  /**
   * 读取模型按钮
   */
  that.addAction('openModel...', function()
  {
    var dlg = new AdaptiveDesignTableDialog(ui);
    ui.showDialog(dlg.container, 1000, 500, true, true);
    dlg.init();
  });
 
  /**
   * 分步计算按钮
   */
  that.addAction('stepCount...', function()
  {
    var dlg = new AdaptiveDesignStepCountTableDialog(ui);
    ui.showDialog(dlg.container, 1000, 500, true, true);
    dlg.init();
  });

  /**
   * 打开保存结果按钮
   */
	that.addAction('modelDataOpen...', function()
	{
    // 判断是否需要提示保存
    if (nowFlowAdaptiveSaveDataSaveOp == 1) { // 已改动
      var dlg = new TipsDialog(ui, mxUtils.bind(this, function()
      {
        var dlg = new FlowAdaptiveSaveDataTableDialog(ui);
        ui.showDialog(dlg.container, 1000, 500, true, true);
        dlg.init();
      }), '当前编辑内容尚未保存，如果打开其它流程图会使当前编辑内容丢失，是否继续?');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      var dlg = new FlowAdaptiveSaveDataTableDialog(ui);
      ui.showDialog(dlg.container, 1000, 500, true, true);
      dlg.init();
    }
  });
 
  /**
   * 保存按钮
   */
  that.addAction('modelDataSave', function()
  {
    // 获取计算与设置参数
    var saveData = getAdaptiveSaveData();
    // 判断是否可以保存
    if ($.isEmptyObject(saveData) || nowFlowAdaptiveSaveDataSaveOp == 0) {
      var dlg = new TipsDialog(ui, null, '参数未设置任何数值，无法保存！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      if (nowFlowAdaptiveSaveDataSaveOp == 1) { // 已改动
        if (nowFlowAdaptiveSaveDataID == '') { // 新建
          // 打开面板
          var dlg = new FlowAdaptiveSaveDataEditDialog(ui, mxUtils.bind(this, function()
          {
            // 加载面板
            $("body").mLoading("show");
            // 延时执行
            setTimeout(function(){
              // 处理JSON字符串
              var jsonData = form2Json('flowAdaptiveSaveDataForm');
              jsonData['f_adaptive_design_id'] = nowFlowAdaptiveID;
              jsonData['f_data_json'] = JSON.stringify(saveData);
              // 添加数据
              $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/s/flow/addFlowAdaptiveSaveData', // 调用地址
                data: JSON.stringify(jsonData),
                async: false,
                success: function(data) {
                  $("body").mLoading("hide");
                  if (data.result == "success") {
                    nowFlowAdaptiveSaveDataID = data.key;
                    nowFlowAdaptiveSaveDataName = jsonData['f_name'];
                    nowFlowAdaptiveSaveDataMemo = jsonData['f_memo'];
                    nowFlowAdaptiveSaveDataSaveOp = 0;
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
            // 生成参数
            var jsonData = {};
            jsonData['f_key_id'] = nowFlowAdaptiveSaveDataID;
            jsonData['f_name'] = nowFlowAdaptiveSaveDataName;
            jsonData['f_memo'] = nowFlowAdaptiveSaveDataMemo;
            jsonData['f_data_json'] = JSON.stringify(saveData);
            // 更新数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/flow/updateFlowAdaptiveSaveData', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  nowFlowAdaptiveSaveDataSaveOp = 0;
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
  that.addAction('modelDataSaveEdit', function()
  {
    // 获取计算与设置参数
    var saveData = getAdaptiveSaveData();
    // 判断是否可以保存
    if ($.isEmptyObject(saveData)) {
      var dlg = new TipsDialog(ui, null, '参数未设置任何数值，无法保存！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      if (nowFlowAdaptiveSaveDataID == '') { // 新建
        // 打开面板
        var dlg = new FlowAdaptiveSaveDataEditDialog(ui, mxUtils.bind(this, function()
        {
          // 加载面板
          $("body").mLoading("show");
          setTimeout(function(){
            // 处理JSON字符串
            var jsonData = form2Json('flowAdaptiveSaveDataForm');
            jsonData['f_adaptive_design_id'] = nowFlowAdaptiveID;
            jsonData['f_data_json'] = JSON.stringify(saveData);
            // 添加数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/flow/addFlowAdaptiveSaveData', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  nowFlowAdaptiveSaveDataID = data.key;
                  nowFlowAdaptiveSaveDataName = jsonData['f_name'];
                  nowFlowAdaptiveSaveDataMemo = jsonData['f_memo'];
                  nowFlowAdaptiveSaveDataSaveOp = 0;
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
        ParameterJSON['f_name'] = nowFlowAdaptiveSaveDataName;
        ParameterJSON['f_memo'] = nowFlowAdaptiveSaveDataMemo;
        // 打开面板
        var dlg = new FlowAdaptiveSaveDataEditDialog(ui, mxUtils.bind(this, function()
        {
          // 加载面板
          $("body").mLoading("show");
          setTimeout(function(){
            // 处理JSON字符串
            var jsonData = form2Json('flowAdaptiveSaveDataForm');
            jsonData['f_key_id'] = nowFlowAdaptiveSaveDataID;
            jsonData['f_data_json'] = JSON.stringify(saveData);
            // 更新数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/flow/updateFlowAdaptiveSaveData', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  nowFlowAdaptiveSaveDataName = jsonData['f_name'];
                  nowFlowAdaptiveSaveDataMemo = jsonData['f_memo'];
                  nowFlowAdaptiveSaveDataSaveOp = 0;
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
  that.addAction('modelDataSaveAs...', function()
  {
    // 获取计算与设置参数
    var saveData = getAdaptiveSaveData();
    // 判断是否可以保存
    if ($.isEmptyObject(saveData)) {
      var dlg = new TipsDialog(ui, null, '参数未设置任何数值，无法保存！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else if (nowFlowAdaptiveSaveDataID == '') {
      var dlg = new TipsDialog(ui, null, '数据未保存，无需另存为！');
      ui.showDialog(dlg.container, 300, 90, true, true);
    } else {
      // 打开面板
      var dlg = new FlowAdaptiveSaveDataEditDialog(ui, mxUtils.bind(this, function()
      {
        // 加载面板
        $("body").mLoading("show");
        setTimeout(function(){
          // 处理JSON字符串
          var jsonData = form2Json('flowAdaptiveSaveDataForm');
          jsonData['f_adaptive_design_id'] = nowFlowAdaptiveID;
          jsonData['f_data_json'] = JSON.stringify(saveData);
          // 添加数据
          $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/s/flow/addFlowAdaptiveSaveData', // 调用地址
            data: JSON.stringify(jsonData),
            async: false,
            success: function(data) {
              $("body").mLoading("hide");
              if (data.result == "success") {
                nowFlowAdaptiveSaveDataID = data.key;
                nowFlowAdaptiveSaveDataName = jsonData['f_name'];
                nowFlowAdaptiveSaveDataMemo = jsonData['f_memo'];
                nowFlowAdaptiveSaveDataSaveOp = 0;
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