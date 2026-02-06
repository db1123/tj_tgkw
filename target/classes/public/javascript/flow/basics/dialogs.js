/**
 * 弹出提示框
 */
var TipsDialog = function(editorUi, fn, label, type, ParameterJSON)
{
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.marginTop = '8px';
  table.style.width = '100%';
  table.style.height = '100%';
  var row, td;
  // 提示文本框
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.verticalAlign = 'top';
  td.style.margin = '8px 20px';
  td.style.fontSize = '1em';
  td.style.fontWeight = 600;
  td.innerHTML = label;
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.height = '30px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button(fn == null ? '关闭' : '取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  td.appendChild(cancelBtn);
  // 确定按钮
  if (fn != null) {
    var genericBtn;
    if (isNull(type) || type == 1) { // 普通
    genericBtn = mxUtils.button('确定', function()
    {
      editorUi.hideDialog();
      fn(ParameterJSON);
    });
    genericBtn.className = 'geBtn gePrimaryBtn';
    } else { // 删除
    genericBtn = mxUtils.button('删除', function()
    {
        editorUi.hideDialog();
        fn(ParameterJSON);
    });
    genericBtn.className = 'geBtn geDangerBtn';
    }
    td.appendChild(genericBtn);
  }
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  // 放入展示面板
  this.container = table;
};
 
/**
 * 弹出脚本查询框
 */
var ScriptTableDialog = function(editorUi)
{
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.margin = '0';
  table.style.width = '100%';
  table.style.height = '100%';
  var row, td;
  // 列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'viewScriptTable');
  DateTable.setAttribute('class', 'fTable display compact');
  td.appendChild(DateTable);
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  // 放入展示面板
  this.container = table;
  // 初始化
  this.init = function()
  {
    // 封装表格
    $('#viewScriptTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/script/queryScript', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length // 每页显示条数
          } ) );
        },
        dataSrc: function (json) {
          json.recordsFiltered = json.total;  // 总行数
          json.recordsTotal = json.page; // 当前页数
          return json.list;
        },
        error: function (xhr, status, error) {
          toastr.error(xhr);
        }
      },
      columns: [
        {
          title: '脚本类型',
          data: 'f_type',
          width: 60,
          render: function (data) {
            var str = '';
            switch (data) {
              case 1:
                str = 'javascript';
                break;
            
              default:
                str = '其它';
                break;
            }
            return str;
          }
        },
        { title: '标题', data: 'f_title' },
        {
          title: '说明',
          data: 'key',
          width: 60,
          orderable: false,
          render: function (data) {
            return '<button type="button" class="geBtn geSuccessBtn" onclick="ScriptTableDialogInfoAction(\''+data+'\')"><i class="fas fa-question-circle"></i>说明</button>';
          }
        },
        {
          title: '脚本列表',
          data: 'f_state',
          width: 60,
          orderable: false,
          render: function (data, type, row) {
            return '<button type="button" class="geBtn geInfoBtn" onclick="ScriptTableDialogListAction(\''+row.key+'\')"><i class="fas fa-file mr-2"></i>列表</button>';
          }
        }
      ],
      paging: true, // 是否开启本地分页
      pageLength: 16, // 改变初始化页长度（每页多少条数据）
      pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: false, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
  }
};
var ScriptTableDialogInfoAction = function(id)
{
  // 加载面板
  $('body').mLoading('show');
  setTimeout(function(){
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/script/queryScriptInfo', // 调用地址
      data: JSON.stringify({
        id: id
      }),
      async: false,
      success: function(data) {
        $('body').mLoading('hide');
        if (data.result == 'success') {
          var dlg = new TipsDialog(mainEditorUi, null, data['info']['f_memo']);
          mainEditorUi.showDialog(dlg.container, 700, 500, true, true);
        }
      },
      error : function(e){
        $('body').mLoading('hide');
        toastr.error(e.status);
        toastr.error(e.responseText);
      }
    });
  }, 200);
};
var ScriptTableDialogListAction = function(id)
{
  // 加载面板
  var dlg = new ScriptFileTableDialog(mainEditorUi);
  mainEditorUi.showDialog(dlg.container, 600, 350, true, true);
  dlg.init(id);
};
/**
 * 弹出脚本附件查询框
 */
var ScriptFileTableDialog = function(editorUi)
{
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.margin = '0';
  table.style.width = '100%';
  table.style.height = '100%';
  var row, td;
  // 列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'viewScriptFileTable');
  DateTable.setAttribute('class', 'fTable display compact');
  td.appendChild(DateTable);
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  // 放入展示面板
  this.container = table;
  // 初始化
  this.init = function(id)
  {
    // 封装表格
    $('#viewScriptFileTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/script/queryScriptFile', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            scriptID: id
          } ) );
        },
        dataSrc: function (json) {
          json.recordsFiltered = json.total;  // 总行数
          json.recordsTotal = json.page; // 当前页数
          return json.list;
        },
        error: function (xhr, status, error) {
          toastr.error(xhr);
        }
      },
      columns: [
        { title: '文件名', data: 'f_filename', orderable: false }
      ],
      paging: true, // 是否开启本地分页
      pageLength: 16, // 改变初始化页长度（每页多少条数据）
      pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: false, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
  }
};

/**
 * 弹出流程图编辑查询框
 */
