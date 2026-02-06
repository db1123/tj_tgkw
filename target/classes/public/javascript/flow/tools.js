/**
 * 获取iframe父页URL
 * @returns url
 */
function getParentUrl() {
var url = null;
  if (parent !== window) {
    try {
      url = parent.location.href;
    } catch (e) {
      url = document.referrer;
    }
  }
  return url;
}

/**
 * 屏蔽所有功能项
 */
function flowDisableFunctionAll() {
  for(var key in mainEditorUi.actions.actions) {
    mainEditorUi.actions.get(key).setEnabled(false);
  }
}

/**
 * 启用所有功能项
 */
function flowEnableFunctionAll() {
  for(var key in mainEditorUi.actions.actions) {
    mainEditorUi.actions.get(key).setEnabled(true);
  }
}

/**
 * 显示区域自适应
 */
function flowViewAuto() {
  var graph = mainEditorUi.editor.graph;
  graph.fit(); // 自适应
  graph.center(true,true,0.5,0.5); // 将画布放到容器中间
  var sc = graph.getView().getScale(); // 获取当前的缩放比例
  graph.zoomTo(sc/2); // 在缩放一半，否则是满屏状态，不好看
}

/**
 * 调整视图比例
 * @param {比例} num 
 */
function flowZoom(num) {
	var graph = mainEditorUi.editor.graph;
  graph.zoomTo(num);
  mainEditorUi.resetScrollbars();
}

/**
 * 打开流程图
 * @param {流程图xml} xmlString 
 */
async function openFlowXml(xmlString) {
  while (!mainEditorUi.editor) {
    await sleep(1000);
  }
  var editor = mainEditorUi.editor;
  var doc = mxUtils.parseXml(xmlString);
  editor.setGraphXml(doc.documentElement);
  nowFlowSaveOp = 0; // 打开流程图后修改状态会被设置为1，所以需要重置修改状态
  nowFlowCourseSaveOp = 0;
}

/**
 * 清空流程图
 */
function cleanFlowGraph() {
  var editor = mainEditorUi.editor;
  // 空面板XML字符串
  var emptyStr = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/></root></mxGraphModel>';
  // 生成空白面板
  var doc = mxUtils.parseXml(emptyStr);
  editor.setGraphXml(doc.documentElement);
}

/**
 * 获取流程图字符串
 * @returns 程图字符串
 */
function getFlowStr() {
  var graph = mainEditorUi.editor.graph;
  // 获取当前面板XML
  var encoder = new mxCodec();
  var node = encoder.encode(graph.getModel());
  var xmlString = mxUtils.getXml(node);
  // 判断是否处于子流程图中
  if (saveXMLArray.length > 0) {
    savaParent(); // 逐级保存修改内容
    xmlString = saveXMLArray[0].xml;
  }
  // 返回
  return { xml: xmlString };
  // // 获取JSON
  // var jsonString = JSON.stringify(formatCells(graph.getModel()['cells']));
  // // 返回
  // return { xml: xmlString, json: jsonString };
}

// 获取指定Node节点
function findNodeById(nodeId) {
  var graph = mainEditorUi.editor.graph;
  // 获取所有节点
  var cells = graph.getModel()['cells'];
  // 获取指定节点
  var node = cells[nodeId];
  // 返回结果
  return node;
}

/**
 * 休眠
 * @param {时长} time 
 * @returns 
 */
function sleep(time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}

/**
 * 获取JSON属性个数
 * @param {JSON} obj 
 * @returns 
 */
function JSONLength(obj) {
  var size = 0, key;
  for (key in obj) {
    if (obj.hasOwnProperty(key)) size++;
  }
  return size;
};