// 打开指定自适应设计模型(纵向)
async function openTrainingProgramCourse(id) {
  var TrainingProgramCourseGroupJSON = {};
  // 获取流程图编辑器对象
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  // 允许连线
  graph.setConnectable(true);
  // 禁止悬空连线
  graph.setAllowDanglingEdges(false);
  // 设置默认连线样式为肘形路由（自动绕开节点）
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_EDGE] = mxEdgeStyle.ElbowConnector;
  // 关键配置：路由绕开的距离、是否考虑节点边界
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_SPACING] = 20; // 绕开节点的间距（像素）
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_ELBOW] = mxConstants.ELBOW_VERTICAL; // 纵向肘形（适配泳道纵向排列）
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_ROUNDED] = true; // 圆角转角（可选）
  // 生成空面板
  var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
  var doc = mxUtils.parseXml(emptyStr);
  editor.setGraphXml(doc.documentElement);
  // 获取根节点
  var parent = graph.getDefaultParent();
  //----------【第一步：读取数据】----------
  $('body').mLoading('show'); // 打开加载面板
  setTimeout(function(){
    $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
    $('.mloading-text').html('读取课程信息...');
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/trainingprogramcoursest/getttrainingprogramCourseData', // 调用地址
      data: JSON.stringify({
        FTPID: id
      }),
      async: false,
      success: function(data) {
        if (data.result == 'success') {
          setTimeout(function(){
            //----------【第二步：生成分组】----------
            $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
            $('.mloading-text').html('学期模块创建中...');
            graph.getModel().beginUpdate();
            try
            {
              // 生成学期
              for ( var i=1; i<=8; i++) {
                eval("var group_" + i + " = graph.insertVertex(parent, null, '第\\n" + i + "\\n学\\n期', 0, 0, 0, 0, \"swimlane;startSize=30;fontSize=18;direction=north;horizontal=1;spacing=20;swimlaneFillColor=none;rounded=0;shadow=0;sketch=0;glass=0;swimlaneLine=1;verticalAlign=middle;labelPosition=center;verticalLabelPosition=middle;align=center;flipH=0;flipV=0;swimlaneFillColor=#ffffff;dashed=1;\");");
              }
            } finally {
              graph.getModel().endUpdate();
            }
            setTimeout(function(){
              //----------【第三步：生成节点】----------
              $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
              $('.mloading-text').html('课程节点创建中...');
              graph.getModel().beginUpdate();
              try
              {
                var styleList = [
                  "rounded=1;whiteSpace=wrap;html=1;shadow=0;fillColor=#CCFFCC;strokeColor=#00994D;",
                  "rounded=1;whiteSpace=wrap;html=1;shadow=0;fillColor=#FFE6CC;strokeColor=#CC6600;",
                  "rounded=1;whiteSpace=wrap;html=1;shadow=0;fillColor=#E5CCFF;strokeColor=#6600CC;"
                ];
                var styleDict = {}; // 样式字典
                var nature_list = data.zjsonArray[0].nature_list;
                for (var i=0; i<nature_list.length; i++) {
                  styleDict[nature_list[i].id] = styleList[i];
                }
                var list = data.zjsonArray[0].graph_nodes;
                for (var i=0; i<list.length; i++) {
                  var item = list[i];
                  var nodeStyle = styleDict[item.FCNature];
                  var width = 120;
                  var height = 60;
                  // 创建节点
                  eval("var v" + item.FCourseID + " = graph.insertVertex(group_" + item.group + ", null, '<font style=\"font-size: 14px\"><b>" + item.FName + "</b></font>', 0, 0, " + width + ", " + height + ", '" + nodeStyle + "');");
                  eval("TrainingProgramCourseGroupJSON['" + item.FCourseID + "']=" + item.group + ";");
                }
              } finally {
                graph.getModel().endUpdate();
              }
              setTimeout(function(){
                //----------【第四步：生成关系连线】----------
                $('.mloading-text').html('节点关系连线生成中...');
                graph.getModel().beginUpdate();
                try
                {
                  // 生成关系
                  var relationship = data.zjsonArray[0].graph_relationships;
                  for (var i=0; i<relationship.length; i++) {
                    var item = relationship[i];
                    var lineStyle = "";
                    if ((TrainingProgramCourseGroupJSON[item.to] - TrainingProgramCourseGroupJSON[item.from]) > 1) {
                      lineStyle = "edgeStyle=entityRelationEdgeStyle;orthogonalLoop=1;jettySize=auto;html=1;elbow=vertical;rounded=1;";
                    } else {
                      lineStyle = "edgeStyle=elbowEdgeStyle;orthogonalLoop=1;jettySize=auto;html=1;elbow=vertical;rounded=1;";
                    }
                    eval(
                      "if(v" + item.from + "&&v" + item.to + "){" + 
                        "var e" + item.from + "_" + item.to + " = graph.insertEdge(parent, null, '', v" + item.from + ", v" + item.to + ", '" + lineStyle + "');" +
                        "graph.orderCells(true, [e" + item.from + "_" + item.to + "]);" +
                      "}"
                      );
                  }
                  // 将学期面板置于底层
                  graph.orderCells(true, [group_1,group_2,group_3,group_4,group_5,group_6,group_7,group_8], false);
                } finally {
                  graph.getModel().endUpdate();
                }
                setTimeout(function(){
                  //----------【第五步：对节点和关系连线进行布局】----------
                  $('.mloading-text').html('对节点和关系连线进行布局...');
                  graph.getModel().beginUpdate();
                  try
                  {
                    // 各学期布局
                    for ( var i=1; i<=8; i++) {
                      eval("var hierarchicalLayout_" + i + " = new mxHierarchicalLayout(graph);");
                      eval("hierarchicalLayout_" + i + ".resizeParent = true;"); // 调整父对象的大小
                      eval("hierarchicalLayout_" + i + ".parentBorder = 35;");
                      eval("hierarchicalLayout_" + i + ".execute(group_" + i + ");");
                    }
                    // 根节点对分组节点进行布局排版
                    var stackLayout_parent = new mxStackLayout(graph, false); // 纵向堆叠布局（适配泳道）
                    stackLayout_parent.horizontal = false; // 垂直
                    stackLayout_parent.spacing = 0; // 间距
                    stackLayout_parent.fill = true; // 子元素适用父元素的变化
                    stackLayout_parent.execute(parent);
                  } finally {
                    graph.getModel().endUpdate();
                  }
                  setTimeout(function(){
                    //----------【第六步：对泳道宽度进行调整】----------
                    $('.mloading-text').html('对泳道宽度进行调整...');
                    graph.getModel().beginUpdate();
                    try
                    {
                      var max_width = 0;
                      for ( var i=1; i<=8; i++) {
                        eval("var group_" + i + "_geom = group_" + i + ".getGeometry();");
                        eval("max_width = group_" + i + "_geom.width > max_width ? group_" + i + "_geom.width : max_width;");
                      }
                      for ( var i=1; i<=8; i++) {
                        eval("group_" + i + "_geom.width = max_width;");
                      }
                    } finally {
                      graph.getModel().endUpdate();
                    }
                    setTimeout(function(){
                      // 回执
                      $('body').mLoading('hide');
                      toastr.success('完成模型绘制！');
                    }, 500);
                  }, 1000);
                }, 1000);
              }, 1000); 
            }, 1000);
          }, 100);
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
  }, 100);
}

// 打开指定自适应设计模型(无泳道)
async function openTrainingProgramCourse2(id) {
  // 获取流程图编辑器对象
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  // 允许连线
  graph.setConnectable(true);
  // 禁止悬空连线
  graph.setAllowDanglingEdges(false);
  // 设置默认连线样式为肘形路由（自动绕开节点）
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_EDGE] = mxEdgeStyle.ElbowConnector;
  // 关键配置：路由绕开的距离、是否考虑节点边界
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_SPACING] = 20; // 绕开节点的间距（像素）
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_ELBOW] = mxConstants.ELBOW_VERTICAL; // 纵向肘形（适配泳道纵向排列）
  graph.getStylesheet().getDefaultEdgeStyle()[mxConstants.STYLE_ROUNDED] = true; // 圆角转角（可选）
  // 生成空面板
  var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
  var doc = mxUtils.parseXml(emptyStr);
  editor.setGraphXml(doc.documentElement);
  // 获取根节点
  var parent = graph.getDefaultParent();
  // 图例颜色
  var styleList = [
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#FFE6CC;strokeColor=#FFB366;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#FFFFCC;strokeColor=#CCCC00;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#E6FFCC;strokeColor=#66CC00;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#99FF99;strokeColor=#009900;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#CCFFFF;strokeColor=#00CCCC;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#CCCCFF;strokeColor=#6600CC;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#FFCCFF;strokeColor=#CC00CC;",
    "rounded=1;whiteSpace=wrap;html=1;fillColor=#FFCCE6;strokeColor=#99004D;"
  ];
  //----------【第一步：读取数据】----------
  $('body').mLoading('show'); // 打开加载面板
  setTimeout(function(){
    $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
    $('.mloading-text').html('读取课程信息...');
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/trainingprogramcoursest/getttrainingprogramCourseData', // 调用地址
      data: JSON.stringify({
        FTPID: id
      }),
      async: false,
      success: function(data) {
        if (data.result == 'success') {
          setTimeout(function(){
            //----------【第一步：生成布局和图例】----------
            $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
            $('.mloading-text').html('课程节点创建中...');
            graph.getModel().beginUpdate();
            try
            {
              // 布局
              var group_1 = graph.insertVertex(parent, null, '', 20, 10, 780, 30, "rounded=0;whiteSpace=wrap;html=1;dashed=1;dashPattern=1 1;");
              var group_2 = graph.insertVertex(parent, null, '', 20, 80, 0, 0, "rounded=0;whiteSpace=wrap;html=1;strokeColor=none;fillColor=none;");
              // 图例
              for (var i =0; i<8; i++) {
                eval("graph.insertVertex(group_1, null, '<font style=\"font-size: 14px\"><b>第" + (i + 1) + "学期</b></font>', " + (i*100+5) + ", 5, 70, 20, '" + styleList[i] + "');");
              }
            } finally {
              graph.getModel().endUpdate();
            }
            setTimeout(function(){
              //----------【第二步：生成节点】----------
              $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
              $('.mloading-text').html('课程节点创建中...');
              graph.getModel().beginUpdate();
              try
              {
                var list = data.zjsonArray[0].graph_nodes;
                for (var i=0; i<list.length; i++) {
                  var item = list[i];
                  var nodeStyle = styleList[item.group - 1];
                  var width = 120;
                  var height = 50;
                  // 创建节点
                  eval("var v" + item.FCourseID + " = graph.insertVertex(group_2, null, '<font style=\"font-size: 14px\"><b>" + item.FName + "</b></font><br /><font style=\"font-size: 12px\">(第<b>" + item.group + "</b>学期)</font>', 0, 0, " + width + ", " + height + ", '" + nodeStyle + "');");
                }
              } finally {
                graph.getModel().endUpdate();
              }
              setTimeout(function(){
                //----------【第三步：生成关系连线】----------
                $('.mloading-text').html('节点关系连线生成中...');
                graph.getModel().beginUpdate();
                try
                {
                  // 生成关系
                  var relationship = data.zjsonArray[0].graph_relationships;
                  for (var i=0; i<relationship.length; i++) {
                    var item = relationship[i];
                    eval(
                      "if(v" + item.from + "&&v" + item.to + "){" + 
                        "var e" + item.from + "_" + item.to + " = graph.insertEdge(group_2, null, '', v" + item.from + ", v" + item.to + ");" +
                        "graph.orderCells(true, [e" + item.from + "_" + item.to + "]);" +
                      "}"
                      );
                  }
                } finally {
                  graph.getModel().endUpdate();
                }
                setTimeout(function(){
                  //----------【第四步：对节点和关系连线进行布局】----------
                  $('.mloading-text').html('对节点和关系连线进行布局...');
                  graph.getModel().beginUpdate();
                  try
                  {
                    var hierarchicalLayout_2 = new mxHierarchicalLayout(graph); //分层布局
                    hierarchicalLayout_2.resizeParent = true; // 调整父对象的大小
                    hierarchicalLayout_2.execute(group_2);
                  } finally {
                    graph.getModel().endUpdate();
                  }
                  setTimeout(function(){
                    // 回执
                    $('body').mLoading('hide');
                    toastr.success('完成模型绘制！');
                  }, 500);
                }, 1000);
              }, 1000); 
            }, 1000);
          }, 1000);
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
  }, 100);
}