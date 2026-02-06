var FlowCourseTableDialog = function(editorUi)
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
  NameInput.setAttribute('class', 'form-control form-flow-course-text-name');
  NameInput.style.width = '200px';
  td.appendChild(NameInput);
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '80px';
  var I_Search = document.createElement('i');
  I_Search.setAttribute('class', 'fas fa-search');
  var Button_Search = mxUtils.button('', function()
  {
    $('#mainFlowCourseTable').DataTable().ajax.reload(null, false);
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
  DateTable.setAttribute('id', 'mainFlowCourseTable');
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
    $('#mainFlowCourseTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/trainingprogramflow/queryFlow', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            tpid: nowFlowCourseTpID,
            name: $('.form-flow-course-text-name').val()
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
            if (row['key'] == nowFlowCourseID) {
              return '<i class="fas fa-hand-point-right"></i>&nbsp;' + data;
            } else {
              return data;
            }
          }
        },
        //{ title: '说明', data: 'f_explain', orderable: false},
        { title: '创建时间', data: 'f_c_date', width: 120 },
        {
          title: '操作',
          data: 'key',
          width: 110,
          orderable: false,
          render: function (data, type, row) {
            var temp = '';
            temp += '<button type="button" class="geBtn gePrimaryBtn" onclick="FlowCourseTableDialogChooseAction(\''+data+'\')"><i class="fas fa-folder-open"></i>选择</button>';
            if (data == nowFlowCourseID) {
              temp +=  '';
            } else {
              temp +=  '<button type="button" class="geBtn geDangerBtn" onclick="FlowCourseTableDialogDelAction(\''+data+'\')"><i class="fas fa-trash"></i>删除</button>';
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
var FlowCourseTableDialogChooseAction = function(id, state)
{
  // 加载面板
  $('body').mLoading('show');
  setTimeout(function(){
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/trainingprogramflow/queryFlowInfo', // 调用地址
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
          nowFlowCourseID = id;
          nowFlowCourseName = data['info']['f_name'];
          nowFlowCourseExplain = data['info']['f_explain'];
          nowFlowCourseSaveOp = 0;
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
var FlowCourseTableDialogDelAction = function(id)
{
  var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
  {
    // 加载面板
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/s/trainingprogramflow/delFlow',
        data: JSON.stringify({
          id: id
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            $('#mainFlowCourseTable').DataTable().ajax.reload(null, false);
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
var FlowCourseEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'flowCourseForm');
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
    that.validator = $('#flowCourseForm').validate({
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