var FlowTableDialog = function(editorUi)
{
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.margin = '0';
  table.style.width = '100%';
  table.style.height = '100%';
  var row, td;
  // 查询列表
  row = document.createElement('tr');
  row.style.height = '29px';
  td = document.createElement('td');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '200px';
  var NameInput = document.createElement('input');
  NameInput.style.marginBottom = '3px';
  NameInput.setAttribute('class', 'form-control form-flow-text-name');
  NameInput.style.width = '200px';
  td.appendChild(NameInput);
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '模板:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '100px';
  var ShareSelect = document.createElement('select');
  ShareSelect.style.marginBottom = '3px';
  ShareSelect.options.add(new Option('全部', -1));
  ShareSelect.options.add(new Option('未创建', 0));
  ShareSelect.options.add(new Option('已创建', 1));
  ShareSelect.setAttribute('class', 'form-control form-flow-select-share');
  ShareSelect.style.width = '100px';
  td.appendChild(ShareSelect);
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '状态:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '100px';
  var StateSelect = document.createElement('select');
  StateSelect.style.marginBottom = '3px';
  StateSelect.options.add(new Option('全部', -1));
  StateSelect.options.add(new Option('未提交', 0));
  StateSelect.options.add(new Option('已提交', 1));
  StateSelect.setAttribute('class', 'form-control form-flow-select-state');
  StateSelect.style.width = '100px';
  td.appendChild(StateSelect);
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '80px';
  var I_Search = document.createElement('i');
  I_Search.setAttribute('class', 'fas fa-search');
  var Button_Search = mxUtils.button('', function()
  {
    $('#mainFlowTable').DataTable().ajax.reload(null, false);
  });
  Button_Search.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Search.appendChild(I_Search);
  mxUtils.write(Button_Search, '查询');
  td.appendChild(Button_Search);
  row.appendChild(td);
  tbody.appendChild(row);
  // 流程图列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainFlowTable');
  DateTable.setAttribute('class', 'display compact');
  td.appendChild(DateTable);
  row.appendChild(td);
  tbody.appendChild(row);
  // 写入表格
  table.appendChild(tbody);
  // 放入展示面板
  this.container = table;
  // 初始化
  this.init = function()
    {
    // 封装表格
    $('#mainFlowTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/flow/queryFlow', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            type: 2,
            name: $('.form-flow-text-name').val(),
            share: $('.form-flow-select-share').val(),
            state: $('.form-flow-select-state').val()
          } ) );
        },
        dataSrc: function (json) {
          json.recordsFiltered = json.total;  // 总行数
          json.recordsTotal = json.page; // 当前页数
          return json.list;
        },
        error: function (xhr, status, error) {
          toastr.error(xhr);
        }
      },
      columns: [
        {
          title: '名称',
          data: 'f_name',
          render: function (data, type, row) {
            if (row['key'] == nowFlowID) {
              return '<i class="fas fa-hand-point-right"></i>&nbsp;' + data;
            } else {
              return data;
            }
          }
        },
        //{ title: '说明', data: 'f_explain', orderable: false},
        { title: '创建时间', data: 'f_c_date', width: 120 },
        {
          title: '提交',
          data: 'f_state',
          width: 60,
          orderable: false,
          render: function (data, type, row) {
            if (data == 1) {
              return  '<b>已提交</b>';
              // return  '<button type="button" class="geBtn" disabled="disabled">已提交</button>';
            } else {
              return  '<b>编辑中</b>';
              // return  '<button type="button" class="geBtn geInfoBtn" onclick="FlowTableDialogSubmitAction(\''+row.key+'\')"><i class="fas fa-thumbs-up"></i>提交</button>';
            }
          }
        },
        {
          title: '模板',
          data: 'f_share',
          width: 100,
          orderable: false,
          render: function (data, type, row) {
            var title = '';
            var message = '';
            if (data == 1) {
              title = '取消';
              message = '确定要取消创建为模板吗?';
            } else {
              title = '<i class="fas fa-share-alt"></i>创建为模板';
              message = '确定要把当前记录创建为模板吗，创建模板后流程图将对所有有模板展示权限的人可见?';
            }
            return  '<button type="button" class="geBtn geSuccessBtn" onclick="FlowTableDialogShareAction(\''+row.key+'\','+data+',\''+message+'\')">'+title+'</button>';
          }
        },
        {
          title: '操作',
          data: 'key',
          width: 110,
          orderable: false,
          render: function (data, type, row) {
            var temp = '';
            temp += '<button type="button" class="geBtn gePrimaryBtn" onclick="FlowTableDialogChooseAction(\''+data+'\','+row['f_state']+')"><i class="fas fa-folder-open"></i>选择</button>';
            if (data == nowFlowID || row['f_state'] == 1) {
              temp +=  '';
            } else {
              temp +=  '<button type="button" class="geBtn geDangerBtn" onclick="FlowTableDialogDelAction(\''+data+'\')"><i class="fas fa-trash"></i>删除</button>';
            }
            return temp;
          }
        }
      ],
      paging: true, // 是否开启本地分页
      pageLength: 16, // 改变初始化页长度（每页多少条数据）
      pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: false, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      order: [[1, "desc"]], // 默认按时间倒序
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
  }
};
var FlowTableDialogShareAction = function(id, share, message)
{
  var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
  {
    // 加载面板
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/s/flow/shareFlow',
        data: JSON.stringify({
          id: id,
          share: share
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            $('#mainFlowTable').DataTable().ajax.reload(null, false);
            toastr.success('设置成功！');
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
  }), message, 1);
  mainEditorUi.showDialog(dlg.container, 480, 80, true, true);
};
var FlowTableDialogSubmitAction = function(id)
{
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
          id: id,
          state: 0
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            //$('#mainFlowTable').DataTable().ajax.reload(null, false);
            nowFlowState = 1;
            EditorUiSubmit(); // 流程图编辑器-提交状态
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
  }), '确定要提交当前记录吗，提交后记录将无法修改?', 1);
  mainEditorUi.showDialog(dlg.container, 320, 80, true, true);
};
var FlowTableDialogChooseAction = function(id, state)
{
  // 加载面板
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
          // 打开流程图
          var editor = mainEditorUi.editor;
          var graph = editor.graph;
          var doc = mxUtils.parseXml(data['info']['f_xml']);
          editor.setGraphXml(doc.documentElement);
          // 初始化系统参数
          nowFlowID = id;
          nowFlowName = data['info']['f_name'];
          nowFlowExplain = data['info']['f_explain'];
          nowFlowSaveOp = 0;
          nowFlowState = state;
          saveXMLArray = []; // 顶级-二级-三级...
          // 设置编辑器状态
          if (nowFlowState == 1) { // 提交
            EditorUiSubmit(); // 禁止流程图编辑
            EditorMenuNoSaveNoSubmit(); // 无需保存提交状态
            // 鼠标拖拽图层-开启
            graph.panningHandler.ignoreCell = true;
          } else {
            EditorUiEdit(); // 启用流程图编辑
            EditorMenuSubmit(); // 可提交状态
            graph.panningHandler.ignoreCell = false; // 鼠标拖拽图层-关闭
          }
          // 回执
          toastr.success('加载成功！');
          mainEditorUi.hideDialog();
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
};
var FlowTableDialogDelAction = function(id)
{
  var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
  {
    // 加载面板
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/s/flow/delFlow',
        data: JSON.stringify({
          id: id
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            $('#mainFlowTable').DataTable().ajax.reload(null, false);
            toastr.success('删除成功！')
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

  }), '确定要删除当前记录吗?', 2);
  mainEditorUi.showDialog(dlg.container, 300, 80, true, true);
};

