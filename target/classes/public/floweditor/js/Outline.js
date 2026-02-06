function Outline(editorUi, container)
{
	this.editorUi = editorUi;
	this.container = container;
  this.init();
};

Outline.prototype.init = function()
{
  var editorUi = this.editorUi;
  var graph = editorUi.editor.graph;
  // 图标
  var i = document.createElement('i');
  i.style.paddingRight = '5px';
  i.setAttribute('class', 'fas fa-map-marked-alt');
  // 标题
  var tittleDiv = document.createElement('div');
  tittleDiv.style.display = 'block';
  tittleDiv.style.height = '26px';
  tittleDiv.style.paddingTop = '6px';
  tittleDiv.style.paddingLeft = '10px';
  tittleDiv.style.backgroundColor = '#efefef';
  tittleDiv.style.borderTop = '1px solid #dadce0';
  tittleDiv.style.borderBottom = '1px solid #dadce0';
  tittleDiv.appendChild(i);
	mxUtils.write(tittleDiv, '缩略图');
  this.container.appendChild(tittleDiv);
  // 缩略图面板
	var div = document.createElement('div');
	div.style.display = 'block';
	div.style.position = 'absolute';
	div.style.width = '100%';
	div.style.height = '140px';
	div.style.border = '1px solid whiteSmoke';
	div.style.overflow = 'hidden';
  this.container.appendChild(div);
  // 缩略图组件
	var outline = editorUi.createOutline();
  // 缩略图面板内创建绘图区域
	var outlineCreateGraph = outline.createGraph;
	outline.createGraph = function(container)
	{
		var g = outlineCreateGraph.apply(this, arguments);
		g.gridEnabled = false;
		g.pageScale = graph.pageScale;
		g.pageFormat = graph.pageFormat;
		g.background = (graph.background == null || graph.background == mxConstants.NONE) ? graph.defaultPageBackgroundColor : graph.background;
		g.pageVisible = graph.pageVisible;

		var current = mxUtils.getCurrentStyle(graph.container);
		div.style.backgroundColor = isNull(current) ? graph.defaultPageBackgroundColor : current.backgroundColor;
		
		return g;
	};
	// 更新缩略图面板绘图区域
	function update()
	{
		outline.outline.pageScale = graph.pageScale;
		outline.outline.pageFormat = graph.pageFormat;
		outline.outline.pageVisible = graph.pageVisible;
		outline.outline.background = (graph.background == null || graph.background == mxConstants.NONE) ? graph.defaultPageBackgroundColor : graph.background;;
		
		var current = mxUtils.getCurrentStyle(graph.container);
		div.style.backgroundColor = current.backgroundColor;

		if (graph.view.backgroundPageShape != null && outline.outline.view.backgroundPageShape != null)
		{
			outline.outline.view.backgroundPageShape.fill = graph.view.backgroundPageShape.fill;
		}
		
		outline.outline.refresh();
  };
  // 延时执行初始化操作
  setTimeout(function(){
    // 将缩略图组件初始化到div内
    outline.init(div);
    // 绑定组件改变事件
    editorUi.editor.addListener('resetGraphView', update);
    editorUi.addListener('pageFormatChanged', update);
    editorUi.addListener('backgroundColorChanged', update);
    editorUi.addListener('backgroundImageChanged', update);
    editorUi.addListener('pageViewChanged', function()
    {
      update();
      outline.update(true);
    });
    // 判断放大缩小变化
    if (outline.outline.dialect == mxConstants.DIALECT_SVG)
    {
      var zoomInAction = editorUi.actions.get('zoomIn');
      var zoomOutAction = editorUi.actions.get('zoomOut');
      mxEvent.addMouseWheelListener(function(evt, up)
      {
        var outlineWheel = false;
        var source = mxEvent.getSource(evt);
        while (source != null)
        {
          if (source == outline.outline.view.canvas.ownerSVGElement)
          {
            outlineWheel = true;
            break;
          }
          source = source.parentNode;
        }
        if (outlineWheel)
        {
          if (up)
          {
            zoomInAction.funct();
          }
          else
          {
            zoomOutAction.funct();
          }
        }
      });
    }
  }, 2000);
};