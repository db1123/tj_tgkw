/**
 * 业务属性面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container
 */
function Logic(editorUi, container)
{
	this.editorUi = editorUi;
  this.container = container;
  this.logicMainContainer;
  this.init();
};
// 非活动选项卡的背景色
Logic.prototype.inactiveTabBackgroundColor = '#f1f3f4';
// 当前活动面板
Logic.prototype.currentIndex = 0;
// 初始化
Logic.prototype.init = function()
{
  // 标题
  var i = document.createElement('i');
  i.style.paddingRight = '5px';
  i.setAttribute('class', 'fas fa-cogs');
  var tittleDiv = document.createElement('div');
  tittleDiv.style.fontSize = '14px';
  tittleDiv.style.height = '26px';
  tittleDiv.style.paddingTop = '6px';
  tittleDiv.style.paddingLeft = '10px';
  tittleDiv.style.backgroundColor = '#efefef';
  tittleDiv.style.borderTop = '1px solid #dadce0';
  tittleDiv.style.borderBottom = '1px solid #dadce0';
  tittleDiv.style.display = 'block';
  tittleDiv.style.position = 'fixed';
  tittleDiv.style.width = '230px';
  tittleDiv.style.zIndex = 1;
  tittleDiv.appendChild(i);
	mxUtils.write(tittleDiv, '业务属性');
  this.container.appendChild(tittleDiv);
  // 业务属性面板
	this.logicMainContainer = document.createElement('div');
	this.logicMainContainer.style.width = '240px';
  this.logicMainContainer.style.overflow = 'hidden';
  this.logicMainContainer.style.paddingTop = '34px';
  this.container.appendChild(this.logicMainContainer);
};
// 刷新面板
Logic.prototype.Refresh = function(cell)
{
  // 清除内容
  this.logicMainContainer.innerHTML = '';
  // 判断是否为点击节点状态
  if (cell) { // 点击节点
    // Tab标签
    var tabDiv = document.createElement('div');
    tabDiv.style.whiteSpace = 'nowrap';
    tabDiv.style.color = 'rgb(112, 112, 112)';
    tabDiv.style.textAlign = 'left';
    tabDiv.style.cursor = 'default';
    tabDiv.style.width = '240px';
    tabDiv.style.position = 'fixed';
    tabDiv.style.zIndex = 1;
    this.logicMainContainer.appendChild(tabDiv);
    // 副标题（基本）
    var labelEntity = document.createElement('div');
    labelEntity.className = 'geFormatSection';
    labelEntity.style.textAlign = 'center';
    labelEntity.style.fontWeight = 'bold';
    labelEntity.style.paddingTop = '0';
    labelEntity.style.fontSize = '13px';
    labelEntity.style.borderWidth = '0px 0px 1px 0px';
    labelEntity.style.borderStyle = 'solid';
    labelEntity.style.display = 'inline-block';
    labelEntity.style.height = '23px';
    labelEntity.style.lineHeight = '23px';
    labelEntity.style.overflow = 'hidden';
    labelEntity.style.backgroundColor = this.inactiveTabBackgroundColor;
    labelEntity.style.cursor = 'pointer';
    if (function_param_panel_type == 1) {
      labelEntity.style.width = '25%';
    } else {
      labelEntity.style.width = '16.6%';
    }
    mxUtils.write(labelEntity, '基本');
    tabDiv.appendChild(labelEntity);
    // 副标题（团队）
    var labelTeam = labelEntity.cloneNode(false);
    labelTeam.style.backgroundColor = this.inactiveTabBackgroundColor;
    labelTeam.style.borderLeftWidth = '1px';
    mxUtils.write(labelTeam, '团队');
    tabDiv.appendChild(labelTeam);
    // 副标题（工作）
    var labelWork = labelEntity.cloneNode(false);
    labelWork.style.backgroundColor = this.inactiveTabBackgroundColor;
    labelWork.style.borderLeftWidth = '1px';
    mxUtils.write(labelWork, '时间');
    tabDiv.appendChild(labelWork);
    // 副标题（附件）
    var labelFile = labelEntity.cloneNode(false);
    labelFile.style.backgroundColor = this.inactiveTabBackgroundColor;
    labelFile.style.borderLeftWidth = '1px';
    mxUtils.write(labelFile, '文件');
    tabDiv.appendChild(labelFile);
    // 副标题（变量）
    var labelVariable = labelEntity.cloneNode(false);
    labelVariable.style.backgroundColor = this.inactiveTabBackgroundColor;
    labelVariable.style.borderLeftWidth = '1px';
    if (function_param_panel_type == 1) {
      labelVariable.style.display = 'none';
    }
    mxUtils.write(labelVariable, '变量');
    tabDiv.appendChild(labelVariable);
    // 副标题（规则）
    var labelRule = labelEntity.cloneNode(false);
    labelRule.style.backgroundColor = this.inactiveTabBackgroundColor;
    labelRule.style.borderLeftWidth = '1px';
    if (function_param_panel_type == 1) {
      labelRule.style.display = 'none';
    }
    mxUtils.write(labelRule, '规则');
    tabDiv.appendChild(labelRule);
    // 选项卡点击事件
    var currentLabel = null;
    var currentPanel = null;
    var addClickHandler = mxUtils.bind(this, function(elt, panel, index)
    {
      var clickHandler = mxUtils.bind(this, function(evt)
      {
        if (currentLabel != elt)
        {
          this.currentIndex = index;
          // 将原本激活的选项卡改为未激活样式
          if (currentLabel != null)
          {
            currentLabel.style.backgroundColor = this.inactiveTabBackgroundColor;
            currentLabel.style.borderBottom = '1px solid #dadce0';
          }
          // 重新设置当前激活选项卡（标题）
          currentLabel = elt;
          currentLabel.style.backgroundColor = '#fbfbfb';
          currentLabel.style.borderBottom = '1px solid #fbfbfb';
          // 将原本激活的选项卡隐藏
          if (currentPanel != null)
          {
            currentPanel.style.display = 'none';
          }
          // 重新设置当前激活选项卡（面板）
          currentPanel = panel;
          currentPanel.style.display = '';
        }
      });
      // 选项卡点击事件绑定
      mxEvent.addListener(elt, 'click', clickHandler);
      // 阻止文本选择
      mxEvent.addListener(elt, (mxClient.IS_POINTER) ? 'pointerdown' : 'mousedown',
        mxUtils.bind(this, function(evt)
        {
          evt.preventDefault();
        })
      );
      // 默认激活页
      if (index == this.currentIndex)
      {
        clickHandler();
      }
    });
    // 面板
    var idx = 0;
    var div = document.createElement('div');
    div.style.width = '240px';
    div.style.overflow = 'hidden';
    div.style.paddingTop = '23px';
    // 基本面板
    var entityPanel = div.cloneNode(false);
    entityPanel.style.display = 'none';
    entityPanel.style.paddingLeft = '10px';
    this.logicMainContainer.appendChild(entityPanel);
    addClickHandler(labelEntity, entityPanel, idx++);
    // 成员面板
    var teamPanel = div.cloneNode(false);
    teamPanel.style.display = 'none';
    teamPanel.style.paddingLeft = '10px';
    this.logicMainContainer.appendChild(teamPanel);
    addClickHandler(labelTeam, teamPanel, idx++);
    // 工作面板
    var workPanel = div.cloneNode(false);
    workPanel.style.display = 'none';
    workPanel.style.paddingLeft = '10px';
    this.logicMainContainer.appendChild(workPanel);
    addClickHandler(labelWork, workPanel, idx++);
    // 附件面板
    var filePanel = div.cloneNode(false);
    filePanel.style.display = 'none';
    filePanel.style.paddingLeft = '10px';
    this.logicMainContainer.appendChild(filePanel);
    addClickHandler(labelFile, filePanel, idx++);
    // 变量面板
    var variablePanel = div.cloneNode(false);
    variablePanel.style.display = 'none';
    variablePanel.style.paddingLeft = '10px';
    this.logicMainContainer.appendChild(variablePanel);
    addClickHandler(labelVariable, variablePanel, idx++);
    // 规则面板
    var rulePanel = div.cloneNode(false);
    rulePanel.style.display = 'none';
    rulePanel.style.paddingLeft = '10px';
    this.logicMainContainer.appendChild(rulePanel);
    addClickHandler(labelRule, rulePanel, idx++);
    // 重新添加（基本）
    switch (cell['simulationId']) {
      case 'start': // 开始节点
      case 'startPulse': // 脉冲开始节点
      case 'stop': // 结束节点
      case 'worker': // 工作人员
      case 'production': // 生产线
      case 'supplier': // 供应商
      case 'warehouse': // 仓库
      case 'processing': // 材料加工
      case 'freight': // 货运公司
      case 'machining': // 加工厂
      case 'materialFactory': // 原料生产厂
      case 'product': // 产品
      case 'material': // 原材料
      case 'inventoryMaintenance': // 库存
      case 'createOrder': // 创建单据
      case 'orderManagement': // 单据管理
      case 'customer': // 客户
      case 'trash': // 废弃
      case 'website': // 网站
      case 'database': // 数据库
      case 'data_capture': // 数据抓取
      case 'word_analytics': // 文档解析
      case 'data_analytics': // 数据分析
      case 'param': // 参数
      case 'function': // 方法
      case 'affect': // 功能
      case 'part': // 零件
      case 'other': // 其他
      case 'judge': // 判断
      case 'summary': // 汇总
      case 'back': // 驳回
        // 基本面板
        EntityLogicPanel(this.editorUi, entityPanel, cell);
        // 成员面板
        TeamLogicPanel(this.editorUi, teamPanel, cell);
        // 工作面板
        WorkLogicPanel(this.editorUi, workPanel, cell);
        // 附件面板
        FileLogicPanel(this.editorUi, filePanel, cell);
        // 变量面板
        VariableLogicPanel(this.editorUi, variablePanel, cell);
        // 规则面板
        RuleLogicPanel(this.editorUi, rulePanel, cell);
        // // 步长面板
        // StartPulseLogicPanel(this.editorUi, entityPanel, cell);
        break;
    
      default:
        entityPanel.style.paddingLeft = '10px';
        mxUtils.write(entityPanel, '暂无业务逻辑组件');
        teamPanel.style.paddingLeft = '10px';
        mxUtils.write(teamPanel, '暂无业务逻辑组件');
        variablePanel.style.paddingLeft = '10px';
        mxUtils.write(variablePanel, '暂无业务逻辑组件');
        rulePanel.style.paddingLeft = '10px';
        mxUtils.write(rulePanel, '暂无业务逻辑组件');
        break;
    }
  } else { // 点击绘图区域(显示全部参数)
    this.editorUi.logic.Clear();
  }
}
// 清空
Logic.prototype.Clear = function()
{
  // 清除内容
  this.logicMainContainer.innerHTML = '';
}
/**
 * 开始(脉冲)节点面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function StartPulseLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 布局表格
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.appendChild(tbody);
  this.container.appendChild(table);
  var row, td;
  // 节点运行间隔时间
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.padding = '8px 0';
  td.style.width = '90px';
  mxUtils.write(td, '节点脉冲间隔');
  row.appendChild(td);
  // 下拉列表(秒)
  td = document.createElement('td');
  td.style.width = '75px';
  var secondInput = document.createElement('div');
  secondInput.setAttribute('class', 'start-run-pulse-time-div');
  td.appendChild(secondInput);
  row.appendChild(td);
  // 单位
  td = document.createElement('td');
  td.style.padding = '8px 0';
  td.style.width = '20px';
  mxUtils.write(td, '秒');
  row.appendChild(td);
  tbody.appendChild(row);
  // 封装下拉列表
  $(".start-run-pulse-time-div").numInput({
    width: 70, //宽度
    positive: true, //允许输入正数
    negative: false, //允许输入负数
    floatCount: 0, //小数点后保留位数
    wrapperClass: 'start-run-pulse-time-wrap', //最外层容器
    inputClass: 'start-run-pulse-time-input', //input输入框
    addClass: 'start-run-pulse-time-add', //增加按钮
    subtractClass: 'start-run-pulse-time-subtract', //减少按钮
    interval: 1 //增加&减少按钮每次变化的值
  });
  // 下拉列表赋值
  if (cell['startPulseTime']) {
    $('.start-run-pulse-time-input').val(cell['startPulseTime']);
  }
  // 改变事件
  var valueChange = function() {
    cell['startPulseTime'] = $('.start-run-pulse-time-input').val();
    // ↓↓↓ 启动保存按钮 开始 ↓↓↓
    nowFlowSaveOp = 1;
    EditorMenuSave(); // 可保存状态
    // ↑↑↑ 启动保存按钮 结束 ↑↑↑
  };
  $('.start-run-pulse-time-input').bind('input', valueChange);
  $('.start-run-pulse-time-add').bind('click', valueChange);
  $('.start-run-pulse-time-subtract').bind('click', valueChange);
}
/**
 * 供应商面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function SupplierLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  var graph = editorUi.editor.graph;
  // 下拉列表
  var select = document.createElement('select');
  select.style.width = "96%";
  select.setAttribute('class', 'select-supplier');
  this.container.appendChild(select);
  // 封装下拉列表
  var selectSupplier = $('.select-supplier').select2({
    placeholder: '请选择供应商',
    ajax: {
      type:'POST',
      url: "/s/supplier/queryDataSupplierSelect",
      dataType: 'json',
      data: function (params) {
        return {
          search: params.term,
        };
      },
      delay: 800,
      cache: true
    },
    language: "zh-CN"
  });
  // 下拉列表赋值
  if (!isNull(cell['supplierId']) && !isNull(cell['supplierName'])) {
    setTimeout(function () {
      var option = new Option(cell['supplierName'], cell['supplierId'], true, true);
      selectSupplier.append(option);
      selectSupplier.change();
    }, 100);
  }
  // 改变事件
  $(".select-supplier").on("select2:select",function(e){
    var data = e.params.data;
    cell['value'] = cell.value + ':' + data.text;
    cell['supplierId'] = data.id;
    cell['supplierName'] = data.text;
    // ↓↓↓ 启动保存按钮 开始 ↓↓↓
    nowFlowSaveOp = 1;
    EditorMenuSave(); // 可保存状态
    // ↑↑↑ 启动保存按钮 结束 ↑↑↑
    graph.refresh(cell);
  });
  // 判断是否需要禁止
  if (function_all_op == 0) {
    $('.select-supplier').prop('disabled', true);
  }
}
/**
 * 人员面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function UserLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  var graph = editorUi.editor.graph;
  // 下拉列表
  var select = document.createElement('select');
  select.style.width = "96%";
  select.setAttribute('class', 'select-user');
  this.container.appendChild(select);
  // 封装下拉列表
  var selectUser = $('.select-user').select2({
    placeholder: '请选择人员',
    ajax: {
      type:'POST',
      url: '/s/user/queryDataUserSelect',
      dataType: 'json',
      data: function (params) {
        return {
          search: params.term,
        };
      },
      delay: 800,
      cache: true
    },
    language: "zh-CN"
  });
  // 下拉列表赋值
  if (!isNull(cell['userId']) && !isNull(cell['userName'])) {
    setTimeout(function () {
      var option = new Option(cell['userName'], cell['userId'], true, true);
      selectUser.append(option);
      selectUser.change();
    }, 100);
  }
  // 改变事件
  $(".select-user").on("select2:select",function(e){
    var data = e.params.data;
    cell['value'] = cell.value + ':' + data.text;
    cell['userId'] = data.id;
    cell['userName'] = data.text;
    // ↓↓↓ 启动保存按钮 开始 ↓↓↓
    nowFlowSaveOp = 1;
    EditorMenuSave(); // 可保存状态
    // ↑↑↑ 启动保存按钮 结束 ↑↑↑
    graph.refresh(cell);
  });
  // 判断是否需要禁止
  if (function_all_op == 0) {
    $('.select-user').prop('disabled', true);
  }
}
/**********************************
 * 基本面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 **********************************/
function EntityLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  console.log(cell['simulationId'])
  switch (cell['simulationId']) {
    case 'worker': // 工作人员
    case 'production': // 生产线
    case 'supplier': // 供应商
    case 'warehouse': // 仓库
    case 'processing': // 材料加工
    case 'freight': // 货运公司
    case 'machining': // 加工厂
    case 'materialFactory': // 原料生产厂
    case 'product': // 产品
    case 'material': // 原材料
    case 'inventoryMaintenance': // 库存
    case 'createOrder': // 创建单据
    case 'orderManagement': // 单据管理
    case 'customer': // 客户
    case 'trash': // 废弃
    case 'website': // 网站
    case 'database': // 数据库
    case 'data_capture': // 数据抓取
    case 'word_analytics': // 文档解析
    case 'data_analytics': // 数据分析
    case 'summary': // 汇总
    case 'judge': // 判断
    case 'other': // 其他
      // 子流程图绑定
      if (control_logic_subprocess_op == 1) {
        SubprocessLogicPanel(this.editorUi, this.container, cell);
      }
      // URL连接面板
      URLLogicPanel(this.editorUi, this.container, cell);
      // 说明
      MemoLogicPanel(this.editorUi, this.container, cell);
      break;
    case 'param': // 参数
    case 'function': // 方法
    case 'affect': // 功能
    case 'part': // 零件

      break;
    default: // 未知类型
      break;
  }
}
/**
 * 子流程图绑定面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function SubprocessLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 初始化
  if (isNull(cell['subprocess'])) { // 跳转地址
    cell['subprocess'] = {};
  }
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 子流程绑定标题
  var Span_Subprocess_Title = titleSpan.cloneNode(false);
  mxUtils.write(Span_Subprocess_Title, '子流程绑定');
  div.appendChild(Span_Subprocess_Title);
  // 设置按钮
  if (function_all_op == 1) {
    var I_Subprocess = document.createElement('i');
    I_Subprocess.setAttribute('class', 'fas fa-cog');
    var Button_Subprocess = mxUtils.button('', function()
    {
      var ParameterJSON = {};
      ParameterJSON['subprocess'] = cell['subprocess'];
      // 打开面板
      var dlg = new SubprocessSetDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var subprocess;
          $("#subprocessSelect").select2("data").forEach((item)=>{
            subprocess = {subprocessId: item.id, subprocessName: item.text};
          });
          if (subprocess['subprocessId'] == 0) {
            cell['subprocess'] = {};
            $('#SubprocessSpan').text('无');
          } else {
            cell['subprocess'] = subprocess;
            cell['subprocess']['subprocessXML'] = $('#flowIframeView')[0].contentWindow.getFlowStr().xml;
            // ↓↓↓ 启动保存按钮 开始 ↓↓↓
            nowFlowSaveOp = 1;
            EditorMenuSave(); // 可保存状态
            // ↑↑↑ 启动保存按钮 结束 ↑↑↑
            var title = cell['subprocess'].subprocessName;
            $('#SubprocessSpan').text(title.length>12?title.substring(0,12)+'...':title);
          }
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
        }, 100);
      }), 2, ParameterJSON);
      editorUi.showDialog(dlg.container, 1000, 500, true, true);
      dlg.init();
    });
    Button_Subprocess.style.marginLeft = '88px';
    Button_Subprocess.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_Subprocess.appendChild(I_Subprocess);
    mxUtils.write(Button_Subprocess, '设置');
    Span_Subprocess_Title.appendChild(Button_Subprocess);
    var hr = document.createElement('hr');
    hr.style.margin = '3px 0';
    Span_Subprocess_Title.appendChild(hr);
  }
  // 打开子流程图按钮
  var I_OpenFlow = document.createElement('i');
  I_OpenFlow.setAttribute('class', 'fas fa-folder-open');
  var Button_OpenFlow = mxUtils.button('', function()
  {
    // 打开子流程图
    if (cell['subprocess']['subprocessXML']) {
      var editor = mainEditorUi.editor;
      var graph = editor.graph;
      // 当前流程图存入面包屑数组
      var encoder = new mxCodec();
      var node = encoder.encode(graph.getModel());
      var xml = mxUtils.getXml(node);
      saveXMLArray.push({
        nodeId: cell.id,
        xml: xml
      });
      // 在子流程中禁止部分功能
      var editor = mainEditorUi.editor;
      var graph = editor.graph;
      graph.setEnabled(false); // 禁止流程图编辑
      control_toolbar_op = 0; // 工具栏面板 
      control_sidebar_op = 0; // 左侧图形面板
      control_outline_op = 0; // 缩略图面板
      function_draw_edit_op = 0; // 绘图区禁止编辑
      function_undo_redo_op = 0; // 撤销 恢复 功能禁止
      function_fileUpLoad_op = 0; // 附件编辑功能禁用
      mainEditorUi.refresh();
      // 打开子流程图
      var doc = mxUtils.parseXml(cell['subprocess']['subprocessXML']);
      editor.setGraphXml(doc.documentElement);
      // 生成返回按钮
      if (saveXMLArray.length == 1) {
        flowBack();
      }
    }
  });
  Button_OpenFlow.style.marginLeft = '0';
  Button_OpenFlow.style.width = '100%';
  Button_OpenFlow.style.textAlign = 'left';
  Button_OpenFlow.setAttribute('id', 'SubprocessOpenFlowButton');
  Button_OpenFlow.setAttribute('class', 'geBtn geSuccessBtn geBtnMini2');
  Button_OpenFlow.appendChild(I_OpenFlow);
  // 打开子流程图按钮-显示内容
  var goSubprocessContentSpan = document.createElement('span');
  goSubprocessContentSpan.setAttribute('id', 'SubprocessSpan');
  var title = cell['subprocess'].subprocessName ? cell['subprocess'].subprocessName : '无';
  mxUtils.write(goSubprocessContentSpan, title.length>12?title.substring(0,12)+'...':title);
  Button_OpenFlow.appendChild(goSubprocessContentSpan);
  Span_Subprocess_Title.appendChild(Button_OpenFlow);
}
/**
 * URL表格面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function URLLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 节点启动条件标题
  var nodeSpan = titleSpan.cloneNode(false);
  mxUtils.write(nodeSpan, '资源');
  div.appendChild(nodeSpan);
  // 表格数据
  if (isNull(cell['urlData'])) {
    cell['urlData'] = [];
  }
  // 新建按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var i = document.createElement('i');
    i.setAttribute('class', 'fas fa-plus');
    var newButton = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new URLEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('urlForm');
          jsonData['f_id'] = guid();
          jsonData['f_node_id'] = cell.id;
          cell['urlData'].push(jsonData);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#urlTable').dataTable().fnClearTable();
    　    $('#urlTable').dataTable().fnAddData(cell['urlData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 500, 135, true, true);
      dlg.init();
    });
    newButton.style.marginLeft = '133px';
    newButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    newButton.appendChild(i);
    mxUtils.write(newButton, '新增');
    nodeSpan.appendChild(newButton);
  }
  // 变量列表
  var DateTable = document.createElement('table');
  DateTable.style.width = '225px';
  DateTable.style.color = 'rgb(112, 112, 112)';
  DateTable.style.margin = '5px 0 0 0';
  DateTable.setAttribute('id', 'urlTable');
  DateTable.setAttribute('class', 'display compact');
  div.appendChild(DateTable);
  // 封装表格
  var urlTableConfig = {
    data: cell['urlData'],
    columns: [
      {
        data: 'f_name',
        render: function (data, type, row) {
          var last = row.f_url.length > 3 ? row.f_url.substring(row.f_url.length-3) : '';
          if (last == '://') {
            return '<a href="' + row.f_url + '">' + data + '</a>';
          } else {
            return '<div class="tip-hotspot" style="cursor: pointer;" onclick="top.creatIframe(\''+row.f_url+'\',\''+row.f_name+'\')" data-tip="'+(row.f_url ? row.f_url : row.f_name)+'">'+data+'</div>';
            // return '<div class="tip-hotspot" style="cursor: pointer;" onclick="window.open(\''+row.f_url+'\')" data-tip="'+(row.f_url ? row.f_url : row.f_name)+'">'+data+'</div>';
          }
        }
      }
    ],
    paging: false, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: false, // 是否允许Datatables开启本地搜索
    ordering: false, // 是否允许Datatables开启排序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  };
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    urlTableConfig.columns.push({
      data: 'f_id',
      width: 33,
      orderable: false,
      render: function (data) {
        var actionEdit = function(id)
        {
          // 加载面板
          $('body').mLoading('show');
          setTimeout(function(){
            if (nowSelectNode && nowSelectNode['urlData']) {
              var dataArr = nowSelectNode['urlData'];
              var index = -1;
              for (var i = 0, l = dataArr.length; i < l; i++) {
                index = i;
                var row = dataArr[i];
                if (row['f_id'] == id) { // 判断是否为编辑行
                  var ParameterJSON = {};
                  ParameterJSON['f_name'] = row['f_name'];
                  ParameterJSON['f_url'] = row['f_url'];
                  ParameterJSON['f_id'] = row['f_id'];
                  var dlg = new URLEditDialog(editorUi, mxUtils.bind(this, function()
                  {
                    // 加载面板
                    $('body').mLoading('show');
                    // 延时执行
                    setTimeout(function(){
                      var jsonData = form2Json('urlForm');
                      row['f_name'] = jsonData['f_name'];
                      row['f_url'] = jsonData['f_url'];
                      nowSelectNode['urlData'].splice(index, 1, row);
                      $('body').mLoading('hide');
                      toastr.success('保存成功！');
                      mainEditorUi.hideDialog();
                      // 重新构建table
                      $('#urlTable').dataTable().fnClearTable();
                　    $('#urlTable').dataTable().fnAddData(nowSelectNode['urlData'], true);
                    }, 100);
                  }), 2, ParameterJSON);
                  mainEditorUi.showDialog(dlg.container, 500, 135, true, true);
                  dlg.init();
                  break;
                }
              }
            }
            $('body').mLoading('hide');
          }, 200);
        };
        var actionDel = function(id)
        {
          var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 加载面板
            $('body').mLoading('show');
            setTimeout(function(){
              if (nowSelectNode && nowSelectNode['urlData']) {
                var dataArr = nowSelectNode['urlData'];
                var index = -1;
                for (var i = 0, l = dataArr.length; i < l; i++) {
                  index = i;
                  var row = dataArr[i];
                  if (row['f_id'] == id) {
                    break;
                  }
                }
                if (index != -1) {
                  nowSelectNode['urlData'].splice(index, 1);
                }
              }
              $('body').mLoading('hide');
              toastr.success('删除成功！')
              mainEditorUi.hideDialog();
              // 重新构建table
              $('#urlTable').dataTable().fnClearTable();
              if(nowSelectNode['urlData'].length > 0) {
        　      $('#urlTable').dataTable().fnAddData(nowSelectNode['urlData'], true);
              }
            }, 200);
          }), '确定要删除当前记录吗?', 2);
          mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionEdit.toString()+')(\''+data+'\')">改</button>' +
          '<button type="button" class="geBtn geDangerBtn geBtnMini" onclick="('+actionDel.toString()+')(\''+data+'\')">删</button>';
      }
    });
  }
  $('#urlTable').DataTable(urlTableConfig);
}
/**
 * 说明面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
 function MemoLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 初始化
  if (isNull(cell['memo'])) {
    cell['memo'] = '';
  }
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var tittleSpan = document.createElement('span');
  tittleSpan.style.display = 'block';
  tittleSpan.style.fontWeight = '600';
  tittleSpan.style.padding = '5px 15px 0 0';
  tittleSpan.style.color = '#2460bf';
  tittleSpan.style.fontSize = '15px';
  mxUtils.write(tittleSpan, '说明');
  div.appendChild(tittleSpan);
  // 设置按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_Work = document.createElement('i');
    I_Work.setAttribute('class', 'fas fa-cog');
    var Button_Work = mxUtils.button('', function()
    {
      var ParameterJSON = {};
      ParameterJSON['memo'] = cell['memo'];
      // 打开面板
      var dlg = new MemoSetDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('memoSetForm');
          cell['memo'] = jsonData['memo'];
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('#LogicMemoDiv').html(jsonData['memo']);
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
        }, 100);
      }), 2, ParameterJSON);
      editorUi.showDialog(dlg.container, 410, 218, true, true);
      dlg.init();
    });
    Button_Work.style.marginLeft = '132px';
    Button_Work.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_Work.appendChild(I_Work);
    mxUtils.write(Button_Work, '设置');
    tittleSpan.appendChild(Button_Work);
    var hr = document.createElement('hr');
    hr.style.margin = '3px 0';
    tittleSpan.appendChild(hr);
  }
  // 显示内容
  var content = document.createElement('div');
  content.style.whiteSpace = 'pre-line';
  content.style.position = 'relative';
  content.style.width = '205px';
  content.style.height = '160px';
  content.style.backgroundColor = '#f7f7f9';
  content.style.border = '1px solid #e1e1e8';
  content.style.padding = '5px';
  content.style.borderRadius = '4px';
  content.innerHTML = cell['memo'] == '' ? '无' : cell['memo'];
  content.setAttribute('id', 'LogicMemoDiv');
  div.appendChild(content);
  setTimeout(function(){ // 缓冲后创建自定义滚动条(不使用setTimeout的话,会出现首次创建无法加载横向条的问题)
    new PerfectScrollbar('#LogicMemoDiv');
  }, 100);
}
/**
 * 附件上传规范面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function FileControlLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 初始化
  if (isNull(cell['uploadFileSet'])) { // 上传设置
    cell['uploadFileSet'] = {};
  }
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 上传文件规则标题
  var uploadFileTittleSpan = titleSpan.cloneNode(false);
  mxUtils.write(uploadFileTittleSpan, '上传文件规则');
  div.appendChild(uploadFileTittleSpan);
  // 设置按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_UploadFile = document.createElement('i');
    I_UploadFile.setAttribute('class', 'fas fa-cog');
    var Button_UploadFile = mxUtils.button('', function()
    {
      var old = cell['uploadFileSet'];
      var ParameterJSON = {};
      ParameterJSON['f_num'] = old['f_num'] ? old['f_num'] : '';
      ParameterJSON['fileExts'] = old['fileExts'];
      ParameterJSON['fileTypes'] = old['fileTypes'];
      // 打开面板
      var dlg = new UploadFileSetDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('uploadFileSetForm');
          var extData = [];
          var extStr = '';
          $("#f_file_ext").select2("data").forEach((item)=>{
            extData.push({
              fileExtId: item.id,
              fileExtName: item.text,
              fileExt: item.ext
            });
            extStr += ',' + item.text;
          });
          if (extStr.length > 0) extStr = extStr.substring(1);
          var typeData = [];
          var typeStr = '';
          $("#f_file_type").select2("data").forEach((item)=>{
            typeData.push({
              fileTypeId: item.id,
              fileTypeName: item.text
            });
            typeStr += ',' + item.text;
          });
          if (typeStr.length > 0) typeStr = typeStr.substring(1);
          cell['uploadFileSet'] = {
            f_num: jsonData['f_num'],
            fileExts: extData,
            fileTypes: typeData
          }
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          var str = '数量: ' + jsonData['f_num'] + '<br/>';
          str += '类型:【' + extStr + '】' + '<br/>';
          str += '类别:【' + typeStr + '】';
          $('#UploadFileSpan').html(str);
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
        }, 100);
      }), 2, ParameterJSON);
      editorUi.showDialog(dlg.container, 500, 300, true, true);
      dlg.init();
    });
    Button_UploadFile.style.marginLeft = '65px';
    Button_UploadFile.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_UploadFile.appendChild(I_UploadFile);
    mxUtils.write(Button_UploadFile, '设置');
    uploadFileTittleSpan.appendChild(Button_UploadFile);
    var hr = document.createElement('hr');
    hr.style.margin = '3px 0';
    uploadFileTittleSpan.appendChild(hr);
  }
  // 显示内容
  var uploadFileContentSpan = contentSpan.cloneNode(false);
  uploadFileContentSpan.setAttribute('id', 'UploadFileSpan');
  var extStr = '';
  if (cell['uploadFileSet']['fileExts']) {
    cell['uploadFileSet']['fileExts'].forEach((item)=>{
      extStr += ',' + item.fileExtName;
    });
  }
  if (extStr.length > 0) extStr = extStr.substring(1);
  var typeStr = '';
  if (cell['uploadFileSet']['fileTypes']) {
    cell['uploadFileSet']['fileTypes'].forEach((item)=>{
      typeStr += ',' + item.fileTypeName;
    });
  }
  if (typeStr.length > 0) typeStr = typeStr.substring(1);
  var str = '数量: ' + (cell['uploadFileSet']['f_num'] ? cell['uploadFileSet']['f_num'] : '无') + '<br/>';
  str += '类型:【' + (extStr == '' ? '无' : extStr) + '】' + '<br/>';
  str += '类别:【' + (typeStr == '' ? '无' : typeStr) + '】';
  uploadFileContentSpan.innerHTML = str;
  div.appendChild(uploadFileContentSpan);
}
/**********************************
 * 成员面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 **********************************/
function TeamLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 团队--负责人
  LeaderUserLogicPanel(this.editorUi, this.container, cell);
  // 团队--成员
  MemberUserLogicPanel(this.editorUi, this.container, cell);
}
/**
 * 负责人面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function LeaderUserLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 标题
  var LeaderSpan = document.createElement('span');
  LeaderSpan.style.display = 'block';
  LeaderSpan.style.color = 'rgb(112, 112, 112)';
  LeaderSpan.style.fontWeight = '600';
  LeaderSpan.style.padding = '5px 0';
  LeaderSpan.style.color = '#2460bf';
  LeaderSpan.style.fontSize = '15px';
  mxUtils.write(LeaderSpan, '负责人');
  this.container.appendChild(LeaderSpan);
  // 下拉列表
  var select = document.createElement('select');
  select.style.width = "225px";
  select.setAttribute('multiple', 'multiple');
  select.setAttribute('class', 'select-leader-user');
  this.container.appendChild(select);
  // 封装下拉列表
  var selectUser = $('.select-leader-user').select2({
    ajax: {
      type:'POST',
      url: "/s/user/queryDataUserSelect",
      dataType: 'json',
      data: function (params) {
        return {
          search: params.term,
        };
      },
      delay: 800,
      cache: true
    },
    language: "zh-CN"
  });
  // 下拉列表赋值
  if (!isNull(cell['leaderUserData'])) {
    setTimeout(function () {
      cell['leaderUserData'].forEach((item)=>{
        var option = new Option(item.userName, item.userId, true, true);
        selectUser.append(option);
      });
      selectUser.change();
    }, 100);
  }
  // 改变事件
  $(".select-leader-user").on("change",function(e){
    var data = [];
    $(".select-leader-user").select2("data").forEach((item)=>{
      data.push({
        userId: item.id,
        userName: item.text
      });
    });
    cell['leaderUserData'] = data;
    // 刷新节点
    mainEditorUi.editor.graph.refresh(cell);
    // ↓↓↓ 启动保存按钮 开始 ↓↓↓
    nowFlowSaveOp = 1;
    EditorMenuSave(); // 可保存状态
    // ↑↑↑ 启动保存按钮 结束 ↑↑↑
  });
  // 判断是否需要禁止
  if (function_all_op == 0) {
    $('.select-leader-user').prop('disabled', true);
  }
}
/**
 * 成员面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function MemberUserLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 标题
  var MemberUserSpan = document.createElement('span');
  MemberUserSpan.style.display = 'block';
  MemberUserSpan.style.color = 'rgb(112, 112, 112)';
  MemberUserSpan.style.fontWeight = '600';
  MemberUserSpan.style.padding = '5px 0';
  MemberUserSpan.style.color = '#2460bf';
  MemberUserSpan.style.fontSize = '15px';
  mxUtils.write(MemberUserSpan, '团队成员');
  this.container.appendChild(MemberUserSpan);
  // 下拉列表
  var select = document.createElement('select');
  select.style.width = "225px";
  select.setAttribute('multiple', 'multiple');
  select.setAttribute('class', 'select-member-user');
  this.container.appendChild(select);
  // 封装下拉列表
  var selectUser = $('.select-member-user').select2({
    ajax: {
      type:'POST',
      url: "/s/user/queryDataUserSelect",
      dataType: 'json',
      data: function (params) {
        return {
          search: params.term,
        };
      },
      delay: 800,
      cache: true
    },
    language: "zh-CN"
  });
  // 下拉列表赋值
  if (!isNull(cell['memberUserData'])) {
    setTimeout(function () {
      cell['memberUserData'].forEach((item)=>{
        var option = new Option(item.userName, item.userId, true, true);
        selectUser.append(option);
      });
      selectUser.change();
    }, 100);
  }
  // 改变事件
  $(".select-member-user").on("change",function(e){
    var data = [];
    $(".select-member-user").select2("data").forEach((item)=>{
      data.push({
        userId: item.id,
        userName: item.text
      });
    });
    cell['memberUserData'] = data;
    // 刷新节点
    mainEditorUi.editor.graph.refresh(cell);
    // ↓↓↓ 启动保存按钮 开始 ↓↓↓
    nowFlowSaveOp = 1;
    EditorMenuSave(); // 可保存状态
    // ↑↑↑ 启动保存按钮 结束 ↑↑↑
  });
  // 判断是否需要禁止
  if (function_all_op == 0) {
    $('.select-member-user').prop('disabled', true);
  }
}
/**********************************
 * 工作面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 **********************************/
function WorkLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 工作周期设置
  WorkNumLogicPanel(this.editorUi, this.container, cell);
  // 起止日期设置
  StartEndLogicPanel(this.editorUi, this.container, cell);
}
/**
 * 工作周期面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function WorkNumLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 初始化
  if (isNull(cell['work_num'])) { // 跳转地址
    cell['work_num'] = '';
  }
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var tittleSpan = document.createElement('span');
  tittleSpan.style.display = 'block';
  tittleSpan.style.fontWeight = '600';
  tittleSpan.style.padding = '5px 15px 0 0';
  tittleSpan.style.color = '#2460bf';
  tittleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 工作周期标题
  var goWorkTittleSpan = tittleSpan.cloneNode(false);
  mxUtils.write(goWorkTittleSpan, '工作周期(天)');
  div.appendChild(goWorkTittleSpan);
  // 设置按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_Work = document.createElement('i');
    I_Work.setAttribute('class', 'fas fa-cog');
    var Button_Work = mxUtils.button('', function()
    {
      var ParameterJSON = {};
      ParameterJSON['work_num'] = cell['work_num'];
      // 打开面板
      var dlg = new WorkNumSetDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('workNumSetForm');
          cell['work_num'] = jsonData['work_num'];
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('#WorkNumSpan').text(jsonData['work_num']);
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
        }, 100);
      }), 2, ParameterJSON);
      editorUi.showDialog(dlg.container, 250, 88, true, true);
      dlg.init();
    });
    Button_Work.style.marginLeft = '78px';
    Button_Work.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_Work.appendChild(I_Work);
    mxUtils.write(Button_Work, '设置');
    goWorkTittleSpan.appendChild(Button_Work);
    var hr = document.createElement('hr');
    hr.style.margin = '3px 0';
    goWorkTittleSpan.appendChild(hr);
  }
  // 显示内容
  var goURLContentSpan = contentSpan.cloneNode(false);
  goURLContentSpan.setAttribute('id', 'WorkNumSpan');
  mxUtils.write(goURLContentSpan, cell['work_num'] == '' ? '无' : cell['work_num']);
  div.appendChild(goURLContentSpan);
}
/**
 * 起止日期面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function StartEndLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 初始化
  if (isNull(cell['start_date']) && isNull(cell['end_date'])) { // 跳转地址
    cell['start_date'] = '';
    cell['end_date'] = '';
  }
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var tittleSpan = document.createElement('span');
  tittleSpan.style.display = 'block';
  tittleSpan.style.fontWeight = '600';
  tittleSpan.style.padding = '5px 15px 0 0';
  tittleSpan.style.color = '#2460bf';
  tittleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 起止日期标题
  var goStartEndTittleSpan = tittleSpan.cloneNode(false);
  mxUtils.write(goStartEndTittleSpan, '起止日期');
  div.appendChild(goStartEndTittleSpan);
  // 设置按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_StartEnd = document.createElement('i');
    I_StartEnd.setAttribute('class', 'fas fa-cog');
    var Button_StartEnd = mxUtils.button('', function()
    {
      var ParameterJSON = {};
      ParameterJSON['start_date'] = cell['start_date'];
      ParameterJSON['end_date'] = cell['end_date'];
      // 打开面板
      var dlg = new StartEndSetDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          cell['start_date'] = $('#start_end_date').attr('start_date');
          cell['end_date'] = $('#start_end_date').attr('end_date');
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('#StartEndDateSpan').text(cell['start_date'] + ' — ' + cell['end_date']);
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
        }, 100);
      }), 2, ParameterJSON);
      editorUi.showDialog(dlg.container, 300, 82, true, true);
      dlg.init();
    });
    Button_StartEnd.style.marginLeft = '103px';
    Button_StartEnd.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_StartEnd.appendChild(I_StartEnd);
    mxUtils.write(Button_StartEnd, '设置');
    goStartEndTittleSpan.appendChild(Button_StartEnd);
    var hr = document.createElement('hr');
    hr.style.margin = '3px 0';
    goStartEndTittleSpan.appendChild(hr);
  }
  // 显示内容
  var goURLContentSpan = contentSpan.cloneNode(false);
  goURLContentSpan.setAttribute('id', 'StartEndDateSpan');
  mxUtils.write(goURLContentSpan, (cell['start_date'] == '' && cell['end_date'] == '') ? '无' : (cell['start_date'] + ' — ' + cell['end_date']));
  div.appendChild(goURLContentSpan);
}
/**********************************
 * 附件面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 **********************************/
function FileLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 附件上传规范面板
  // FileControlLogicPanel(this.editorUi, this.container, cell);
  // 交付物面板
  FileSubDataLogicPanel(this.editorUi, this.container, cell);
  // 附件面板
  FileUploadLogicPanel(this.editorUi, this.container, cell);
}
/**
 * 附件上传规范面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function FileControlLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 初始化
  if (isNull(cell['uploadFileSet'])) { // 上传设置
    cell['uploadFileSet'] = {};
  }
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 上传文件规则标题
  var uploadFileTittleSpan = titleSpan.cloneNode(false);
  mxUtils.write(uploadFileTittleSpan, '上传文件规则');
  div.appendChild(uploadFileTittleSpan);
  // 设置按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_UploadFile = document.createElement('i');
    I_UploadFile.setAttribute('class', 'fas fa-cog');
    var Button_UploadFile = mxUtils.button('', function()
    {
      var old = cell['uploadFileSet'];
      var ParameterJSON = {};
      ParameterJSON['f_num'] = old['f_num'] ? old['f_num'] : '';
      ParameterJSON['fileExts'] = old['fileExts'];
      ParameterJSON['fileTypes'] = old['fileTypes'];
      // 打开面板
      var dlg = new UploadFileSetDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('uploadFileSetForm');
          var extData = [];
          var extStr = '';
          $("#f_file_ext").select2("data").forEach((item)=>{
            extData.push({
              fileExtId: item.id,
              fileExtName: item.text,
              fileExt: item.ext
            });
            extStr += ',' + item.text;
          });
          if (extStr.length > 0) extStr = extStr.substring(1);
          var typeData = [];
          var typeStr = '';
          $("#f_file_type").select2("data").forEach((item)=>{
            typeData.push({
              fileTypeId: item.id,
              fileTypeName: item.text
            });
            typeStr += ',' + item.text;
          });
          if (typeStr.length > 0) typeStr = typeStr.substring(1);
          cell['uploadFileSet'] = {
            f_num: jsonData['f_num'],
            fileExts: extData,
            fileTypes: typeData
          }
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          var str = '数量: ' + jsonData['f_num'] + '<br/>';
          str += '类型:【' + extStr + '】' + '<br/>';
          str += '类别:【' + typeStr + '】';
          $('#UploadFileSpan').html(str);
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
        }, 100);
      }), 2, ParameterJSON);
      editorUi.showDialog(dlg.container, 500, 300, true, true);
      dlg.init();
    });
    Button_UploadFile.style.marginLeft = '73px';
    Button_UploadFile.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_UploadFile.appendChild(I_UploadFile);
    mxUtils.write(Button_UploadFile, '设置');
    uploadFileTittleSpan.appendChild(Button_UploadFile);
    var hr = document.createElement('hr');
    hr.style.margin = '3px 0';
    uploadFileTittleSpan.appendChild(hr);
  }
  // 显示内容
  var uploadFileContentSpan = contentSpan.cloneNode(false);
  uploadFileContentSpan.setAttribute('id', 'UploadFileSpan');
  var extStr = '';
  if (cell['uploadFileSet']['fileExts']) {
    cell['uploadFileSet']['fileExts'].forEach((item)=>{
      extStr += ',' + item.fileExtName;
    });
  }
  if (extStr.length > 0) extStr = extStr.substring(1);
  var typeStr = '';
  if (cell['uploadFileSet']['fileTypes']) {
    cell['uploadFileSet']['fileTypes'].forEach((item)=>{
      typeStr += ',' + item.fileTypeName;
    });
  }
  if (typeStr.length > 0) typeStr = typeStr.substring(1);
  var str = '数量: ' + (cell['uploadFileSet']['f_num'] ? cell['uploadFileSet']['f_num'] : '无') + '<br/>';
  str += '类型:【' + (extStr == '' ? '无' : extStr) + '】' + '<br/>';
  str += '类别:【' + (typeStr == '' ? '无' : typeStr) + '】';
  uploadFileContentSpan.innerHTML = str;
  div.appendChild(uploadFileContentSpan);
}
/**
 * 交付物表格面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
 function FileSubDataLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 节点启动条件标题
  var nodeSpan = titleSpan.cloneNode(false);
  mxUtils.write(nodeSpan, '交付物');
  div.appendChild(nodeSpan);
  // 表格数据
  if (isNull(cell['fileSubData'])) {
    cell['fileSubData'] = [];
  }
  // 新建按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var i = document.createElement('i');
    i.setAttribute('class', 'fas fa-plus');
    var newButton = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new SubDataEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('subDataForm');
          jsonData['f_id'] = guid();
          jsonData['f_node_id'] = cell.id;
          $("#f_file_type").select2("data").forEach((item)=>{
            jsonData['fileTypeId'] = item.id;
            jsonData['fileTypeName'] = item.text;
          });
          $("#f_check_flow").select2("data").forEach((item)=>{
            jsonData['checkFlowId'] = item.id;
            jsonData['checkFlowName'] = item.text;
          });
          cell['fileSubData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#subDataTable').dataTable().fnClearTable();
    　    $('#subDataTable').dataTable().fnAddData(cell['fileSubData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 450, 185, true, true);
      dlg.init();
    });
    newButton.style.marginLeft = '120px';
    newButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    newButton.appendChild(i);
    mxUtils.write(newButton, '新增');
    nodeSpan.appendChild(newButton);
  }
  // 数据列表
  var DateTable = document.createElement('table');
  DateTable.style.width = '225px';
  DateTable.style.color = 'rgb(112, 112, 112)';
  DateTable.style.margin = '5px 0 0 0';
  DateTable.setAttribute('id', 'subDataTable');
  DateTable.setAttribute('class', 'display compact');
  div.appendChild(DateTable);
  // 封装表格
  var subDataTableConfig = {
    data: cell['fileSubData'],
    columns: [
      {
        data: 'f_name',
        render: (data, type, row, full) => '<div class="tip-hotspot" data-tip="文件类型：'+row.fileTypeName+((row.f_check_flow&&row.f_check_flow!='') ? '<br/>审核流程：'+row.checkFlowName : '')+'">'+data+'</div>'
      }
    ],
    paging: false, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: false, // 是否允许Datatables开启本地搜索
    ordering: false, // 是否允许Datatables开启排序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  };
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    subDataTableConfig.columns.push({
      data: 'f_id',
      width: 33,
      orderable: false,
      render: function (data) {
        var actionEdit = function(id)
        {
          // 加载面板
          $('body').mLoading('show');
          setTimeout(function(){
            if (nowSelectNode && nowSelectNode['fileSubData']) {
              var dataArr = nowSelectNode['fileSubData'];
              var index = -1;
              for (var i = 0, l = dataArr.length; i < l; i++) {
                index = i;
                var row = dataArr[i];
                if (row['f_id'] == id) { // 判断是否为编辑行
                  var ParameterJSON = {};
                  ParameterJSON['f_name'] = row['f_name'];
                  ParameterJSON['fileTypeId'] = row['fileTypeId'];
                  ParameterJSON['fileTypeName'] = row['fileTypeName'];
                  ParameterJSON['checkFlowId'] = row['checkFlowId'];
                  ParameterJSON['checkFlowName'] = row['checkFlowName'];
                  ParameterJSON['f_id'] = row['f_id'];
                  var dlg = new SubDataEditDialog(editorUi, mxUtils.bind(this, function()
                  {
                    // 加载面板
                    $('body').mLoading('show');
                    // 延时执行
                    setTimeout(function(){
                      var jsonData = form2Json('subDataForm');
                      row['f_name'] = jsonData['f_name'];
                      row['f_check_flow'] = jsonData['f_check_flow'];
                      row['f_file_type'] = jsonData['f_file_type'];
                      $('#f_file_type').select2('data').forEach((item)=>{
                        row['fileTypeId'] = item.id;
                        row['fileTypeName'] = item.text;
                      });
                      $('#f_check_flow').select2('data').forEach((item)=>{
                        row['checkFlowId'] = item.id;
                        row['checkFlowName'] = item.text;
                      });
                      nowSelectNode['fileSubData'].splice(index, 1, row);
                      // 刷新节点
                      mainEditorUi.editor.graph.refresh(nowSelectNode);
                      // 结束操作
                      $('body').mLoading('hide');
                      toastr.success('保存成功！');
                      mainEditorUi.hideDialog();
                      // 重新构建table
                      $('#subDataTable').dataTable().fnClearTable();
                　    $('#subDataTable').dataTable().fnAddData(nowSelectNode['fileSubData'], true);
                    }, 100);
                  }), 2, ParameterJSON);
                  mainEditorUi.showDialog(dlg.container, 450, 185, true, true);
                  dlg.init();
                  break;
                }
              }
            }
            $('body').mLoading('hide');
          }, 200);
        };
        var actionDel = function(id)
        {
          var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 加载面板
            $('body').mLoading('show');
            setTimeout(function(){
              if (nowSelectNode && nowSelectNode['fileSubData']) {
                var dataArr = nowSelectNode['fileSubData'];
                var index = -1;
                for (var i = 0, l = dataArr.length; i < l; i++) {
                  index = i;
                  var row = dataArr[i];
                  if (row['f_id'] == id) {
                    break;
                  }
                }
                if (index != -1) {
                  nowSelectNode['fileSubData'].splice(index, 1);
                }
              }
              // 刷新节点
              mainEditorUi.editor.graph.refresh(nowSelectNode);
              // 结束操作
              $('body').mLoading('hide');
              toastr.success('删除成功！')
              mainEditorUi.hideDialog();
              // 重新构建table
              $('#subDataTable').dataTable().fnClearTable();
              if(nowSelectNode['fileSubData'].length > 0) {
        　      $('#subDataTable').dataTable().fnAddData(nowSelectNode['fileSubData'], true);
              }
            }, 200);
          }), '确定要删除当前记录吗?', 2);
          mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionEdit.toString()+')(\''+data+'\')">改</button>' +
          '<button type="button" class="geBtn geDangerBtn geBtnMini" onclick="('+actionDel.toString()+')(\''+data+'\')">删</button>';
      }
    });
  }
  $('#subDataTable').DataTable(subDataTableConfig);
}
/**
 * 附件表格面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function FileUploadLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '15px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 节点启动条件标题
  var nodeSpan = titleSpan.cloneNode(false);
  mxUtils.write(nodeSpan, '附件');
  div.appendChild(nodeSpan);
  // 表格数据
  if (isNull(cell['fileUploadData'])) {
    cell['fileUploadData'] = [];
  }
  // 新建按钮
  if (((function_all_op == 1 && function_fileUpLoad_op == 1)
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var i = document.createElement('i');
    i.setAttribute('class', 'fas fa-plus');
    var newButton = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new FileUploadEditDialog(editorUi, mxUtils.bind(this, function()
      {
        if($('#f_file').val() != '') {
          var form = new FormData(document.getElementById('fileUploadForm'));
          var jsonData = {};
          jsonData['f_id'] = guid();
          jsonData['f_node_id'] = cell.id;
          jsonData['f_name'] = form.get('f_name');
          jsonData['f_explain'] = form.get('f_explain');
          jsonData['f_path'] = '';
          jsonData['f_filename'] = '';
          jsonData['f_ext'] = '';
          if (function_all_op == 1 && projectEditNodeID == '') {
            jsonData['project'] = 1;
          } else {
            if (saveXMLArray.length > 0) {
              jsonData['child'] = 1;
            }
          }
          cell['fileUploadData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          editorUi.hideDialog();
          // 重新构建table
          $('#fileUploadTable').dataTable().fnClearTable();
    　    $('#fileUploadTable').dataTable().fnAddData(cell['fileUploadData'], true);
          // 存入节点附件待处理功能变量
          if (!nowNodeFileUploatSteps[cell.id]) {
            nowNodeFileUploatSteps[cell.id] = {add:[], update:[], del:[]};
          }
          nowNodeFileUploatSteps[cell.id]['add'].push({id: jsonData['f_id'], form: form});
        } else {
          toastr.error('请选择需要上传的文件');
        }
      }), 1);
      editorUi.showDialog(dlg.container, 350, 245, true, true);
      dlg.init();
    });
    newButton.style.marginLeft = '135px';
    newButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    newButton.appendChild(i);
    mxUtils.write(newButton, '新增');
    nodeSpan.appendChild(newButton);
  }
  // 附件列表
  var DateTable = document.createElement('table');
  DateTable.style.width = '225px';
  DateTable.style.color = 'rgb(112, 112, 112)';
  DateTable.style.margin = '5px 0 0 0';
  DateTable.setAttribute('id', 'fileUploadTable');
  DateTable.setAttribute('class', 'display compact');
  div.appendChild(DateTable);
  // 封装表格
  var fileUploadTableConfig = {
    data: cell['fileUploadData'],
    columns: [
      {
        data: 'f_name',
        render: (data, type, row, full) => '<div class="tip-hotspot" data-tip="'+(row.f_explain ? row.f_explain : row.f_name)+'">'+data+'</div>'
      }
    ],
    paging: false, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: false, // 是否允许Datatables开启本地搜索
    ordering: false, // 是否允许Datatables开启排序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  };
  // 预览按钮
  var protocol = window.location.protocol;
  var host = window.location.host;
  var url = protocol + '//' + host;
  fileUploadTableConfig.columns.push({
    data: 'f_id',
    width: 12,
    orderable: false,
    render: function (data, type, row) {
      if (row['f_path'] && row['f_path'] != '') {
        var actionDown = function(f_path, url)
        {
          var path = f_path;
          // var path_left = path.substring(0, path.indexOf('//Flow//')+8); // 本地测试
          var path_left = path.substring(0, path.indexOf('/attachment/')+12); // http://39.102.232.225:9196/
          var go_url_dow = '';
          go_url_dow = path.replace(path_left, url + '/download/').replace(/\/\//g, '/').replace(':/', '://');
          window.open(go_url_dow);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionDown.toString()+')(\''+row['f_path']+'\',\''+url+'\')"><i class="fa fa-download" style="margin-right: 0 !important;" aria-hidden="true"></i></button>';
      } else {
        return '';
      }
    }
  });
  // 编辑按钮
  if (((function_all_op == 1 && function_fileUpLoad_op == 1)
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    fileUploadTableConfig.columns.push({
      data: 'f_id',
      width: 33,
      orderable: false,
      render: function (data) {
        var actionEdit = function(id)
        {
          if (nowSelectNode && nowSelectNode['fileUploadData']) {
            var dataArr = nowSelectNode['fileUploadData'];
            var index = -1;
            for (var i = 0, l = dataArr.length; i < l; i++) {
              index = i;
              var row = dataArr[i];
              if (row['f_id'] == id) { // 判断是否为编辑行
                var dlg = new FileUploadEditDialog(editorUi, mxUtils.bind(this, function()
                {
                  var form = new FormData(document.getElementById('fileUploadForm'));
                  var child = row['child'];
                  var project = row['project'];
                  if (
                    (function_all_op == 1 && project && project == 1)
                    ||
                    (function_all_op == 1 && child && child == 1)
                    ||
                    (function_all_op == 1 && saveXMLArray.length == 0)
                    ) { // 判断是否可替换文件
                      row['f_name'] = form.get('f_name');
                      row['f_explain'] = form.get('f_explain');
                      nowSelectNode['fileUploadData'].splice(index, 1, row);
                      // 刷新节点
                      mainEditorUi.editor.graph.refresh(nowSelectNode);
                      // ↓↓↓ 启动保存按钮 开始 ↓↓↓
                      nowFlowSaveOp = 1;
                      EditorMenuSave(); // 可保存状态
                      // ↑↑↑ 启动保存按钮 结束 ↑↑↑
                      mainEditorUi.hideDialog();
                      // 重新构建table
                      $('#fileUploadTable').dataTable().fnClearTable();
                      $('#fileUploadTable').dataTable().fnAddData(nowSelectNode['fileUploadData'], true);
                      // 存入节点附件待处理功能变量
                      if (!nowNodeFileUploatSteps[nowSelectNode.id]) {
                        nowNodeFileUploatSteps[nowSelectNode.id] = {add:[], update:[], del:[]};
                      }
                      nowNodeFileUploatSteps[nowSelectNode.id]['update'].push({id: row['f_id'], form: form});
                  }
                }), 2, row);
                mainEditorUi.showDialog(dlg.container, 350, 245, true, true);
                dlg.init();
                break;
              }
            }
          }
        };
        var actionDel = function(id)
        {
          var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            if (nowSelectNode && nowSelectNode['fileUploadData']) {
              var dataArr = nowSelectNode['fileUploadData'];
              var index = -1;
              for (var i = 0, l = dataArr.length; i < l; i++) {
                index = i;
                if (dataArr[i]['f_id'] == id) {
                  break;
                }
              }
              if (index != -1) {
                var row = dataArr[index];
                var child = row['child'];
                var project = row['project'];
                if (
                  (function_all_op == 1 && project && project == 1)
                  ||
                  (function_all_op == 1 && child && child == 1)
                  ||
                  (function_all_op == 1 && saveXMLArray.length == 0)
                  ) {
                    nowSelectNode['fileUploadData'].splice(index, 1);
                    // 刷新节点
                    mainEditorUi.editor.graph.refresh(nowSelectNode);
                    // ↓↓↓ 启动保存按钮 开始 ↓↓↓
                    nowFlowSaveOp = 1;
                    EditorMenuSave(); // 可保存状态
                    // ↑↑↑ 启动保存按钮 结束 ↑↑↑
                    mainEditorUi.hideDialog();
                    // 重新构建table
                    $('#fileUploadTable').dataTable().fnClearTable();
                    if(nowSelectNode['fileUploadData'].length > 0) {
                      $('#fileUploadTable').dataTable().fnAddData(nowSelectNode['fileUploadData'], true);
                    }
                    // 存入节点附件待处理功能变量
                    if (!nowNodeFileUploatSteps[nowSelectNode.id]) {
                      nowNodeFileUploatSteps[nowSelectNode.id] = {add:[], update:[], del:[]};
                    }
                    nowNodeFileUploatSteps[nowSelectNode.id]['del'].push({id: row['f_id'], row: row});
                }
              }
            }
          }), '确定要删除当前记录吗?', 2);
          mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionEdit.toString()+')(\''+data+'\')">改</button>' +
          '<button type="button" class="geBtn geDangerBtn geBtnMini" onclick="('+actionDel.toString()+')(\''+data+'\')">删</button>';
      }
    });
  }
  $('#fileUploadTable').DataTable(fileUploadTableConfig);
}
/**
 * 变量表格面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function VariableLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 表格数据
  if (isNull(cell['variableData'])) {
    cell['variableData'] = [];
  }
  // 新建(设计参数)按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var i = document.createElement('i');
    i.setAttribute('class', 'fas fa-plus');
    var newConstantButton = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new ConstantEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('constantForm');
          jsonData['f_id'] = guid();
          jsonData['f_type'] = 2; // 1方法 2设计参数
          jsonData['f_node_id'] = cell.id;
          $("#f_value_unit").select2("data").forEach((item)=>{
            jsonData['ValueUnitId'] = item.id;
            jsonData['ValueUnitName'] = item.text;
          });
          cell['variableData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#variableTable').dataTable().fnClearTable();
    　    $('#variableTable').dataTable().fnAddData(cell['variableData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 270, 228, true, true);
      dlg.init();
    });
    newConstantButton.style.marginTop = '5px';
    newConstantButton.style.marginLeft = '78px';
    newConstantButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    newConstantButton.appendChild(i);
    mxUtils.write(newConstantButton, '设计参数');
    this.container.appendChild(newConstantButton);
  }
  // 新建(方法)按钮
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var i = document.createElement('i');
    i.setAttribute('class', 'fas fa-plus');
    var newFunctionButton = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new VariableEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('variableForm');
          jsonData['f_id'] = guid();
          jsonData['f_type'] = 1; // 1方法 2设计参数
          jsonData['f_node_id'] = cell.id;
          var data = [];
          $('#f_input_parameter').select2('data').forEach((item)=>{
            data.push({
              f_variable_id: item.id,
              f_name: item.text,
              f_node_id: item.f_node_id
            });
          });
          jsonData['f_input_parameter'] = data;
          jsonData['f_function'] = dlg.f_functionCodeMirror.getValue();
          cell['variableData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#variableTable').dataTable().fnClearTable();
    　    $('#variableTable').dataTable().fnAddData(cell['variableData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 1000, 410, true, true);
      dlg.init();
    });
    newFunctionButton.style.marginTop = '3px';
    newFunctionButton.style.marginLeft = '5px';
    newFunctionButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    newFunctionButton.appendChild(i);
    mxUtils.write(newFunctionButton, '方法');
    this.container.appendChild(newFunctionButton);
  }
  // 变量列表
  var DateTable = document.createElement('table');
  DateTable.style.width = '225px';
  DateTable.style.color = 'rgb(112, 112, 112)';
  DateTable.style.margin = '5px 0 0 0';
  DateTable.setAttribute('id', 'variableTable');
  DateTable.setAttribute('class', 'display compact');
  this.container.appendChild(DateTable);
  // 封装表格
  var variableTableConfig = {
    data: cell['variableData'],
    columns: [
      {
        data: 'f_name',
        render: (data, type, row, full) => '<div class="tip-hotspot" data-tip="'+(row.f_value ? row.f_value : row.f_function)+'">'+data+'</div>'
      }
    ],
    paging: false, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: false, // 是否允许Datatables开启本地搜索
    ordering: false, // 是否允许Datatables开启排序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  };
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    variableTableConfig.columns.push({
      data: 'f_id',
      width: 33,
      orderable: false,
      render: function (data) {
        var actionEdit = function(id)
        {
          // 加载面板
          $('body').mLoading('show');
          setTimeout(function(){
            if (nowSelectNode && nowSelectNode['variableData']) {
              var dataArr = nowSelectNode['variableData'];
              var index = -1;
              for (var i = 0, l = dataArr.length; i < l; i++) {
                index = i;
                var row = dataArr[i];
                if (row['f_id'] == id) { // 判断是否为编辑行
                  if (row['f_type'] == 1) { // 方法
                    var ParameterJSON = {};
                    ParameterJSON['f_name'] = row['f_name'];
                    ParameterJSON['f_action'] = row['f_action'];
                    ParameterJSON['f_input_parameter'] = row['f_input_parameter'];
                    ParameterJSON['f_return_type'] = row['f_return_type'];
                    ParameterJSON['f_function'] = row['f_function'];
                    ParameterJSON['f_id'] = row['f_id'];
                    var dlg = new VariableEditDialog(editorUi, mxUtils.bind(this, function()
                    {
                      // 加载面板
                      $('body').mLoading('show');
                      // 延时执行
                      setTimeout(function(){
                        var jsonData = form2Json('variableForm');
                        row['f_name'] = jsonData['f_name'];
                        row['f_action'] = jsonData['f_action'];
                        var data = [];
                        $('#f_input_parameter').select2('data').forEach((item)=>{
                          data.push({
                            f_variable_id: item.id,
                            f_name: item.text,
                            f_node_id: item.f_node_id
                          });
                        });
                        row['f_input_parameter'] = data;
                        row['f_return_type'] = jsonData['f_return_type'];
                        row['f_function'] = dlg.f_functionCodeMirror.getValue();
                        nowSelectNode['variableData'].splice(index, 1, row);
                        // 刷新节点
                        mainEditorUi.editor.graph.refresh(nowSelectNode);
                        // 结束操作
                        $('body').mLoading('hide');
                        toastr.success('保存成功！');
                        mainEditorUi.hideDialog();
                        // 重新构建table
                        $('#variableTable').dataTable().fnClearTable();
                  　    $('#variableTable').dataTable().fnAddData(nowSelectNode['variableData'], true);
                      }, 100);
                    }), 2, ParameterJSON);
                    mainEditorUi.showDialog(dlg.container, 1000, 410, true, true);
                    dlg.init(ParameterJSON);
                  } else if (row['f_type'] == 2) { // 设计参数
                    var ParameterJSON = {};
                    ParameterJSON['f_name'] = row['f_name'];
                    ParameterJSON['f_return_type'] = row['f_return_type'];
                    ParameterJSON['f_value'] = row['f_value'];
                    ParameterJSON['f_value_unit'] = row['f_value_unit'];
                    ParameterJSON['ValueUnitId'] = row['ValueUnitId'];
                    ParameterJSON['ValueUnitName'] = row['ValueUnitName'];
                    ParameterJSON['f_id'] = row['f_id'];
                    var dlg = new ConstantEditDialog(editorUi, mxUtils.bind(this, function()
                    {
                      // 加载面板
                      $('body').mLoading('show');
                      // 延时执行
                      setTimeout(function(){
                        var jsonData = form2Json('constantForm');
                        row['f_name'] = jsonData['f_name'];
                        row['f_return_type'] = jsonData['f_return_type'];
                        row['f_value'] = jsonData['f_value'];
                        row['f_value_unit'] = jsonData['f_value_unit'];
                        $('#f_value_unit').select2('data').forEach((item)=>{
                          row['ValueUnitId'] = item.id;
                          row['ValueUnitName'] = item.text;
                        });
                        nowSelectNode['variableData'].splice(index, 1, row);
                        // 刷新节点
                        mainEditorUi.editor.graph.refresh(nowSelectNode);
                        // 结束操作
                        $('body').mLoading('hide');
                        toastr.success('保存成功！');
                        mainEditorUi.hideDialog();
                        // 重新构建table
                        $('#variableTable').dataTable().fnClearTable();
                  　    $('#variableTable').dataTable().fnAddData(nowSelectNode['variableData'], true);
                      }, 100);
                    }), 2, ParameterJSON);
                    mainEditorUi.showDialog(dlg.container, 270, 228, true, true);
                    dlg.init();
                  }
                  break;
                }
              }
            }
            $('body').mLoading('hide');
          }, 200);
        };
        var actionDel = function(id)
        {
          var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 加载面板
            $('body').mLoading('show');
            setTimeout(function(){
              if (nowSelectNode && nowSelectNode['variableData']) {
                var dataArr = nowSelectNode['variableData'];
                var index = -1;
                for (var i = 0, l = dataArr.length; i < l; i++) {
                  index = i;
                  var row = dataArr[i];
                  if (row['f_id'] == id) {
                    break;
                  }
                }
                if (index != -1) {
                  nowSelectNode['variableData'].splice(index, 1);
                }
              }
              // 刷新节点
              mainEditorUi.editor.graph.refresh(nowSelectNode);
              // 结束操作
              $('body').mLoading('hide');
              toastr.success('删除成功！')
              mainEditorUi.hideDialog();
              // 重新构建table
              $('#variableTable').dataTable().fnClearTable();
              if(nowSelectNode['variableData'].length > 0) {
        　      $('#variableTable').dataTable().fnAddData(nowSelectNode['variableData'], true);
              }
            }, 200);
          }), '确定要删除当前记录吗?', 2);
          mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionEdit.toString()+')(\''+data+'\')">改</button>' +
          '<button type="button" class="geBtn geDangerBtn geBtnMini" onclick="('+actionDel.toString()+')(\''+data+'\')">删</button>';
      }
    });
  }
  $('#variableTable').DataTable(variableTableConfig);
}
/**********************************
 * 规则面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 **********************************/
function RuleLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 节点启动条件面板
  StartNodeLogicPanel(this.editorUi, this.container, cell);
  // 节点完成条件面板
  EndNodeLogicPanel(this.editorUi, this.container, cell);
}
/**
 * 节点启动条件面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function StartNodeLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 节点启动条件标题
  var nodeStartSpan = titleSpan.cloneNode(false);
  mxUtils.write(nodeStartSpan, '启动条件');
  div.appendChild(nodeStartSpan);
  // 添加按钮(节点)
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_Start_NewNode = document.createElement('i');
    I_Start_NewNode.setAttribute('class', 'fas fa-plus');
    var Button_Start_NewNode = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new NodeStartNodeEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('nodeStartNodeForm');
          jsonData['f_id'] = guid();
          jsonData['f_type'] = 1; // 1节点 2变量
          cell['nodeStartData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#nodeStartTable').dataTable().fnClearTable();
    　    $('#nodeStartTable').dataTable().fnAddData(cell['nodeStartData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 280, 138, true, true);
      dlg.init();
    });
    Button_Start_NewNode.style.marginLeft = '45px';
    Button_Start_NewNode.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_Start_NewNode.appendChild(I_Start_NewNode);
    mxUtils.write(Button_Start_NewNode, '节点');
    nodeStartSpan.appendChild(Button_Start_NewNode);
  }
  // 添加按钮(方法)
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_Start_NewConstant = document.createElement('i');
    I_Start_NewConstant.setAttribute('class', 'fas fa-plus');
    var Button_Start_NewConstant = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new NodeStartConstantEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('nodeStartVariableForm');
          jsonData['f_id'] = guid();
          jsonData['f_type'] = 2; // 1节点 2变量
          var data = [];
          $('#f_input_parameter').select2('data').forEach((item)=>{
            data.push({
              f_variable_id: item.id,
              f_name: item.text,
              f_node_id: item.f_node_id
            });
          });
          jsonData['f_input_parameter'] = data;
          jsonData['f_function'] = dlg.f_functionCodeMirror.getValue();
          cell['nodeStartData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#nodeStartTable').dataTable().fnClearTable();
    　    $('#nodeStartTable').dataTable().fnAddData(cell['nodeStartData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 1000, 410, true, true);
      dlg.init();
    });
    Button_Start_NewConstant.style.marginLeft = '5px';
    Button_Start_NewConstant.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_Start_NewConstant.appendChild(I_Start_NewConstant);
    mxUtils.write(Button_Start_NewConstant, '方法');
    nodeStartSpan.appendChild(Button_Start_NewConstant);
  }
  // 表格数据
  if (isNull(cell['nodeStartData'])) {
    cell['nodeStartData'] = [];
  }
  // 节点开始条件列表
  var DateTable_NodeStart = document.createElement('table');
  DateTable_NodeStart.style.width = '225px';
  DateTable_NodeStart.style.color = 'rgb(112, 112, 112)';
  DateTable_NodeStart.style.margin = '5px 0 0 0';
  DateTable_NodeStart.setAttribute('id', 'nodeStartTable');
  DateTable_NodeStart.setAttribute('class', 'display compact');
  div.appendChild(DateTable_NodeStart);
  // 封装表格
  var nodeStartTableConfig = {
    data: cell['nodeStartData'],
    columns: [
      {
        data: 'f_type',
        render: function (data, type, row, full) {
          if (data == 1) { // 节点
            var graph = mainEditorUi.editor.graph;
            var cells = graph.getModel().cells;
            var cell = cells[row.f_node];
            return cell.value.replace(/<[^<>]+>/g,'') + '【' + (row.f_node_state==1 ? '开始' : '完成') + '】';
          } else if (data == 2) { // 变量
            return '<div class="tip-hotspot" data-tip="'+row.f_function+'">'+row.f_name+'</div>';
          }
        }
      }
    ],
    paging: false, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: false, // 是否允许Datatables开启本地搜索
    ordering: false, // 是否允许Datatables开启排序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  }
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    nodeStartTableConfig.columns.push({
      data: 'f_id',
      width: 33,
      orderable: false,
      render: function (data) {
        var actionEdit = function(id)
        {
          // 加载面板
          $('body').mLoading('show');
          setTimeout(function(){
            if (nowSelectNode && nowSelectNode['nodeStartData']) {
              var dataArr = nowSelectNode['nodeStartData'];
              var index = -1;
              for (var i = 0, l = dataArr.length; i < l; i++) {
                index = i;
                var row = dataArr[i];
                if (row['f_id'] == id) { // 判断是否为编辑行
                  if (row['f_type'] == 1) { // 节点
                    var ParameterJSON = {};
                    ParameterJSON['f_node'] = row['f_node'];
                    ParameterJSON['f_node_state'] = row['f_node_state'];
                    ParameterJSON['f_id'] = row['f_id'];
                    var dlg = new NodeStartNodeEditDialog(editorUi, mxUtils.bind(this, function()
                    {
                      // 加载面板
                      $('body').mLoading('show');
                      // 延时执行
                      setTimeout(function(){
                        var jsonData = form2Json('nodeStartNodeForm');
                        row['f_node'] = jsonData['f_node'];
                        row['f_node_state'] = jsonData['f_node_state'];
                        nowSelectNode['nodeStartData'].splice(index, 1, row);
                        // 刷新节点
                        mainEditorUi.editor.graph.refresh(nowSelectNode);
                        // 结束操作
                        $('body').mLoading('hide');
                        toastr.success('保存成功！');
                        mainEditorUi.hideDialog();
                        // 重新构建table
                        $('#nodeStartTable').dataTable().fnClearTable();
                  　    $('#nodeStartTable').dataTable().fnAddData(nowSelectNode['nodeStartData'], true);
                      }, 100);
                    }), 2, ParameterJSON);
                    mainEditorUi.showDialog(dlg.container, 280, 138, true, true);
                    dlg.init(ParameterJSON);
                  } else if (row['f_type'] == 2) { // 方法
                    var ParameterJSON = {};
                    ParameterJSON['f_name'] = row['f_name'];
                    ParameterJSON['f_input_parameter'] = row['f_input_parameter'];
                    ParameterJSON['f_function'] = row['f_function'];
                    ParameterJSON['f_id'] = row['f_id'];
                    var dlg = new NodeStartConstantEditDialog(editorUi, mxUtils.bind(this, function()
                    {
                      // 加载面板
                      $('body').mLoading('show');
                      // 延时执行
                      setTimeout(function(){
                        var jsonData = form2Json('nodeStartVariableForm');
                        row['f_name'] = jsonData['f_name'];
                        var data = [];
                        $('#f_input_parameter').select2('data').forEach((item)=>{
                          data.push({
                            f_variable_id: item.id,
                            f_name: item.text,
                            f_node_id: item.f_node_id
                          });
                        });
                        row['f_input_parameter'] = data;
                        row['f_function'] = dlg.f_functionCodeMirror.getValue();
                        nowSelectNode['nodeStartData'].splice(index, 1, row);
                        // 刷新节点
                        mainEditorUi.editor.graph.refresh(nowSelectNode);
                        // 结束操作
                        $('body').mLoading('hide');
                        toastr.success('保存成功！');
                        mainEditorUi.hideDialog();
                        // 重新构建table
                        $('#nodeStartTable').dataTable().fnClearTable();
                  　    $('#nodeStartTable').dataTable().fnAddData(nowSelectNode['nodeStartData'], true);
                      }, 100);
                    }), 2, ParameterJSON);
                    mainEditorUi.showDialog(dlg.container, 1000, 410, true, true);
                    dlg.init(ParameterJSON);
                  }
                  break;
                }
              }
            }
            $('body').mLoading('hide');
          }, 200);
        };
        var actionDel = function(id)
        {
          var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 加载面板
            $('body').mLoading('show');
            setTimeout(function(){
              if (nowSelectNode && nowSelectNode['nodeStartData']) {
                var dataArr = nowSelectNode['nodeStartData'];
                var index = -1;
                for (var i = 0, l = dataArr.length; i < l; i++) {
                  index = i;
                  var row = dataArr[i];
                  if (row['f_id'] == id) {
                    break;
                  }
                }
                if (index != -1) {
                  nowSelectNode['nodeStartData'].splice(index, 1);
                }
              }
              // 刷新节点
              mainEditorUi.editor.graph.refresh(nowSelectNode);
              // 结束操作
              $('body').mLoading('hide');
              toastr.success('删除成功！')
              mainEditorUi.hideDialog();
              // 重新构建table
              $('#nodeStartTable').dataTable().fnClearTable();
              if(nowSelectNode['nodeStartData'].length > 0) {
        　      $('#nodeStartTable').dataTable().fnAddData(nowSelectNode['nodeStartData'], true);
              }
            }, 200);
          }), '确定要删除当前记录吗?', 2);
          mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionEdit.toString()+')(\''+data+'\')">改</button>' +
          '<button type="button" class="geBtn geDangerBtn geBtnMini" onclick="('+actionDel.toString()+')(\''+data+'\')">删</button>';
      }
    });
  }
  $('#nodeStartTable').DataTable(nodeStartTableConfig);
}
/**
 * 节点完成条件面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function EndNodeLogicPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  // 主体
  var div = document.createElement('div');
  this.container.appendChild(div);
  // 标题
  var titleSpan = document.createElement('span');
  titleSpan.style.display = 'block';
  titleSpan.style.fontWeight = '600';
  titleSpan.style.padding = '5px 15px 0 0';
  titleSpan.style.color = '#2460bf';
  titleSpan.style.fontSize = '15px';
  // 正文
  var contentSpan = document.createElement('span');
  contentSpan.style.display = 'block';
  contentSpan.style.fontWeight = '600';
  contentSpan.style.padding = '0 15px 0 0';
  contentSpan.style.color = 'rgb(82, 82, 82)';
  contentSpan.style.fontSize = '14px';
  // 节点完成条件标题
  var nodeEndSpan = titleSpan.cloneNode(false);
  nodeEndSpan.style.marginTop = '12px';
  mxUtils.write(nodeEndSpan, '完成条件');
  div.appendChild(nodeEndSpan);
  // 添加按钮(节点)
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_End_NewNode = document.createElement('i');
    I_End_NewNode.setAttribute('class', 'fas fa-plus');
    var Button_End_NewNode = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new NodeEndNodeEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('nodeEndNodeForm');
          jsonData['f_id'] = guid();
          jsonData['f_type'] = 1; // 1节点 2变量
          cell['nodeEndData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#nodeEndTable').dataTable().fnClearTable();
    　    $('#nodeEndTable').dataTable().fnAddData(cell['nodeEndData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 280, 138, true, true);
      dlg.init();
    });
    Button_End_NewNode.style.marginLeft = '45px';
    Button_End_NewNode.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_End_NewNode.appendChild(I_End_NewNode);
    mxUtils.write(Button_End_NewNode, '节点');
    nodeEndSpan.appendChild(Button_End_NewNode);
  }
  // 添加按钮(方法)
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    var I_End_NewConstant = document.createElement('i');
    I_End_NewConstant.setAttribute('class', 'fas fa-plus');
    var Button_End_NewConstant = mxUtils.button('', function()
    {
      // 打开面板
      var dlg = new NodeEndConstantEditDialog(editorUi, mxUtils.bind(this, function()
      {
        // 加载面板
        $('body').mLoading('show');
        // 延时执行
        setTimeout(function(){
          var jsonData = form2Json('nodeEndVariableForm');
          jsonData['f_id'] = guid();
          jsonData['f_type'] = 2; // 1节点 2变量
          var data = [];
          $('#f_input_parameter').select2('data').forEach((item)=>{
            data.push({
              f_variable_id: item.id,
              f_name: item.text,
              f_node_id: item.f_node_id
            });
          });
          jsonData['f_input_parameter'] = data;
          jsonData['f_function'] = dlg.f_functionCodeMirror.getValue();
          cell['nodeEndData'].push(jsonData);
          // 刷新节点
          mainEditorUi.editor.graph.refresh(cell);
          // ↓↓↓ 启动保存按钮 开始 ↓↓↓
          nowFlowSaveOp = 1;
          EditorMenuSave(); // 可保存状态
          // ↑↑↑ 启动保存按钮 结束 ↑↑↑
          $('body').mLoading('hide');
          toastr.success('保存成功！');
          editorUi.hideDialog();
          // 重新构建table
          $('#nodeEndTable').dataTable().fnClearTable();
    　    $('#nodeEndTable').dataTable().fnAddData(cell['nodeEndData'], true);
        }, 100);
      }), 1);
      editorUi.showDialog(dlg.container, 1000, 410, true, true);
      dlg.init();
    });
    Button_End_NewConstant.style.marginLeft = '5px';
    Button_End_NewConstant.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
    Button_End_NewConstant.appendChild(I_End_NewConstant);
    mxUtils.write(Button_End_NewConstant, '方法');
    nodeEndSpan.appendChild(Button_End_NewConstant);
  }
  // 表格数据
  if (isNull(cell['nodeEndData'])) {
    cell['nodeEndData'] = [];
  }
  // 节点开始条件列表
  var DateTable_NodeEnd = document.createElement('table');
  DateTable_NodeEnd.style.width = '225px';
  DateTable_NodeEnd.style.color = 'rgb(112, 112, 112)';
  DateTable_NodeEnd.style.margin = '5px 0 0 0';
  DateTable_NodeEnd.setAttribute('id', 'nodeEndTable');
  DateTable_NodeEnd.setAttribute('class', 'display compact');
  div.appendChild(DateTable_NodeEnd);
  // 封装表格
  var nodeEndTableConfig = {
    data: cell['nodeEndData'],
    columns: [
      {
        data: 'f_type',
        render: function (data, type, row, full) {
          if (data == 1) { // 节点
            var graph = mainEditorUi.editor.graph;
            var cells = graph.getModel().cells;
            var cell = cells[row.f_node];
            return cell.value.replace(/<[^<>]+>/g,'') + '【' + (row.f_node_state==1 ? '开始' : '完成') + '】';
          } else if (data == 2) { // 变量
            return '<div class="tip-hotspot" data-tip="'+row.f_function+'">'+row.f_name+'</div>';
          }
        }
      }
    ],
    paging: false, // 是否开启本地分页
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: false, // 是否允许Datatables开启本地搜索
    ordering: false, // 是否允许Datatables开启排序
    info: false, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  };
  if ((function_all_op == 1
    || ( projectEditNodeID != '' // 该条件仅适用于工作流节点编辑
        && (saveXMLArray.length+1 == projectPath.length)
        && (projectPath[projectPath.length-1] == cell.id)
        && ( (saveXMLArray.length == 0 && projectPath.length == 1)
              ||
             (saveXMLArray[saveXMLArray.length-1].nodeId == projectPath[projectPath.length-2])
            ) 
        ) ) && projectEditState == 2) {
    nodeEndTableConfig.columns.push({
      data: 'f_id',
      width: 33,
      orderable: false,
      render: function (data) {
        var actionEdit = function(id)
        {
          // 加载面板
          $('body').mLoading('show');
          setTimeout(function(){
            if (nowSelectNode && nowSelectNode['nodeEndData']) {
              var dataArr = nowSelectNode['nodeEndData'];
              var index = -1;
              for (var i = 0, l = dataArr.length; i < l; i++) {
                index = i;
                var row = dataArr[i];
                if (row['f_id'] == id) { // 判断是否为编辑行
                  if (row['f_type'] == 1) { // 节点
                    var ParameterJSON = {};
                    ParameterJSON['f_node'] = row['f_node'];
                    ParameterJSON['f_node_state'] = row['f_node_state'];
                    ParameterJSON['f_id'] = row['f_id'];
                    var dlg = new NodeEndNodeEditDialog(editorUi, mxUtils.bind(this, function()
                    {
                      // 加载面板
                      $('body').mLoading('show');
                      // 延时执行
                      setTimeout(function(){
                        var jsonData = form2Json('nodeEndNodeForm');
                        row['f_node'] = jsonData['f_node'];
                        row['f_node_state'] = jsonData['f_node_state'];
                        nowSelectNode['nodeEndData'].splice(index, 1, row);
                        // 刷新节点
                        mainEditorUi.editor.graph.refresh(nowSelectNode);
                        // 结束操作
                        $('body').mLoading('hide');
                        toastr.success('保存成功！');
                        mainEditorUi.hideDialog();
                        // 重新构建table
                        $('#nodeEndTable').dataTable().fnClearTable();
                  　    $('#nodeEndTable').dataTable().fnAddData(nowSelectNode['nodeEndData'], true);
                      }, 100);
                    }), 2, ParameterJSON);
                    mainEditorUi.showDialog(dlg.container, 280, 138, true, true);
                    dlg.init(ParameterJSON);
                  } else if (row['f_type'] == 2) { // 方法
                    var ParameterJSON = {};
                    ParameterJSON['f_name'] = row['f_name'];
                    ParameterJSON['f_input_parameter'] = row['f_input_parameter'];
                    ParameterJSON['f_function'] = row['f_function'];
                    ParameterJSON['f_id'] = row['f_id'];
                    var dlg = new NodeEndConstantEditDialog(editorUi, mxUtils.bind(this, function()
                    {
                      // 加载面板
                      $('body').mLoading('show');
                      // 延时执行
                      setTimeout(function(){
                        var jsonData = form2Json('nodeEndVariableForm');
                        row['f_name'] = jsonData['f_name'];
                        var data = [];
                        $('#f_input_parameter').select2('data').forEach((item)=>{
                          data.push({
                            f_variable_id: item.id,
                            f_name: item.text,
                            f_node_id: item.f_node_id
                          });
                        });
                        row['f_input_parameter'] = data;
                        row['f_function'] = dlg.f_functionCodeMirror.getValue();
                        nowSelectNode['nodeEndData'].splice(index, 1, row);
                        // 刷新节点
                        mainEditorUi.editor.graph.refresh(nowSelectNode);
                        // 结束操作
                        $('body').mLoading('hide');
                        toastr.success('保存成功！');
                        mainEditorUi.hideDialog();
                        // 重新构建table
                        $('#nodeEndTable').dataTable().fnClearTable();
                  　    $('#nodeEndTable').dataTable().fnAddData(nowSelectNode['nodeEndData'], true);
                      }, 100);
                    }), 2, ParameterJSON);
                    mainEditorUi.showDialog(dlg.container, 1000, 410, true, true);
                    dlg.init(ParameterJSON);
                  }
                  break;
                }
              }
            }
            $('body').mLoading('hide');
          }, 200);
        };
        var actionDel = function(id)
        {
          var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 加载面板
            $('body').mLoading('show');
            setTimeout(function(){
              if (nowSelectNode && nowSelectNode['nodeEndData']) {
                var dataArr = nowSelectNode['nodeEndData'];
                var index = -1;
                for (var i = 0, l = dataArr.length; i < l; i++) {
                  index = i;
                  var row = dataArr[i];
                  if (row['f_id'] == id) {
                    break;
                  }
                }
                if (index != -1) {
                  nowSelectNode['nodeEndData'].splice(index, 1);
                }
              }
              // 刷新节点
              mainEditorUi.editor.graph.refresh(nowSelectNode);
              // 结束操作
              $('body').mLoading('hide');
              toastr.success('删除成功！')
              mainEditorUi.hideDialog();
              // 重新构建table
              $('#nodeEndTable').dataTable().fnClearTable();
              if(nowSelectNode['nodeEndData'].length > 0) {
        　      $('#nodeEndTable').dataTable().fnAddData(nowSelectNode['nodeEndData'], true);
              }
            }, 200);
          }), '确定要删除当前记录吗?', 2);
          mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
        };
        return '<button type="button" class="geBtn gePrimaryBtn geBtnMini" onclick="('+actionEdit.toString()+')(\''+data+'\')">改</button>' +
          '<button type="button" class="geBtn geDangerBtn geBtnMini" onclick="('+actionDel.toString()+')(\''+data+'\')">删</button>';
      }
    });
  }
  $('#nodeEndTable').DataTable(nodeEndTableConfig);
}