/**
* 弹出流程图编辑提示框
*/
var FlowEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'flowForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
    nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '255px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 说明
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '102px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '说明:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var explainTextarea = document.createElement('textarea');
  explainTextarea.style.marginBottom = '1px';
    explainTextarea.setAttribute('id', 'f_explain');
    explainTextarea.setAttribute('name', 'f_explain');
  if (op == 1) { // 新建
    mxUtils.write(explainTextarea, '');
  } else { // 修改
    mxUtils.write(explainTextarea, ParameterJSON['f_explain']);
  }
  explainTextarea.setAttribute('rows', '5');
  explainTextarea.setAttribute('cols', '34');
  explainTextarea.setAttribute('class', 'form-control');
  td.appendChild(explainTextarea);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 初始化
  this.init = function()
  {
    that.validator = $('#flowForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_explain: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请输入名称"
        },
        f_explain: {
          required: "*请输入说明"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
  // 放入展示面板
  this.container = form;
};
 
/**
* 弹出URL编辑提示框
*/
var URLEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'urlForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
    nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '420px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- URL
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, 'URL:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var valueInput = document.createElement('input');
  valueInput.style.marginBottom = '3px';
  valueInput.setAttribute('id', 'f_url');
  valueInput.setAttribute('name', 'f_url');
  if (op == 1) { // 新建
    valueInput.setAttribute('value', '');
  } else { // 修改
    valueInput.setAttribute('value', ParameterJSON['f_url']);
  }
  valueInput.setAttribute('class', 'form-control');
  valueInput.style.width = '420px';
  td.appendChild(valueInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
    {
    that.validator = $('#urlForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_url: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请填写名称"
        },
        f_url: {
          required: "*请填写URL"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 说明编辑提示框
 */
var MemoSetDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'memoSetForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 说明
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '102px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '说明:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var explainTextarea = document.createElement('textarea');
  explainTextarea.style.marginBottom = '1px';
  explainTextarea.setAttribute('id', 'memo');
  explainTextarea.setAttribute('name', 'memo');
  if (op == 1) { // 新建
    mxUtils.write(explainTextarea, '');
  } else { // 修改
    mxUtils.write(explainTextarea, ParameterJSON['memo']);
  }
  explainTextarea.setAttribute('rows', '10');
  explainTextarea.setAttribute('cols', '45');
  explainTextarea.setAttribute('class', 'form-control');
  td.appendChild(explainTextarea);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
    editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    that.validator = $('#memoSetForm').validate({
      rules: {
        memo: {
          required: true
        }
      },
      messages: {
        memo: {
          required: "*请填写说明"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
  
/**
 * 弹出方法编辑提示框
 */
var VariableEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'variableForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '360px';
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
    nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '350px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 函数
  td = document.createElement('td');
  td.rowSpan = 4;
  td.style.paddingTop = '6px';
  td.style.paddingLeft = '5px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '方法:');
  row.appendChild(td);
  td = document.createElement('td');
  td.rowSpan = 4;
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var functionTextarea = document.createElement('textarea');
  functionTextarea.style.marginBottom = '1px';
  functionTextarea.setAttribute('id', 'f_function');
  functionTextarea.setAttribute('name', 'f_function');
  if (op == 1) { // 新建
    mxUtils.write(functionTextarea, '');
  } else { // 修改
    mxUtils.write(functionTextarea, ParameterJSON['f_function']);
  }
  functionTextarea.setAttribute('class', 'form-control');
  td.appendChild(functionTextarea);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 执行方式
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '执行方式:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var actionSelect = document.createElement('select');
  actionSelect.style.marginBottom = '3px';
  actionSelect.setAttribute('id', 'f_action');
  actionSelect.setAttribute('name', 'f_action');
  actionSelect.options.add(new Option('覆盖原有值', 1));
  actionSelect.options.add(new Option('执行结果累加', 2));
  if (op == 2) { // 修改
    for (var i = 0; i < actionSelect.options.length; i++) {
      if (actionSelect.options[i].value == ParameterJSON['f_action']) {
        actionSelect.options[i].selected = true;
        break;
      }
    }
  }
  actionSelect.setAttribute('class', 'form-control');
  actionSelect.style.width = '354px';
  actionSelect.style.height = '23px';
  td.appendChild(actionSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 输出参数
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '返回参数类型:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var returnTypeSelect = document.createElement('select');
  returnTypeSelect.style.marginBottom = '3px';
    returnTypeSelect.setAttribute('id', 'f_return_type');
  returnTypeSelect.setAttribute('name', 'f_return_type');
  returnTypeSelect.options.add(new Option('数值', 1));
  returnTypeSelect.options.add(new Option('字符串', 2));
  if (op == 2) { // 修改
    for (var i = 0; i < returnTypeSelect.options.length; i++) {
      if (returnTypeSelect.options[i].value == ParameterJSON['f_return_type']) {
        returnTypeSelect.options[i].selected = true;
        break;
      }
    }
  }
  returnTypeSelect.setAttribute('class', 'form-control');
  returnTypeSelect.style.width = '204px';
  returnTypeSelect.style.height = '23px';
  td.appendChild(returnTypeSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 输入参数
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.minHeight = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '输入参数:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var inputParameterSelect = document.createElement('select');
  inputParameterSelect.style.width = '355px';
  inputParameterSelect.style.marginBottom = '3px';
  inputParameterSelect.setAttribute('id', 'f_input_parameter');
  inputParameterSelect.setAttribute('name', 'f_input_parameter');
  inputParameterSelect.setAttribute('multiple', 'multiple');
  inputParameterSelect.setAttribute('class', 'form-control');
  td.appendChild(inputParameterSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- ID
  var idHidden = document.createElement('input');
  idHidden.setAttribute('type', 'hidden');
  idHidden.setAttribute('id', 'f_id');
  idHidden.setAttribute('name', 'f_id');
  if (op == 1) { // 新建
    idHidden.setAttribute('value', '');
  } else { // 修改
    idHidden.setAttribute('value', ParameterJSON['f_id']);
  }
  form.appendChild(idHidden);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 4;
  td.style.paddingTop = '15px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 测试代码按钮
  var that = this;
  var textBtn = mxUtils.button('测试代码', function()
  {
  testFunctionAction($('#f_input_parameter'), that.f_functionCodeMirror.getValue());
  });
  textBtn.className = 'geBtn geSuccessBtn';
  textBtn.setAttribute('type', 'button');
  td.appendChild(textBtn);
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function(ParameterJSON)
  {
    // 初始化代码编辑器
    this.f_functionCodeMirror = CodeMirror.fromTextArea($('#f_function')[0], {
      lineNumbers: true,// 是否显示行号
      mode: 'javascript', // 编辑器语言
      theme: 'dracula', // 编辑器主题
      matchBrackets: true, // 匹配括号
      styleActiveLine: true // 设置光标所在行高亮
    });
    this.f_functionCodeMirror.setSize('500px','380px');
    // 获取祖先节点数据集
    nowNodeInputParameterData = [];
    nowNodeInputParameterCount = {};
    recursionFlowCellVariable(nowSelectNode);
    // 初始化输入参数
    var inputParameterSelect = $('#f_input_parameter').select2({
      data: nowNodeInputParameterData,
      language: "zh-CN"
    });
    if (ParameterJSON) { // 修改
      var values = [];
      ParameterJSON['f_input_parameter'].forEach((item)=>{
        values.push(item.f_variable_id);
      });
      inputParameterSelect.val(values).trigger('change');
    }
    // 初始化效验工具
    that.validator = $('#variableForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_return_type: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请填写名称"
        },
        f_return_type: {
          required: "*请选择返回参数类型"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        if (that.f_functionCodeMirror.getValue() == '') {
          toastr.error('请填写 javascript 函数');
        } else {
          fn();
        }
      }
    });
  }
}
 
/**
 * 弹出常量编辑提示框
 */
var ConstantEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'constantForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
  nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '200px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 参数类型
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '类型:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var returnTypeSelect = document.createElement('select');
  returnTypeSelect.style.marginBottom = '3px';
  returnTypeSelect.setAttribute('id', 'f_return_type');
  returnTypeSelect.setAttribute('name', 'f_return_type');
  returnTypeSelect.options.add(new Option('数值', 1));
  returnTypeSelect.options.add(new Option('字符串', 2));
  if (op == 2) { // 修改
    for (var i = 0; i < returnTypeSelect.options.length; i++){
      if (returnTypeSelect.options[i].value == ParameterJSON['f_return_type']){
        returnTypeSelect.options[i].selected = true;
        break;
      }
    }
  }
  returnTypeSelect.setAttribute('class', 'form-control');
  returnTypeSelect.style.width = '204px';
  returnTypeSelect.style.height = '23px';
  td.appendChild(returnTypeSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 参数值
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '值:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var valueInput = document.createElement('input');
  valueInput.style.marginBottom = '3px';
  valueInput.setAttribute('id', 'f_value');
  valueInput.setAttribute('name', 'f_value');
  if (op == 1) { // 新建
    valueInput.setAttribute('value', '');
  } else { // 修改
    valueInput.setAttribute('value', ParameterJSON['f_value']);
  }
  valueInput.setAttribute('class', 'form-control');
  valueInput.style.width = '200px';
  td.appendChild(valueInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 单位
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '单位:');
  row.appendChild(td);
  // 编辑区域 -- 单位-下拉列表
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var type_select = document.createElement('select');
  type_select.style.width = "200px";
  type_select.setAttribute('id', 'f_value_unit');
  type_select.setAttribute('name', 'f_value_unit');
  td.appendChild(type_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 单位-修改状态
  if (op == 2 && ParameterJSON['ValueUnitId'] && ParameterJSON['ValueUnitName']) {
    var option = new Option(ParameterJSON['ValueUnitName'], ParameterJSON['ValueUnitId'], true, true);
    type_select.append(option);
  }
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    // 单位-封装下拉列表
    $('#f_value_unit').select2({
      placeholder: '单位',
      ajax: {
        type:'POST',
        url: "/s/unit/queryUnitSelect",
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
    // 表单
    that.validator = $('#constantForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_return_type: {
          required: true
        },
        f_value: {
          required: true
        },
        f_value_unit: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请填写名称"
        },
        f_return_type: {
          required: "*请选择参数类型"
        },
        f_value: {
          required: "*请填写参数值"
        },
        f_value_unit: {
          required: "*请选择单位"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 上传规则编辑提示框
 */
var UploadFileSetDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'uploadFileSetForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 数量
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '25px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '上传数量:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var numInput = document.createElement('input');
  numInput.style.width = '50px';
  numInput.style.marginBottom = '3px';
  numInput.style.imeMode = 'Disabled';
  numInput.setAttribute('id', 'f_num');
  numInput.setAttribute('name', 'f_num');
  if (op == 1) { // 新建
    numInput.setAttribute('value', '');
  } else { // 修改
    numInput.setAttribute('value', ParameterJSON['f_num']);
  }
  numInput.setAttribute('class', 'form-control');
  numInput.setAttribute('onkeyup', 'value=value.replace(/[^\\d]/g,\'\')');
  numInput.setAttribute('onbeforepaste', 'clipboardData.setData(\'text\',clipboardData.getData(\'text\').replace(/[^\\d]/g,\'\'))');
  td.appendChild(numInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 类型-编辑区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '文件类型:');
  row.appendChild(td);
  // 类型-下拉列表
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var ext_select = document.createElement('select');
  ext_select.style.width = "425px";
  ext_select.setAttribute('id', 'f_file_ext');
  ext_select.setAttribute('name', 'f_file_ext');
  ext_select.setAttribute('multiple', 'multiple');
  td.appendChild(ext_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 类型-修改状态
  if (op == 2 && ParameterJSON['fileExts']) {
    ParameterJSON['fileExts'].forEach((item)=>{
      var option = new Option(item.fileExtName, item.fileExtId, true, true);
      ext_select.append(option);
    });
  }
  // 类别-编辑区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '160px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '文件类别:');
  row.appendChild(td);
  // 类别-下拉列表
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var type_select = document.createElement('select');
  type_select.style.width = "425px";
  type_select.setAttribute('id', 'f_file_type');
  type_select.setAttribute('name', 'f_file_type');
  type_select.setAttribute('multiple', 'multiple');
  td.appendChild(type_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 类别-修改状态
  if (op == 2 && ParameterJSON['fileTypes']) {
    ParameterJSON['fileTypes'].forEach((item)=>{
      var option = new Option(item.fileTypeName, item.fileTypeId, true, true);
      type_select.append(option);
    });
  }
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.fileExtSelect;
  this.fileTypeSelect;
  this.init = function()
    {
    // 类型-封装下拉列表
    $('#f_file_ext').select2({
      ajax: {
        type:'POST',
        url: "/s/flow/queryFlowCellFileExtSelect",
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
    // 类别-封装下拉列表
    $('#f_file_type').select2({
      ajax: {
        type:'POST',
        url: "/s/flow/queryFlowCellFileTypeSelect",
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
    // 表单验证
    that.validator = $('#uploadFileSetForm').validate({
      rules: {
        f_num: {
          required: true
        },
        f_file_ext: {
          required: true
        },
        f_file_type: {
          required: true
        }
      },
      messages: {
        f_num: {
          required: "*请填写上传限制文件数"
        },
        f_file_ext: {
          required: "*请选择上传文件扩展名"
        },
        f_file_type: {
          required: "*请选择上传文件类别"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 交付物编辑提示框
 */
var SubDataEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'subDataForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 交付物名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '交付物名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
  nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '320px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 交付物类型
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '交付物类型:');
  row.appendChild(td);
  // 编辑区域 -- 交付物类型-下拉列表
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var type_select = document.createElement('select');
  type_select.style.width = "325px";
  type_select.setAttribute('id', 'f_file_type');
  type_select.setAttribute('name', 'f_file_type');
  td.appendChild(type_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 交付物类型-修改状态
  if (op == 2 && ParameterJSON['fileTypeId'] && ParameterJSON['fileTypeName']) {
    var option = new Option(ParameterJSON['fileTypeName'], ParameterJSON['fileTypeId'], true, true);
    type_select.append(option);
  }
  // 编辑区域 -- 审核流程
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '审核流程:');
  row.appendChild(td);
  // 编辑区域 -- 审核流程-下拉列表
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var flow_select = document.createElement('select');
  flow_select.style.width = "325px";
  flow_select.setAttribute('id', 'f_check_flow');
  flow_select.setAttribute('name', 'f_check_flow');
  td.appendChild(flow_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 审核流程-修改状态
  if (op == 2 && ParameterJSON['checkFlowId'] && ParameterJSON['checkFlowName']) {
    var option = new Option(ParameterJSON['checkFlowName'], ParameterJSON['checkFlowId'], true, true);
    flow_select.append(option);
  }
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
    editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.fileExtSelect;
  this.fileTypeSelect;
  this.init = function()
  {
    // 交付物类型-封装下拉列表
    $('#f_file_type').select2({
      ajax: {
        type:'POST',
        url: "/s/flow/queryFlowCellFileTypeSelect",
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
    // 审核流程-封装下拉列表
    $('#f_check_flow').select2({
      ajax: {
        type:'POST',
        url: "/s/flow/queryFlowSelect",
        dataType: 'json',
        data: function (params) {
          return {
            search: params.term,
            view: 'work',
            type: 3
          };
        },
        delay: 800,
        cache: true
      },
      language: "zh-CN"
    });
    // 表单验证
    that.validator = $('#subDataForm').validate({
      rules: {
        f_num: {
          required: true
        },
        f_file_ext: {
          required: true
        },
        f_file_type: {
          required: true
        }
      },
      messages: {
        f_num: {
          required: "*请填写上传限制文件数"
        },
        f_file_ext: {
          required: "*请选择上传文件扩展名"
        },
        f_file_type: {
          required: "*请选择上传文件类别"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
  
/**
 * 附件编辑提示框
 */
var FileUploadEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'fileUploadForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  form.setAttribute('enctype', 'multipart/form-data');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
  nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '280px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 说明
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '102px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '说明:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var explainTextarea = document.createElement('textarea');
  explainTextarea.style.marginBottom = '1px';
  explainTextarea.setAttribute('id', 'f_explain');
  explainTextarea.setAttribute('name', 'f_explain');
  if (op == 1) { // 新建
    mxUtils.write(explainTextarea, '');
  } else { // 修改
    mxUtils.write(explainTextarea, ParameterJSON['f_explain']);
  }
  explainTextarea.setAttribute('rows', '5');
  explainTextarea.setAttribute('cols', '38');
  explainTextarea.setAttribute('class', 'form-control');
  td.appendChild(explainTextarea);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 附件
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '附件:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var fileNameSpan = document.createElement('span');
  fileNameSpan.style.fontWeight = 600;
  if (op == 2) { // 修改
    mxUtils.write(fileNameSpan, ParameterJSON['f_filename']);
  }
  td.appendChild(fileNameSpan);
  var fileInput = document.createElement('input');
  fileInput.style.marginBottom = '3px';
  fileInput.setAttribute('id', 'f_file');
  fileInput.setAttribute('name', 'f_file');
  fileInput.setAttribute('type', 'file');
  fileInput.setAttribute('class', 'form-control');
  fileInput.style.width = '280px';
  td.appendChild(fileInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    that.validator = $('#fileUploadForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_explain: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请填写名称"
        },
        f_explain: {
          required: "*请填写说明"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 工作周期编辑提示框
 */
var WorkNumSetDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'workNumSetForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 数量
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '工作周期(天):');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var urlInput = document.createElement('input');
  urlInput.style.width = '150px';
  urlInput.style.marginBottom = '3px';
  urlInput.style.imeMode = 'Disabled';
  urlInput.setAttribute('id', 'work_num');
  urlInput.setAttribute('name', 'work_num');
  if (op == 1) { // 新建
    urlInput.setAttribute('value', '');
  } else { // 修改
    urlInput.setAttribute('value', ParameterJSON['work_num']);
  }
  urlInput.setAttribute('class', 'form-control');
  td.appendChild(urlInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    that.validator = $('#workNumSetForm').validate({
      rules: {
        work_num: {
          required: true
        }
      },
      messages: {
        work_num: {
          required: "*请填写工作周期"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 起始日期编辑提示框
 */
var StartEndSetDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'startEndSetForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 起始日期
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '12px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '起始日期');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var StartEndInput = document.createElement('div');
  StartEndInput.style.width = '190px';
  StartEndInput.setAttribute('id', 'start_end_date');
  StartEndInput.setAttribute('class', 'form-control date-input');
  td.appendChild(StartEndInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    that.validator = $('#startEndSetForm').validate({
      rules: {
        start_end_date: {
          required: true
        }
      },
      messages: {
        start_end_date: {
          required: "*请填写起始日期"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
    new XNDatepicker($("#start_end_date"),{
      type: 'daterange',
      startTime: ParameterJSON['start_date'],
      endTime: ParameterJSON['end_date'],
      minDate: '2020-01-01'
    },function(data){
      $('#start_end_date').attr('start_date', data.startTime.format('YYYY-MM-DD'));
      $('#start_end_date').attr('end_date', data.endTime.format('YYYY-MM-DD'));
    },);
  }
}
 
/**
 * 弹出节点启动规则节点编辑提示框
 */
var NodeStartNodeEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'nodeStartNodeForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 节点
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '30px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '节点:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nodeSelect = document.createElement('select');
  nodeSelect.style.width = '204px';
  nodeSelect.style.height = '23px';
  nodeSelect.style.marginBottom = '3px';
  nodeSelect.setAttribute('id', 'f_node');
  nodeSelect.setAttribute('name', 'f_node');
  nodeSelect.setAttribute('class', 'form-control');
  td.appendChild(nodeSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  nowNodeInputParameterCount = {};
  recursionFlowCell(nowSelectNode, nodeSelect);
  if (op == 2) { // 修改
    for (var i = 0; i < nodeSelect.options.length; i++) {
      if (nodeSelect.options[i].value == ParameterJSON['f_node']) {
        nodeSelect.options[i].selected = true;
        break;
      }
    }
  }
  // 编辑区域 -- 状态
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '16px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '30px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '状态:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nodeStateSelect = document.createElement('select');
  nodeStateSelect.style.width = '204px';
  nodeStateSelect.style.height = '23px';
  nodeStateSelect.style.marginBottom = '3px';
  nodeStateSelect.setAttribute('id', 'f_node_state');
  nodeStateSelect.setAttribute('name', 'f_node_state');
  nodeStateSelect.setAttribute('class', 'form-control');
  nodeStateSelect.options.add(new Option('开始', 1));
  nodeStateSelect.options.add(new Option('完成', 2));
  if (op == 2) { // 修改
    for (var i = 0; i < nodeStateSelect.options.length; i++) {
      if (nodeStateSelect.options[i].value == ParameterJSON['f_node_state']) {
        nodeStateSelect.options[i].selected = true;
        break;
      }
    }
  }
  td.appendChild(nodeStateSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- ID
  var idHidden = document.createElement('input');
  idHidden.setAttribute('type', 'hidden');
  idHidden.setAttribute('id', 'f_id');
  idHidden.setAttribute('name', 'f_id');
  if (op == 1) { // 新建
    idHidden.setAttribute('value', '');
  } else { // 修改
    idHidden.setAttribute('value', ParameterJSON['f_id']);
  }
  form.appendChild(idHidden);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '15px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    // 初始化效验工具
    that.validator = $('#nodeStartNodeForm').validate({
      rules: {
        f_node: {
          required: true
        },
        f_node_state: {
          required: true
        }
      },
      messages: {
        f_node: {
          required: "*请选择节点"
        },
        f_node_state: {
          required: "*请选择节点状态"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 弹出节点启动规则方法编辑提示框
 */
var NodeStartConstantEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'nodeStartVariableForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '360px';
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
  nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '350px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 函数
  td = document.createElement('td');
  td.rowSpan = 3;
  td.style.paddingTop = '6px';
  td.style.paddingLeft = '5px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '方法:');
  row.appendChild(td);
  td = document.createElement('td');
  td.rowSpan = 3;
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var functionTextarea = document.createElement('textarea');
  functionTextarea.style.marginBottom = '1px';
  functionTextarea.setAttribute('id', 'f_function');
  functionTextarea.setAttribute('name', 'f_function');
  if (op == 1) { // 新建
    mxUtils.write(functionTextarea, '');
  } else { // 修改
    mxUtils.write(functionTextarea, ParameterJSON['f_function']);
  }
  functionTextarea.setAttribute('class', 'form-control');
  td.appendChild(functionTextarea);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 输出参数
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '25px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '返回参数说明:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '25px';
  td.style.color = 'red';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  mxUtils.write(td, '返回值必须为Boolean型，true表示满足条件，false表示未通过');
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 输入参数
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.minHeight = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '输入参数:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var inputParameterSelect = document.createElement('select');
  inputParameterSelect.style.width = '355px';
  inputParameterSelect.style.marginBottom = '3px';
  inputParameterSelect.setAttribute('id', 'f_input_parameter');
  inputParameterSelect.setAttribute('name', 'f_input_parameter');
  inputParameterSelect.setAttribute('multiple', 'multiple');
  inputParameterSelect.setAttribute('class', 'form-control');
  td.appendChild(inputParameterSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- ID
  var idHidden = document.createElement('input');
  idHidden.setAttribute('type', 'hidden');
  idHidden.setAttribute('id', 'f_id');
  idHidden.setAttribute('name', 'f_id');
  if (op == 1) { // 新建
    idHidden.setAttribute('value', '');
  } else { // 修改
    idHidden.setAttribute('value', ParameterJSON['f_id']);
  }
  form.appendChild(idHidden);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 4;
  td.style.paddingTop = '15px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 测试代码按钮
  var that = this;
  var textBtn = mxUtils.button('测试代码', function()
  {
  testFunctionAction($('#f_input_parameter'), that.f_functionCodeMirror.getValue());
  });
  textBtn.className = 'geBtn geSuccessBtn';
  textBtn.setAttribute('type', 'button');
  td.appendChild(textBtn);
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function(ParameterJSON)
  {
    var graph = mainEditorUi.editor.graph;
    var cells = graph.getModel().cells;
    // 初始化代码编辑器
    this.f_functionCodeMirror = CodeMirror.fromTextArea($('#f_function')[0], {
      lineNumbers: true,// 是否显示行号
      mode: 'javascript', // 编辑器语言
      theme: 'dracula', // 编辑器主题
      matchBrackets: true, // 匹配括号
      styleActiveLine: true // 设置光标所在行高亮
    });
    this.f_functionCodeMirror.setSize('500px','380px');
    // 获取祖先节点数据集
    nowNodeInputParameterData = [];
    nowNodeInputParameterCount = {};
    recursionFlowCellVariable(nowSelectNode);
    // 初始化输入参数
    var inputParameterSelect = $('#f_input_parameter').select2({
      data: nowNodeInputParameterData,
      language: "zh-CN"
    });
    if (ParameterJSON) { // 修改
      var values = [];
      ParameterJSON['f_input_parameter'].forEach((item)=>{
        values.push(item.f_variable_id);
      });
      inputParameterSelect.val(values).trigger("change");
    }
    // 初始化效验工具
    that.validator = $('#nodeStartVariableForm').validate({
      rules: {
        f_name: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请填写名称"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        if (that.f_functionCodeMirror.getValue() == '') {
          toastr.error('请填写 javascript 函数');
        } else {
          fn();
        }
      }
    });
  }
}
 
/**
 * 弹出节点完成规则节点编辑提示框
 */
var NodeEndNodeEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'nodeEndNodeForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 节点
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '30px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '节点:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nodeSelect = document.createElement('select');
  nodeSelect.style.width = '204px';
  nodeSelect.style.height = '23px';
  nodeSelect.style.marginBottom = '3px';
  nodeSelect.setAttribute('id', 'f_node');
  nodeSelect.setAttribute('name', 'f_node');
  nodeSelect.setAttribute('class', 'form-control');
  td.appendChild(nodeSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  nowNodeInputParameterCount = {};
  recursionFlowCell(nowSelectNode, nodeSelect);
  if (op == 2) { // 修改
    for (var i = 0; i < nodeSelect.options.length; i++) {
      if (nodeSelect.options[i].value == ParameterJSON['f_node']) {
        nodeSelect.options[i].selected = true;
        break;
      }
    }
  }
  // 编辑区域 -- 状态
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '16px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '30px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '状态:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.paddingTop = '10px';
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nodeStateSelect = document.createElement('select');
  nodeStateSelect.style.width = '204px';
  nodeStateSelect.style.height = '23px';
  nodeStateSelect.style.marginBottom = '3px';
  nodeStateSelect.setAttribute('id', 'f_node_state');
  nodeStateSelect.setAttribute('name', 'f_node_state');
  nodeStateSelect.setAttribute('class', 'form-control');
  nodeStateSelect.options.add(new Option('开始', 1));
  nodeStateSelect.options.add(new Option('完成', 2));
  if (op == 2) { // 修改
    for (var i = 0; i < nodeStateSelect.options.length; i++) {
      if (nodeStateSelect.options[i].value == ParameterJSON['f_node_state']) {
        nodeStateSelect.options[i].selected = true;
        break;
      }
    }
  }
  td.appendChild(nodeStateSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- ID
  var idHidden = document.createElement('input');
  idHidden.setAttribute('type', 'hidden');
  idHidden.setAttribute('id', 'f_id');
  idHidden.setAttribute('name', 'f_id');
  if (op == 1) { // 新建
    idHidden.setAttribute('value', '');
  } else { // 修改
    idHidden.setAttribute('value', ParameterJSON['f_id']);
  }
  form.appendChild(idHidden);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '15px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    // 初始化效验工具
    that.validator = $('#nodeEndNodeForm').validate({
      rules: {
        f_node: {
          required: true
        },
        f_node_state: {
          required: true
        }
      },
      messages: {
        f_node: {
          required: "*请选择节点"
        },
        f_node_state: {
          required: "*请选择节点状态"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
 * 弹出节点完成规则方法编辑提示框
 */
var NodeEndConstantEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'nodeEndVariableForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 名称
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '360px';
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var nameInput = document.createElement('input');
  nameInput.style.marginBottom = '3px';
  nameInput.setAttribute('id', 'f_name');
  nameInput.setAttribute('name', 'f_name');
  if (op == 1) { // 新建
    nameInput.setAttribute('value', '');
  } else { // 修改
    nameInput.setAttribute('value', ParameterJSON['f_name']);
  }
  nameInput.setAttribute('class', 'form-control');
  nameInput.style.width = '350px';
  td.appendChild(nameInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 函数
  td = document.createElement('td');
  td.rowSpan = 3;
  td.style.paddingTop = '6px';
  td.style.paddingLeft = '5px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '方法:');
  row.appendChild(td);
  td = document.createElement('td');
  td.rowSpan = 3;
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var functionTextarea = document.createElement('textarea');
  functionTextarea.style.marginBottom = '1px';
  functionTextarea.setAttribute('id', 'f_function');
  functionTextarea.setAttribute('name', 'f_function');
  if (op == 1) { // 新建
    mxUtils.write(functionTextarea, '');
  } else { // 修改
    mxUtils.write(functionTextarea, ParameterJSON['f_function']);
  }
  functionTextarea.setAttribute('class', 'form-control');
  td.appendChild(functionTextarea);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 输出参数
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '25px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '返回参数说明:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '25px';
  td.style.color = 'red';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  mxUtils.write(td, '返回值必须为Boolean型，true表示满足条件，false表示未通过');
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- 输入参数
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '6px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.minHeight = '35px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '输入参数:');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var inputParameterSelect = document.createElement('select');
  inputParameterSelect.style.width = '355px';
  inputParameterSelect.style.marginBottom = '3px';
  inputParameterSelect.setAttribute('id', 'f_input_parameter');
  inputParameterSelect.setAttribute('name', 'f_input_parameter');
  inputParameterSelect.setAttribute('multiple', 'multiple');
  inputParameterSelect.setAttribute('class', 'form-control');
  td.appendChild(inputParameterSelect);
  row.appendChild(td);
  tbody.appendChild(row);
  // 编辑区域 -- ID
  var idHidden = document.createElement('input');
  idHidden.setAttribute('type', 'hidden');
  idHidden.setAttribute('id', 'f_id');
  idHidden.setAttribute('name', 'f_id');
  if (op == 1) { // 新建
    idHidden.setAttribute('value', '');
  } else { // 修改
    idHidden.setAttribute('value', ParameterJSON['f_id']);
  }
  form.appendChild(idHidden);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 4;
  td.style.paddingTop = '15px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 测试代码按钮
  var that = this;
  var textBtn = mxUtils.button('测试代码', function()
  {
  testFunctionAction($('#f_input_parameter'), that.f_functionCodeMirror.getValue());
  });
  textBtn.className = 'geBtn geSuccessBtn';
  textBtn.setAttribute('type', 'button');
  td.appendChild(textBtn);
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function(ParameterJSON)
    {
    var graph = mainEditorUi.editor.graph;
    var cells = graph.getModel().cells;
    // 初始化代码编辑器
    this.f_functionCodeMirror = CodeMirror.fromTextArea($('#f_function')[0], {
      lineNumbers: true,// 是否显示行号
      mode: 'javascript', // 编辑器语言
      theme: 'dracula', // 编辑器主题
      matchBrackets: true, // 匹配括号
      styleActiveLine: true // 设置光标所在行高亮
    });
    this.f_functionCodeMirror.setSize('500px','380px');
    // 获取祖先节点数据集
    nowNodeInputParameterData = [];
    nowNodeInputParameterCount = {};
    recursionFlowCellVariable(nowSelectNode);
    // 初始化输入参数
    var inputParameterSelect = $('#f_input_parameter').select2({
      data: nowNodeInputParameterData,
      language: "zh-CN"
    });
    if (ParameterJSON) { // 修改
      var values = [];
      ParameterJSON['f_input_parameter'].forEach((item)=>{
        values.push(item.f_variable_id);
      });
      inputParameterSelect.val(values).trigger("change");
    }
    // 初始化效验工具
    that.validator = $('#nodeEndVariableForm').validate({
      rules: {
        f_name: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请填写名称"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        if (that.f_functionCodeMirror.getValue() == '') {
          toastr.error('请填写 javascript 函数');
        } else {
          fn();
        }
      }
    });
  }
}
 
/**
 * 弹出节点模拟运行日志查询框
 */
var NodeRunLogDialog = function(editorUi)
{
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.margin = '0';
  table.style.width = '100%';
  table.style.height = '100%';
  var row, td;
  // 流程图列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'nodeRunLogTable');
  DateTable.setAttribute('class', 'display compact');
  td.appendChild(DateTable);
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  // 放入展示面板
  this.container = table;
  // 初始化
  this.init = function(data)
    {
    // 封装表格
    $('#nodeRunLogTable').DataTable({
      data: data,
      columns: [
        { title: '节点', data: 'node_name', width: 90 },
        { title: '条件类型', data: 'type', width: 60 },
        { title: '说明', data: 'note', orderable: false},
        { title: '时间', data: 'c_date', width: 120 }
      ],
      paging: true, // 是否开启本地分页
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: true, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      order: [[3, "desc"]], // 默认按时间倒序
      info: false, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
  }
};
 
/**
 * 子流程图设置面板
 */
var SubprocessSetDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'subprocessSetForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 流程图-编辑区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '8px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '流程图:');
  row.appendChild(td);
  // 流程图-下拉列表
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var subprocess_select = document.createElement('select');
  subprocess_select.style.width = "425px";
  subprocess_select.setAttribute('id', 'subprocessSelect');
  subprocess_select.setAttribute('name', 'subprocessSelect');
  subprocess_select.append(new Option('无', -1, true, true));
  td.appendChild(subprocess_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 类型-修改状态
  if (op == 2) {
    var option = new Option(ParameterJSON['subprocess'].subprocessName, ParameterJSON['subprocess'].subprocessId, true, true);
    subprocess_select.append(option);
  }
  // 在流程图-预览区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.padding = 0;
  td.setAttribute('colspan', 2);
  var flow_iframe = document.createElement('iframe');
  flow_iframe.style.border = '1px solid rgba(0,0,0,.125)';
  flow_iframe.setAttribute('id', 'flowIframeView');
  flow_iframe.setAttribute('frameborder', 0);
  flow_iframe.setAttribute('scrolling', 'no');
  flow_iframe.setAttribute('width', '100%');
  flow_iframe.setAttribute('height', '400');
  flow_iframe.setAttribute('src', '../../floweditor/index.html?lang=zh&sidebar-entries=large&tv=one' + (ParameterJSON['subprocess'].subprocessId ? '&f='+ParameterJSON['subprocess'].subprocessId : ''));
  td.appendChild(flow_iframe);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.fileExtSelect;
  this.fileTypeSelect;
  this.init = function()
  {
    // 子流程图-封装下拉列表
    var subprocessSelect = $('#subprocessSelect').select2({
      ajax: {
        type:'POST',
        url: "/s/flow/queryFlowSelect",
        dataType: 'json',
        data: function (params) {
          return {
            search: params.term,
            type: 'nothing'
          };
        },
        delay: 800,
        cache: true
      },
      language: "zh-CN"
    });
    subprocessSelect.on("select2:select",function(e){
      if (e.params.data) {
        if (e.params.data.id == 0) {
          $('#flowIframeView')[0].contentWindow.cleanFlowGraph();
        } else {
          $('#flowIframeView')[0].contentWindow.openFlowGraph(e.params.data.id);
        }
      }
    })
    // 表单验证
    that.validator = $('#subprocessSetForm').validate({
      rules: {
        subprocessSelect: {
          required: true
        }
      },
      messages: {
        subprocessSelect: {
          required: "*请选择子流程图"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
  }
}
 
/**
* 设定开始日期编辑提示框
*/
var WorkStartTimeDialog = function(editorUi, fn)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'workStartTimeForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 编辑区域 -- 起始日期
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.paddingTop = '12px';
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.fontWeight = 600;
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '开始日期');
  row.appendChild(td);
  td = document.createElement('td');
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var WorkStartTimeInput = document.createElement('div');
  WorkStartTimeInput.style.width = '110px';
  WorkStartTimeInput.setAttribute('id', 'work_start_time');
  WorkStartTimeInput.setAttribute('class', 'form-control date-input');
  td.appendChild(WorkStartTimeInput);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 2;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('计算', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn';
  genericBtn.setAttribute('type', 'submit');
  td.appendChild(genericBtn);
  // 加入按钮区域
  row.appendChild(td);
  tbody.appendChild(row);
  table.appendChild(tbody);
  form.appendChild(table);
  // 放入展示面板
  this.container = form;
  // 初始化
  this.init = function()
  {
    that.validator = $('#workStartTimeForm').validate({
      rules: {
        work_start_time: {
          required: true
        }
      },
      messages: {
        work_start_time: {
          required: "*请填写开始日期"
        }
      },
      onfocusout: false, // 是否在获取焦点时验证
      onkeyup: false, // 是否在敲击键盘时验证
      focusInvalid: false, //提交表单后，（第一个）未通过验证的表单获得焦点
      focusCleanup: true, // 当未通过验证的元素获得焦点时，移除错误提示
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-control-td').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      },
      submitHandler:function(){
        fn();
      }
    });
    new XNDatepicker($("#work_start_time"),{
      type: 'date'
    },function(data){
      $('#work_start_time').attr('date_value', data.startTime.format('YYYY-MM-DD'));
    },);
  }
}