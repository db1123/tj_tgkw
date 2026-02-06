// 打开指定自适应设计模型
async function openAdaptiveDesign(id) {
  // 获取流程图编辑器对象
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  // 生成空面板
  var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
  var doc = mxUtils.parseXml(emptyStr);
  editor.setGraphXml(doc.documentElement);
  // 获取根节点
  var parent = graph.getDefaultParent();
  //----------【第一步：读取自适应设计模型】----------
  $('body').mLoading('show'); // 打开加载面板
  setTimeout(function(){
    $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
    $('.mloading-text').html('读取自适应设计模型...');
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/relations/yuanshijiuge', // 调用地址
      data: JSON.stringify({
        fmid: id
      }),
      async: false,
      success: function(data) {
        if (data.result == 'success') {
          setTimeout(function(){
            //----------【第二步：生成分组】----------
            $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
            $('.mloading-text').html('分组模块创建中...');
            graph.getModel().beginUpdate();
            try
            {
              // 生成分组 0功能树 1结构BOM 2参数
              var group_0 = graph.insertVertex(parent, null, '', 0, 0, 0, 0, "rounded=0;whiteSpace=wrap;html=1;strokeColor=none;fillColor=none;");
              group_0['classifyId'] = 'group';
              var group_2 = graph.insertVertex(parent, null, '', 0, 100, 0, 0, "rounded=0;whiteSpace=wrap;html=1;strokeColor=none;fillColor=none;");
              group_2['classifyId'] = 'group';
              var group_1 = graph.insertVertex(parent, null, '', 0, 200, 0, 0, "rounded=0;whiteSpace=wrap;html=1;strokeColor=none;fillColor=none;");
              group_1['classifyId'] = 'group';
            } finally {
              graph.getModel().endUpdate();
            }
            setTimeout(function(){
              //----------【第三步：生成节点】----------
              $('body').mLoading('show'); // 打开加载面板(解决偶尔会被别的线程关闭的问题)
              $('.mloading-text').html('模型节点创建中...');
              graph.getModel().beginUpdate();
              try
              {
                adaptiveDesignList = []; // 清空自适应设计全局变量
                adaptiveDesignJSON = {}; // 清空自适应设计全局变量
                var list = data.json.list;
                for (var i=0; i<list.length; i++) {
                  var item = list[i];
                  adaptiveDesignList.push(item);
                  // var side = SideSimulationArray.find((s)=> s.classifyId === item.type); // 自定义图标(废弃)
                  // var iconPath = 'image;html=1;labelBackgroundColor=#ffffff;image=' + side.path; // 自定义图标(废弃)
                  var nodeStyle = '';
                  var width = 100;
                  var height = 50;
                  switch (item.type) {
                    case 0: // 功能树
                      nodeStyle = 'rounded=1;whiteSpace=wrap;html=1;';
                      break;
                  
                    case 1: // 结构BOM
                      nodeStyle = 'shape=parallelogram;perimeter=parallelogramPerimeter;whiteSpace=wrap;html=1;';
                      break;
                
                    case 2: // 参数
                      if (item['Classify'] == 4) { // 公式
                        nodeStyle = 'rounded=0;whiteSpace=wrap;html=1;';
                      } else { // 其他
                        nodeStyle = 'ellipse;whiteSpace=wrap;html=1;aspect=fixed;';
                        width = 60;
                        height = 60;
                      }
                      break;
                        
                    default:
                      break;
                  }
                  // 初始化节点值
                  var value = '';
                  if (item['Classify']) {
                    switch (parseInt(item['Classify'])) {
                      case 1: // 普通参数
                        switch (parseInt(item['ValueType'])) {
                          case 1: // 无值
                            value = '';
                            break;
                        
                          case 2: // 固定值
                            value = item['FValue'];
                            break;
                      
                          case 3: // 区间
                            value = item['MinValue'];
                            break;
                    
                          case 4: // 输入值
                            value = '';
                            break;
                                  
                          default:
                            break;
                        }
                        break;
                
                      case 4: // 公式&约束
                        value = '';
                        break;
                
                      default:
                        break;
                    }
                  }
                  // 显示文本
                  var valueStr = createAdaptiveCellView(item, value);
                  // 创建节点
                  eval("var v" + item.id + " = graph.insertVertex(group_" + item.type + ", null, '" + valueStr + "', 0, 0, " + width + ", " + height + ", '" + nodeStyle + "');");
                  eval("v" + item.id + "['vertex'] = true;");
                  eval("v" + item.id + "['classifyId'] = 'adaptive" + item.type + "';");
                  eval("v" + item.id + "['adaptiveData'] = " + JSON.stringify(item) + ";");
                  eval("v" + item.id + "['adaptiveKey'] = '" + item.id + "';");
                  eval("v" + item.id + "['adaptiveValue'] = '" + value + "';");
                  eval("v" + item.id + "['adaptiveType'] = '" + item.type + "';");
                  eval("v" + item.id + "['adaptiveClassify'] = '" + item.Classify + "';");
                  eval("v" + item.id + "['adaptiveKosten'] = '" + item.Kosten + "';");
                  eval("v" + item.id + "['adaptiveBedeutung'] = '" + item.Bedeutung + "';");
                  eval("adaptiveDesignJSON['" + item.id + "']=v" + item.id + ".id;");
                  //eval("console.log(v" + item.id + ")");
                  // 创建标注
                  if (item.Subtitle) {
                    var note = item.Subtitle;
                    if (note.length > 10) {
                      note = note.substring(0, 10) + '...';
                    }
                    eval("var v" + item.id + "no = graph.insertVertex(v" + item.id + ", null, '" + note + "', 1, 1, 0, 0, 'align=left;verticalAlign=top;labelBackgroundColor=lightgrey;labelBorderColor=black', true);");
                    var LenPx = getLenPx(note, 12);
                    eval("v" + item.id + "no['classifyId'] = 'paramNote';");
                    if (width == 100) {
                      eval("v" + item.id + "no.geometry.offset = new mxPoint(" + (-53-(LenPx/2)) + ", -15);");
                    }
                    if (width == 60) {
                      eval("v" + item.id + "no.geometry.offset = new mxPoint(" + (-33-(LenPx/2)) + ", -20);");
                    }
                  }
                }
              } finally {
                graph.getModel().endUpdate();
              }
              setTimeout(function(){
                //----------【第四步：初步布局】----------
                $('.mloading-text').html('对节点进行初步布局...');
                graph.getModel().beginUpdate();
                try
                {
                  // 功能树布局
                  var stack_0 = new mxStackLayout(graph, true); //分层布局
                  stack_0.spacing = 10; // 间距
                  stack_0.execute(group_0);
                  // 参数布局
                  var stack_2 = new mxStackLayout(graph, true); //分层布局
                  stack_2.spacing = 10; // 间距
                  stack_2.execute(group_2);
                  // 结构BOM
                  var stack_1 = new mxStackLayout(graph, true); //分层布局
                  stack_1.spacing = 10; // 间距
                  stack_1.execute(group_1);
                } finally {
                  graph.getModel().endUpdate();
                }
                setTimeout(function(){
                  //----------【第五步：生成关系连线】----------
                  $('.mloading-text').html('节点关系连线生成中...');
                  graph.getModel().beginUpdate();
                  try
                  {
                    var relationship = data.json.relationship;
                    for (var i=0; i<relationship.length; i++) {
                      var item = relationship[i];
                      var eId = getRandomNum(1000, 99999);
                      var lineStyle = '';
                      if (item.abtype == 1) {
                        lineStyle = 'rounded=0;orthogonalLoop=1;jettySize=auto;html=1;endArrow=none;endFill=0;strokeWidth=2;';
                      } else {
                        lineStyle = 'edgeStyle=none;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;dashed=1;endArrow=none;endFill=0;strokeColor=#CCCCCC;strokeWidth=2;';
                      }
                      eval(
                        "if(v" + item.a + "&&v" + item.b + "){" + 
                          "var e" + eId + " = graph.insertEdge(parent, null, '', v" + item.a + ", v" + item.b + ",'" + lineStyle + "');" +
                          "graph.orderCells(true, [e" + eId + "]);" +
                        "}"
                        );
                    }
                  } finally {
                    graph.getModel().endUpdate();
                  }
                  setTimeout(function(){
                    //----------【第六步：设置功能树布局】----------
                    $('.mloading-text').html('对功能节点进行分析布局...');
                    graph.getModel().beginUpdate();
                    try
                    {
                      // 功能树布局
                      var hierarchicalLayout_0 = new mxHierarchicalLayout(graph); //分层布局
                      hierarchicalLayout_0.resizeParent = true; // 调整父对象的大小
                      hierarchicalLayout_0.execute(group_0);
                      // 根节点对分组节点进行布局排版
                      var stackLayout_parent = new mxStackLayout(graph, true); // 堆栈布局
                      stackLayout_parent.horizontal = false; // 垂直
                      stackLayout_parent.spacing = 100; // 间距
                      stackLayout_parent.execute(parent);
                    } finally {
                      graph.getModel().endUpdate();
                    }
                    setTimeout(function(){
                      //----------【第七步：设置参数布局】----------
                      $('.mloading-text').html('对参数进行分析布局...');
                      graph.getModel().beginUpdate();
                      try
                      {
                        // 参数布局
                        var hierarchicalLayout_2 = new mxHierarchicalLayout(graph); //分层布局
                        hierarchicalLayout_2.resizeParent = true; // 调整父对象的大小
                        hierarchicalLayout_2.execute(group_2);
                        // 根节点对分组节点进行布局排版
                        var stackLayout_parent = new mxStackLayout(graph, true); // 堆栈布局
                        stackLayout_parent.horizontal = false; // 垂直
                        stackLayout_parent.spacing = 100; // 间距
                        stackLayout_parent.execute(parent);
                      } finally {
                        graph.getModel().endUpdate();
                      }
                      setTimeout(function(){
                        //----------【第八步：设置结构BOM布局】----------
                        $('.mloading-text').html('对结构BOM进行分析布局...');
                        graph.getModel().beginUpdate();
                        try
                        {
                          // 结构BOM
                          var hierarchicalLayout_1 = new mxHierarchicalLayout(graph); //分层布局
                          hierarchicalLayout_1.resizeParent = true; // 调整父对象的大小
                          hierarchicalLayout_1.execute(group_1);
                          // 根节点对分组节点进行布局排版
                          var stackLayout_parent = new mxStackLayout(graph, true); // 堆栈布局
                          stackLayout_parent.horizontal = false; // 垂直
                          stackLayout_parent.spacing = 100; // 间距
                          stackLayout_parent.execute(parent);
                        } finally {
                          graph.getModel().endUpdate();
                        }
                        setTimeout(function(){
                          // 回执
                          $('body').mLoading('hide');
                          toastr.success('完成自适应模型设计图加载！');
                        }, 500);
                      }, 1000);
                    }, 1000);
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