
/**
 * 弹出自适应模型查询框
 */
var AdaptiveDesignTableDialog = function(editorUi)
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
  td.style.width = '80px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '模型名称:');
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '200px';
  var NameInput = document.createElement('input');
  NameInput.style.marginBottom = '3px';
  NameInput.setAttribute('class', 'form-control form-adaptive-design-text-name');
  NameInput.style.width = '200px';
  td.appendChild(NameInput);
  row.appendChild(td);
  td = document.createElement('td');
  td.style.width = '60px';
  var I_Search = document.createElement('i');
  I_Search.setAttribute('class', 'fas fa-search');
  var Button_Search = mxUtils.button('', function()
  {
    $('#adaptiveDesignTable').DataTable().ajax.reload(null, false);
  });
  Button_Search.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Search.appendChild(I_Search);
  mxUtils.write(Button_Search, '查询');
  td.appendChild(Button_Search);
  row.appendChild(td);
  tbody.appendChild(row);
  // 列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'adaptiveDesignTable');
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
    $('#adaptiveDesignTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/relationm/queryrelationm', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            name: $('.form-adaptive-design-text-name').val()
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
        { title: '模型名称', data: 'Fname'},
        { title: '功能名称', width: 150, data: 'ffuncmname'},
        { title: 'Bom名称', width: 150, data: 'fbommname'},
        {
          title: '操作',
          data: 'key',
          width: 50,
          orderable: false,
          render: function (data, type, row) {
            return '<button type="button" class="geBtn gePrimaryBtn" onclick="AdaptiveDesignTableDialogChooseAction(\''+data+'\')"><i class="fas fa-folder-open"></i>选择</button>';
          }
        }
      ],
      paging: true, // 是否开启本地分页
      pageLength: 16, // 改变初始化页长度（每页多少条数据）
      pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: false, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      order: [[0, "desc"]], // 默认按时间倒序
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
  }
};
var AdaptiveDesignTableDialogChooseAction = function(id)
{
  var editor = mainEditorUi.editor;
  var graph = editor.graph;
  // 设置为自适应设计视图状态
  EditorUiAdaptiveDesign();
  // 鼠标拖拽图层-开启
  graph.panningHandler.ignoreCell = true;
  // 刷新视图
  mainEditorUi.refresh();
  // 关闭弹出框
  mainEditorUi.hideDialog();
  // 存储自适应模型ID
  nowFlowAdaptiveID = id;
  // 打开自适应模型
  openAdaptiveDesign(id);
  // 初始化变量
  AdaptiveDesignReportSaveDataAllJson = {}; // 全部保存计算结果选中列表
  AdaptiveDesignReportSaveDataAllList = []; // 全部保存计算结果选中列表
  AdaptiveDesignReportSaveDataJson = {}; // 保存计算结果选中列表
};
  
/**
 * 弹出自适应计算报告查询框
 */
var AdaptiveDesignReportTableDialog = function(editorUi)
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
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainParamReportTable');
  DateTable.setAttribute('class', 'display compact');
  DateTable.setAttribute('width', '100%');
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
    AdaptiveDesignReportTableReload();
  }
};
function AdaptiveDesignReportTableReload() {
  var data = [];
  var graph = mainEditorUi.editor.graph;
  var cells = graph.getModel()['cells'];
  adaptiveDesignList.forEach(item => {
    // 获取节点
    var cell = cells[adaptiveDesignJSON[item.id]];
    // 判断是否为参数
    if (item['Classify']) {
      // 公共数据
      var row = {};
      row['name'] = item['fname'];
      row['value'] = cell['adaptiveValue'] ? cell['adaptiveValue'] : '';
      row['unit'] = item['ValueUnit']; // 当前结果值
      row['memo'] = item['Memo'];
      // 判断参数类型
      switch (parseInt(item['Classify'])) {
        case 1: // 普通参数
          switch (parseInt(item['ValueType'])) {
            case 1: row['type'] = '关系'; break;
            case 2: row['type'] = '固定值'; break;
            case 3: row['type'] = '区间'; break;
            case 4: row['type'] = '输入值'; break;
            default: row['type'] = '未知'; break;
          }
          break;
  
        case 4: // 公式&约束
          row['type'] = '公式&约束';
          break;
  
        default:
          break;
      }
      // 保存结果值
      for (var id in AdaptiveDesignReportSaveDataJson) {
        var element = AdaptiveDesignReportSaveDataJson[id];
        var saveData = element.f_data_json[cell.id];
        row[element.key] = (saveData&&saveData['adaptiveValue']) ? saveData['adaptiveValue'] : '';
      }
      // 存入集合
      data.push(row);
    }
  });
  // 默认列模型
  var defaultColumns = [
    {
      width: 6,
      class: 'details-control',
      orderable: false,
      data: null,
      defaultContent: ''
    },
    {
      title: '类型',
      data: 'type',
      width: 80
    },
    {
      title: '名称',
      data: 'name'
    },
    {
      title: '值',
      data: 'value',
      width: 60
    },
    {
      title: '单位',
      data: 'unit',
      width: 40
    }
  ];
  // 自定义列模型
  var customColumns = [];
  for (var id in AdaptiveDesignReportSaveDataJson) {
    var element = AdaptiveDesignReportSaveDataJson[id];
    customColumns.push({
      title: element.f_name.length > 10 ? element.f_name.subData(10)+'...' : element.f_name,
      data: element.key,
      width: 80
    });
  }
  var columns = defaultColumns.concat(customColumns);
  // 封装表格
  var mainParamReportTable = $('#mainParamReportTable').DataTable({
    dom: 'Bfrtip',
    buttons: [
      {extend: 'excel', text: '导出Excel', className: 'geBtn geInfoBtn geBtnMini2'},
      {extend: 'print', text: '打印', className: 'geBtn geInfoBtn geBtnMini2'},
      {text: '自定义结果对比条件', className: 'geBtn geInfoBtn geBtnMini2', action: function(){
        // 弹出保存结果列表显示面板
        var dlg = new AdaptiveDesignReportSaveDataTableDialog(editorUi);
        editorUi.showDialog(dlg.container, 1000, 550, true, true, null, false);
        dlg.init();
      }}
    ],
    data: data,
    columns: columns,
    paging: true, // 是否开启本地分页
    pageLength: 18, // 改变初始化页长度（每页多少条数据）
    pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
    lengthChange: false, // 是否允许用户改变表格每页显示的记录数
    searching: true, // 是否允许Datatables开启本地搜索
    ordering: true, // 是否允许Datatables开启排序
    info: true, // 控制是否显示表格左下角的信息
    autoWidth: false, // 控制Datatables是否自适应宽度
    language: DataTablesLanguage
  });
  var mainParamReportTableDetailRows = [];
  $('#mainParamReportTable tbody').on('click', 'tr td.details-control', function() {
    var tr = $(this).closest('tr');
    var row = mainParamReportTable.row(tr);
    var idx = $.inArray(tr.attr('id'), mainParamReportTableDetailRows);
    if (row.child.isShown()) {
      tr.removeClass('details');
      row.child.hide();
      mainParamReportTableDetailRows.splice(idx, 1);
    } else {
      tr.addClass('details');
      row.child(mainParamReportTableRowFormat(row.data())).show();
      if (idx === -1) {
        mainParamReportTableDetailRows.push(tr.attr('id'));
      }
    }
  });
  mainParamReportTable.on('draw', function() {
    $.each(mainParamReportTableDetailRows, function(i, id) {
      $('#'+id+' td.details-control').trigger('click');
    });
  });
  function mainParamReportTableRowFormat(d) {
    return d.memo;
  }
}

/**
 * 弹出保存结果列表框
 */
var AdaptiveDesignReportSaveDataAllJson = {}; // 全部保存计算结果选中列表
var AdaptiveDesignReportSaveDataAllList = []; // 全部保存计算结果选中列表
var AdaptiveDesignReportSaveDataJson = {}; // 保存计算结果选中列表
var AdaptiveDesignReportSaveDataTableDialog = function(editorUi)
{
  // 表格布局
  var table = document.createElement('table');
  table.style.margin = '0';
  table.style.width = '100%';
  table.style.height = '100%';
  this.container = table;
  var tbody = document.createElement('tbody');
  table.appendChild(tbody);
  var row, td;
  // 保存信息列表
  row = document.createElement('tr');
  tbody.appendChild(row);
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  row.appendChild(td);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainAdaptiveDesignReportSaveDataTable');
  DateTable.setAttribute('class', 'display compact');
  td.appendChild(DateTable);
  // 生成按钮区域
  row = document.createElement('tr');
  tbody.appendChild(row);
  td = document.createElement('td');
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.style.height = '30px';
  td.setAttribute('align', 'right');
  row.appendChild(td);
  // 关闭按钮
  var cancelBtn = mxUtils.button(' 关 闭 ', function()
  {
      editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn geBtnMini3';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 初始化
  this.init = function()
  {
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/flow/queryFlowAdaptiveSaveDataAll', // 调用地址
      data: JSON.stringify({
        adaptive_design_id: nowFlowAdaptiveID
      }),
      async: false,
      success: function(data) {
        if (data.result == "success") {
          // 封装表格
          AdaptiveDesignReportSaveDataAllJson = data.data;
          AdaptiveDesignReportSaveDataAllList = data.list;
          $('#mainAdaptiveDesignReportSaveDataTable').DataTable({
            data: data.list,
            columns: [
              {
                data: 'key',
                width: 20,
                orderable: false,
                render: function (data, type, row, meta) {
                  if (AdaptiveDesignReportSaveDataJson[data]) {
                    return '<i class="fa fa-check-square-o" aria-hidden="true" onclick="AdaptiveDesignReportSaveDataDelAction(\''+data+'\')"></i>';
                  } else {
                    return '<i class="fa fa-square-o" aria-hidden="true" onclick="AdaptiveDesignReportSaveDataAddAction(\''+data+'\')"></i>';
                  }
                }
              },
              { title: '名称', data: 'f_name' },
              { 
                title: '说明',
                data: 'f_memo',
                orderable: false,
                render: function (data, type, row) {
                  return '<span title="' + data + '">' + (data.length > 30 ? data.subData(30)+'...' : data) + '</span>';
                }
              },
              { title: '创建时间', data: 'f_c_date', width: 120 },
              { title: '最后修改时间', data: 'f_u_date', width: 120 }
            ],
            paging: false, // 是否开启本地分页
            searching: true, // 是否允许Datatables开启本地搜索
            ordering: true, // 是否允许Datatables开启排序
            order: [[ 3, 'desc' ]],
            info: false, // 控制是否显示表格左下角的信息
            autoWidth: true, // 控制Datatables是否自适应宽度
            language: DataTablesLanguage
          });
        } else {
          toastr.error(data.error);
        }
      },
      error: function(e) {
        toastr.error(e.status);
        toastr.error(e.responseText);
      }
    });
  }
};
var AdaptiveDesignReportSaveDataAddAction = function(id)
{
  var json = AdaptiveDesignReportSaveDataAllJson[id];
  AdaptiveDesignReportSaveDataJson[id] = json;
  // 重新构建table
  $('#mainAdaptiveDesignReportSaveDataTable').dataTable().fnClearTable();
  $('#mainAdaptiveDesignReportSaveDataTable').dataTable().fnAddData(AdaptiveDesignReportSaveDataAllList, true);
  // 刷新报表
  $('#mainParamReportTable').dataTable().fnDestroy();
  $('#mainParamReportTable').empty();
  AdaptiveDesignReportTableReload();
};
var AdaptiveDesignReportSaveDataDelAction = function(id)
{
  delete AdaptiveDesignReportSaveDataJson[id];
  // 重新构建table
  $('#mainAdaptiveDesignReportSaveDataTable').dataTable().fnClearTable();
  $('#mainAdaptiveDesignReportSaveDataTable').dataTable().fnAddData(AdaptiveDesignReportSaveDataAllList, true);
  // 刷新报表
  $('#mainParamReportTable').dataTable().fnDestroy();
  $('#mainParamReportTable').empty();
  AdaptiveDesignReportTableReload();
};

/**
 * 弹出自适应节点关系查询框 
 */
var AdaptiveDesignRelationshipTableDialog = function(editorUi)
{
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.margin = '0';
  table.style.width = '100%';
  table.style.height = '100%';
  var row, td;
  // (主)列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.style.height = '200px';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainParamRelationshipTable');
  DateTable.setAttribute('class', 'display compact');
  DateTable.setAttribute('width', '100%');
  td.appendChild(DateTable);
  row.appendChild(td);
  tbody.appendChild(row);
  // (从)列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'subParamRelationshipTable');
  DateTable.setAttribute('class', 'display compact');
  DateTable.setAttribute('width', '100%');
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
    // ********
    // ***主***
    // ********
    var mainData = [];
    for (var id in AdaptiveRelationshipSelectNodes) {
      var node = AdaptiveRelationshipSelectNodes[id]['cell'];
      var adaptiveData = node['adaptiveData'];
      // 判断是否为参数
      if (adaptiveData) {
        // 公共数据
        var row = {};
        row['cell'] = node;
        row['name'] = adaptiveData['fname'];
        row['value'] = node['adaptiveValue'];
        row['unit'] = adaptiveData['ValueUnit'] ? adaptiveData['ValueUnit'] : '';
        row['memo'] = adaptiveData['Memo'];
        // 判断参数类型
        if (adaptiveData['Classify']) { // 计算类
          switch (parseInt(adaptiveData['Classify'])) {
            case 1: // 普通参数
              switch (parseInt(adaptiveData['ValueType'])) {
                case 1: row['type'] = '关系'; break;
                case 2: row['type'] = '固定值'; break;
                case 3: row['type'] = '区间'; break;
                case 4: row['type'] = '输入值'; break;
                default: row['type'] = '未知'; break;
              }
              break;
      
            case 4: // 公式&约束
              row['type'] = '公式&约束';
              break;
      
            default:
              break;
          }
        } else { // 显示类
          if (node['adaptiveType'] == 0) {
            row['type'] = '功能';
          }
          if (node['adaptiveType'] == 1) {
            row['type'] = '结构';
          }
        }
        // 存入集合
        mainData.push(row);
      }
    }
    // 封装表格
    var mainParamRelationshipTable = $('#mainParamRelationshipTable').DataTable({
      data: mainData,
      columns: [
        {
          width: 6,
          class: 'details-control',
          orderable: false,
          data: null,
          defaultContent: ''
        },
        {
          title: '类型',
          data: 'type',
          width: 80
        },
        {
          title: '选中节点名称(中心点)',
          data: 'name'
        },
        {
          title: '值',
          data: 'value',
          width: 60
        },
        {
          title: '单位',
          data: 'unit',
          width: 40
        },
        {
          title: '影响比(数量)',
          data: 'cell',
          width: 100,
          render: function (data, type, row) {
            var relationCells = AdaptiveRelationshipSelectNodes[data.id]['relationCells'];
            return parseInt((JSONLength(relationCells) / adaptiveDesignList.length) * 10000) / 100 + '%';
          }
        },
        {
          title: '影响比(成本)',
          data: 'cell',
          width: 100,
          render: function (data, type, row) {
            var graph = mainEditorUi.editor.graph;
            var cells = graph.getModel()['cells'];
            // 获取有关联的参数节点成本之和(包括选中节点自身)
            var relationCells = AdaptiveRelationshipSelectNodes[data.id]['relationCells'];
            var relationCellsCount = 0;
            for (var id in relationCells) {
              var item = relationCells[id];
              relationCellsCount += parseFloat((!item['adaptiveKosten'] || item['adaptiveKosten'] == '' || isNaN(item['adaptiveKosten'])) ? 0 : item['adaptiveKosten']);
            }
            relationCellsCount += parseFloat((!data['adaptiveKosten'] || data['adaptiveKosten'] == '' || isNaN(data['adaptiveKosten'])) ? 0 : data['adaptiveKosten']);
            // 获取全部参数节点成本之和
            var adaptiveDesignListCount = 0;
            adaptiveDesignList.forEach(item => {
              var cell = cells[adaptiveDesignJSON[item['id']]];
              adaptiveDesignListCount += parseFloat((!cell['adaptiveKosten'] || cell['adaptiveKosten'] == '' || isNaN(cell['adaptiveKosten'])) ? 0 : cell['adaptiveKosten']);
            });
            return parseInt((relationCellsCount / adaptiveDesignListCount) * 10000) / 100 + '%';
          }
        },
        {
          title: '影响比(重要度)',
          data: 'cell',
          width: 100,
          render: function (data, type, row) {
            var graph = mainEditorUi.editor.graph;
            var cells = graph.getModel()['cells'];
            // 获取有关联的参数节点重要度之和(包括选中节点自身)
            var relationCells = AdaptiveRelationshipSelectNodes[data.id]['relationCells'];
            var relationCellsCount = 0;
            for (var id in relationCells) {
              var item = relationCells[id];
              relationCellsCount += parseInt((!item['adaptiveBedeutung'] || item['adaptiveBedeutung'] == '' || isNaN(item['adaptiveBedeutung'])) ? 0 : item['adaptiveBedeutung']);
            }
            relationCellsCount += parseInt((!data['adaptiveBedeutung'] || data['adaptiveBedeutung'] == '' || isNaN(data['adaptiveBedeutung'])) ? 0 : data['adaptiveBedeutung']);
            // 获取全部参数节点重要度之和
            var adaptiveDesignListCount = 0;
            adaptiveDesignList.forEach(item => {
              var cell = cells[adaptiveDesignJSON[item['id']]];
              adaptiveDesignListCount += parseInt((!cell['adaptiveBedeutung'] || cell['adaptiveBedeutung'] == '' || isNaN(cell['adaptiveBedeutung'])) ? 0 : cell['adaptiveBedeutung']);
            });
            return parseInt((relationCellsCount / adaptiveDesignListCount) * 10000) / 100 + '%';
          }
        }
      ],
      paging: true, // 是否开启本地分页
      pageLength: 5, // 改变初始化页长度（每页多少条数据）
      pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: true, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
    var mainParamRelationshipTableDetailRows = [];
    $('#mainParamRelationshipTable tbody').on('click', 'tr td.details-control', function() {
      var tr = $(this).closest('tr');
      var row = mainParamRelationshipTable.row(tr);
      var idx = $.inArray(tr.attr('id'), mainParamRelationshipTableDetailRows);
      if (row.child.isShown()) {
        tr.removeClass('details');
        row.child.hide();
        mainParamRelationshipTableDetailRows.splice(idx, 1);
      } else {
        tr.addClass('details');
        row.child(mainParamRelationshipTableRowFormat(row.data())).show();
        if (idx === -1) {
          mainParamRelationshipTableDetailRows.push(tr.attr('id'));
        }
      }
    });
    mainParamRelationshipTable.on('draw', function() {
      $.each(mainParamRelationshipTableDetailRows, function(i, id) {
        $('#'+id+' td.details-control').trigger('click');
      });
    });
    function mainParamRelationshipTableRowFormat(d) {
      return d.memo;
    }
    // ********
    // ***从***
    // ********
    var subData = [];
    for (var id in AdaptiveRelationshipSelNodesSubNodes) {
      var element = AdaptiveRelationshipSelNodesSubNodes[id];
      var node = element['cell'];
      var adaptiveData = node['adaptiveData'];
      if (adaptiveData) {
        // 公共数据
        var row = {};
        row['name'] = adaptiveData['fname'];
        row['value'] = node['adaptiveValue'];
        row['unit'] = adaptiveData['ValueUnit'] ? adaptiveData['ValueUnit'] : '';
        row['memo'] = adaptiveData['Memo'];
        row['source'] = element['source'];
        row['target'] = element['target'];
        // 判断是参数还是其它节点
        if (adaptiveData['Classify']) {
          switch (parseInt(adaptiveData['Classify'])) {
            case 1: // 普通参数
              switch (parseInt(adaptiveData['ValueType'])) {
                case 1: row['type'] = '关系'; break;
                case 2: row['type'] = '固定值'; break;
                case 3: row['type'] = '区间'; break;
                case 4: row['type'] = '输入值'; break;
                default: row['type'] = '未知'; break;
              }
              break;
      
            case 4: // 公式&约束
              row['type'] = '公式&约束';
              break;
      
            default:
              break;
          }
        } else {
          if (adaptiveData['type'] == 0) {
            row['type'] = '功能';
          }
          if (adaptiveData['type'] == 1) {
            row['type'] = '结构';
          }
        }
        // 存入集合
        subData.push(row);
      }
    }
    // 封装表格
    var subParamRelationshipTable = $('#subParamRelationshipTable').DataTable({
      data: subData,
      columns: [
        {
          width: 6,
          class: 'details-control',
          orderable: false,
          data: null,
          defaultContent: ''
        },
        {
          title: '类型',
          data: 'type',
          width: 80
        },
        {
          title: '关系节点名称',
          data: 'name'
        },
        {
          title: '值',
          data: 'value',
          width: 60
        },
        {
          title: '单位',
          data: 'unit',
          width: 40
        },
        {
          title: '源于',
          data: 'source',
          width: 200,
          render: function (data, type, row) {
            var temp = '';
            for (var name in data) {
              temp += data[name] + ' ';
            }
            return temp;
          }
        },
        {
          title: '目标',
          data: 'target',
          width: 200,
          render: function (data, type, row) {
            var temp = '';
            for (var name in data) {
              temp += data[name] + ' ';
            }
            return temp;
          }
        }
      ],
      paging: true, // 是否开启本地分页
      pageLength: 10, // 改变初始化页长度（每页多少条数据）
      pagingType: 'full_numbers', // 首页，尾页，上一页和下一页四个按钮,加上数字按钮
      lengthChange: false, // 是否允许用户改变表格每页显示的记录数
      searching: true, // 是否允许Datatables开启本地搜索
      ordering: true, // 是否允许Datatables开启排序
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
    var subParamRelationshipTableDetailRows = [];
    $('#subParamRelationshipTable tbody').on('click', 'tr td.details-control', function() {
      var tr = $(this).closest('tr');
      var row = subParamRelationshipTable.row(tr);
      var idx = $.inArray(tr.attr('id'), subParamRelationshipTableDetailRows);
      if (row.child.isShown()) {
        tr.removeClass('details');
        row.child.hide();
        subParamRelationshipTableDetailRows.splice(idx, 1);
      } else {
        tr.addClass('details');
        row.child(subParamRelationshipTableRowFormat(row.data())).show();
        if (idx === -1) {
          subParamRelationshipTableDetailRows.push(tr.attr('id'));
        }
      }
    });
    subParamRelationshipTable.on('draw', function() {
      $.each(subParamRelationshipTableDetailRows, function(i, id) {
        $('#'+id+' td.details-control').trigger('click');
      });
    });
    function subParamRelationshipTableRowFormat(d) {
      return d.memo;
    }
  }
};

/**
 * 弹出自适应计算模型说明显示框
 */
var AdaptiveDesignMemoDialog = function(editorUi, ParameterJSON)
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
  tbody.appendChild(row);
  row.appendChild(td);
  td.innerHTML = ParameterJSON['memo'];
  // 写入表格
  table.appendChild(tbody);
  // 放入展示面板
  this.container = table;
};

/**
 * 弹出分布计算[主]信息列表框
 */
var AdaptiveDesignStepCountTableDialog = function(editorUi)
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
  tbody.appendChild(row);
  td = document.createElement('td'); // 新建
  td.style.textAlign = 'left';
  row.appendChild(td);
  var I_New = document.createElement('i');
  I_New.setAttribute('class', 'fas fa-plus');
  var newButton = mxUtils.button('', function()
  {
    // 打开面板
    var dlg = new AdaptiveDesignStepCountEditDialog(editorUi, mxUtils.bind(this, function()
    {
      // 加载面板
      $("body").mLoading("show");
      // 延时执行
      setTimeout(function(){
        // 处理JSON字符串
        var jsonData = form2Json('adaptiveDesignStepCountForm');
        // 添加数据
        $.ajax({
          type: 'POST',
          contentType: 'application/json',
          url: '/s/flow/addFlowAdaptiveStepCount', // 调用地址
          data: JSON.stringify(jsonData),
          async: false,
          success: function(data) {
            $("body").mLoading("hide");
            if (data.result == "success") {
              $('#mainAdaptiveDesignStepCountTable').DataTable().ajax.reload(null, false);
              toastr.success('添加成功！');
              editorUi.hideDialog();
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
    editorUi.showDialog(dlg.container, 380, 200, true, true);
    dlg.init();
  });
  newButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  newButton.appendChild(I_New);
  mxUtils.write(newButton, '新增');
  td.appendChild(newButton);
  td = document.createElement('td'); // 名称标题
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td'); // 名称控件
  td.style.width = '200px';
  var NameInput = document.createElement('input');
  NameInput.style.marginBottom = '3px';
  NameInput.setAttribute('class', 'form-control form-adaptive-design-step-count-text-name');
  NameInput.style.width = '200px';
  td.appendChild(NameInput);
  row.appendChild(td);
  td = document.createElement('td'); // 说明标题
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '说明:');
  row.appendChild(td);
  td = document.createElement('td'); // 说明控件
  td.style.width = '200px';
  var MemoInput = document.createElement('input');
  MemoInput.style.marginBottom = '3px';
  MemoInput.setAttribute('class', 'form-control form-adaptive-design-step-count-text-memo');
  MemoInput.style.width = '200px';
  td.appendChild(MemoInput);
  row.appendChild(td);
  td = document.createElement('td'); // 查询按钮
  td.style.width = '60px';
  var I_Search = document.createElement('i');
  I_Search.setAttribute('class', 'fas fa-search');
  var Button_Search = mxUtils.button('', function()
  {
    $('#mainAdaptiveDesignStepCountTable').DataTable().ajax.reload(null, false);
  });
  Button_Search.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Search.appendChild(I_Search);
  mxUtils.write(Button_Search, '查询');
  td.appendChild(Button_Search);
  row.appendChild(td);
  // 保存信息列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainAdaptiveDesignStepCountTable');
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
    $('#mainAdaptiveDesignStepCountTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/flow/queryFlowAdaptiveStepCount', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            name: $('.form-adaptive-design-step-count-text-name').val(),
            memo: $('.form-adaptive-design-step-count-text-memo').val()
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
        { title: '名称', data: 'f_name' },
        { 
          title: '说明',
          data: 'f_memo',
          orderable: false,
          render: function (data, type, row) {
            return '<span title="' + data + '">' + (data.length > 30 ? data.subData(30)+'...' : data) + '</span>';
          }
        },
        // { title: '创建时间', data: 'f_c_date', width: 120 },
        // { title: '最后修改时间', data: 'f_u_date', width: 120 },
        {
          title: '操作',
          data: 'key',
          width: 250,
          orderable: false,
          render: function (data, type, row) {
            var temp = '';
            temp += '<button type="button" class="geBtn geInfoBtn" onclick="AdaptiveDesignStepCountTableDialogGOAction(\''+data+'\')"><i class="fa fa-server"></i>分布计算</button>';
            temp += '<button type="button" class="geBtn geSuccessBtn" onclick="AdaptiveDesignStepCountTableDialogConfigAction(\''+data+'\')"><i class="fa fa-cogs"></i>设置</button>';
            temp += '<button type="button" class="geBtn gePrimaryBtn" onclick="AdaptiveDesignStepCountTableDialogEditAction(\''+data+'\')"><i class="fas fa-edit"></i>编辑</button>';
            temp += '<button type="button" class="geBtn geDangerBtn" onclick="AdaptiveDesignStepCountTableDialogDelAction(\''+data+'\')"><i class="fas fa-trash"></i>删除</button>';
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
var AdaptiveDesignStepCountTableDialogConfigAction = function(id)
{
  var dlg = new AdaptiveDesignStepCountDetailTableDialog(mainEditorUi, id);
  mainEditorUi.showDialog(dlg.container, 1000, 500, true, true);
  dlg.init();
};
var AdaptiveDesignStepCountTableDialogEditAction = function(id)
{
  // 加载面板
  $('body').mLoading('show');
  setTimeout(function(){
    $.ajax({
      type: 'POST',
      contentType: 'application/json;charset=UTF-8',
      url: '/s/flow/queryFlowAdaptiveStepCountInfo',
      data: JSON.stringify({
        id: id
      }),
      success: function(data) {
        $('body').mLoading('hide');
        if(data.result == 'success'){
          // 打开面板
          var dlg = new AdaptiveDesignStepCountEditDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 处理JSON字符串
            var jsonData = form2Json('adaptiveDesignStepCountForm');
            jsonData['f_key_id'] = id;
            // 添加数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/flow/updateFlowAdaptiveStepCount', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data) {
                $("body").mLoading("hide");
                if (data.result == "success") {
                  $('#mainAdaptiveDesignStepCountTable').DataTable().ajax.reload(null, false);
                  toastr.success('修改成功！');
                  mainEditorUi.hideDialog();
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
          }), 2, { f_name: data.info.f_name, f_memo: data.info.f_memo });
          mainEditorUi.showDialog(dlg.container, 380, 200, true, true);
          dlg.init();
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
}
var AdaptiveDesignStepCountTableDialogDelAction = function(id)
{
  var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
  {
    // 加载面板
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/s/flow/delFlowAdaptiveStepCount',
        data: JSON.stringify({
          id: id
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            $('#mainAdaptiveDesignStepCountTable').DataTable().ajax.reload(null, false);
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
 * 弹出分布计算[主]信息编辑提示框
 */
var AdaptiveDesignStepCountEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'adaptiveDesignStepCountForm');
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
  var memoTextarea = document.createElement('textarea');
  memoTextarea.style.marginBottom = '1px';
  memoTextarea.setAttribute('id', 'f_memo');
  memoTextarea.setAttribute('name', 'f_memo');
  if (op == 1) { // 新建
    mxUtils.write(memoTextarea, '');
  } else { // 修改
    mxUtils.write(memoTextarea, ParameterJSON['f_memo']);
  }
  memoTextarea.setAttribute('rows', '5');
  memoTextarea.setAttribute('cols', '34');
  memoTextarea.setAttribute('class', 'form-control');
  td.appendChild(memoTextarea);
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
    that.validator = $('#adaptiveDesignStepCountForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_memo: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请输入名称"
        },
        f_memo: {
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
 * 自适应模型分布计算[设置]表提示框
 */
var AdaptiveDesignStepCountDetailTableDialog = function(editorUi, f_main_id)
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
  td.style.width = '100px';
  td.style.textAlign = 'right';
  var I_Add = document.createElement('i');
  I_Add.setAttribute('class', 'fas fa-plus');
  var Button_Add = mxUtils.button('', function()
  {
    // 打开面板
    var dlg = new AdaptiveDesignStepCountDetailEditDialog(editorUi, mxUtils.bind(this, function()
    {
      // 加载面板
      $("body").mLoading("show");
      // 延时执行
      setTimeout(function(){
        // 处理JSON字符串
        var jsonData = form2Json('adaptiveDesignStepCountDetailEditForm');
        jsonData['f_main_id'] = f_main_id;
        jsonData['f_order'] = $('.f_order_input').val();
        // 添加数据
        $.ajax({
          type: 'POST',
          contentType: 'application/json',
          url: '/s/flow/addFlowAdaptiveStepCountDetail', // 调用地址
          data: JSON.stringify(jsonData),
          async: false,
          success: function(data) {
            $("body").mLoading("hide");
            if (data.result == "success") {
              $('#mainAdaptiveDesignStepCountDetailTable').DataTable().ajax.reload(null, false);
              toastr.success('添加成功！');
              editorUi.hideDialog();
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
    editorUi.showDialog(dlg.container, 1200, 550, true, true);
    dlg.init();
  });
  Button_Add.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Add.appendChild(I_Add);
  mxUtils.write(Button_Add, '新增模型');
  td.appendChild(Button_Add);
  row.appendChild(td);
  tbody.appendChild(row);
  // 流程图列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainAdaptiveDesignStepCountDetailTable');
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
    $('#mainAdaptiveDesignStepCountDetailTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/flow/queryFlowAdaptiveStepCountDetail', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            f_main_id: f_main_id
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
        { title: '顺序', data: 'f_order', orderable: false, width: 40 },
        { title: '名称', data: 'adaptive_design_name', orderable: false },
        {
          title: '操作',
          data: 'key',
          width: 120,
          orderable: false,
          render: function (data, type, row) {
            var temp = '';
            temp += '<button type="button" class="geBtn gePrimaryBtn" onclick="AdaptiveDesignStepCountDetailTableDialogEditAction(\''+data+'\')"><i class="fas fa-edit"></i>编辑</button>';
            temp += '<button type="button" class="geBtn geDangerBtn" onclick="AdaptiveDesignStepCountDetailTableDialogDelAction(\''+data+'\')"><i class="fas fa-trash"></i>删除</button>';
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
      info: true, // 控制是否显示表格左下角的信息
      autoWidth: false, // 控制Datatables是否自适应宽度
      language: DataTablesLanguage
    });
  }
}
var AdaptiveDesignStepCountDetailTableDialogEditAction = function(id)
{
  // 加载面板
  $('body').mLoading('show');
  setTimeout(function(){
    $.ajax({
      type: 'POST',
      contentType: 'application/json;charset=UTF-8',
      url: '/s/flow/queryFlowAdaptiveStepCountDetailInfo',
      data: JSON.stringify({
        id: id
      }),
      success: function(data) {
        $('body').mLoading('hide');
        if(data.result == 'success'){
          // 打开面板
          var dlg = new AdaptiveDesignStepCountDetailEditDialog(mainEditorUi, mxUtils.bind(this, function()
          {
            // 处理JSON字符串
            var jsonData = form2Json('adaptiveDesignStepCountDetailEditForm');
            jsonData['f_key_id'] = id;
            jsonData['f_order'] = $('.f_order_input').val();
            // 添加数据
            $.ajax({
              type: 'POST',
              contentType: 'application/json',
              url: '/s/flow/updateFlowAdaptiveStepCountDetail', // 调用地址
              data: JSON.stringify(jsonData),
              async: false,
              success: function(data2) {
                $("body").mLoading("hide");
                if (data2.result == "success") {
                  $('#mainAdaptiveDesignStepCountDetailTable').DataTable().ajax.reload(null, false);
                  toastr.success('修改成功！');
                  mainEditorUi.hideDialog();
                } else {
                  toastr.error(data2.error);
                }
              },
              error : function(e){
                $("body").mLoading("hide");
                toastr.error(e.status);
                toastr.error(e.responseText);
              }
            });
          }), 2, { f_adaptive_design_id: data.info.f_adaptive_design_id, adaptive_design_name: data.info.adaptive_design_name, f_order: data.info.f_order });
          mainEditorUi.showDialog(dlg.container, 1200, 550, true, true);
          dlg.init();
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
}
var AdaptiveDesignStepCountDetailTableDialogDelAction = function(id)
{
  var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
  {
    // 加载面板
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/s/flow/delFlowAdaptiveStepCountDetail',
        data: JSON.stringify({
          id: id
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            $('#mainAdaptiveDesignStepCountDetailTable').DataTable().ajax.reload(null, false);
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
 * 自适应模型选择面板
 */
var AdaptiveDesignStepCountDetailEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'adaptiveDesignStepCountDetailEditForm');
  form.setAttribute('role', 'form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', 'javascript:void(0);');
  // 表格布局
  var table = document.createElement('table');
  var tbody = document.createElement('tbody');
  table.style.width = '100%';
  var row, td;
  // 自适应模型-标题
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.height = '35px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '8px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '自适应模型:');
  row.appendChild(td);
  // 自适应模型-下拉列表
  td = document.createElement('td');
  td.style.width = '450px';
  td.setAttribute('valign', 'top');
  td.setAttribute('class', 'form-control-td');
  var adaptiveDesign_select = document.createElement('select');
  adaptiveDesign_select.style.width = "425px";
  adaptiveDesign_select.setAttribute('id', 'f_adaptive_design_id');
  adaptiveDesign_select.setAttribute('name', 'f_adaptive_design_id');
  adaptiveDesign_select.append(new Option('无', -1, true, true));
  td.appendChild(adaptiveDesign_select);
  row.appendChild(td);
  tbody.appendChild(row);
  // 自适应模型-修改状态
  if (op == 2) {
    var option = new Option(ParameterJSON['adaptive_design_name'], ParameterJSON['f_adaptive_design_id'], true, true);
    adaptiveDesign_select.append(option);
  }
  // 序号-标题
  td = document.createElement('td');
  td.style.whiteSpace = 'nowrap';
  td.style.fontSize = '10pt';
  td.style.width = '60px';
  td.style.fontWeight = 600;
  td.style.paddingTop = '8px';
  td.setAttribute('valign', 'top');
  mxUtils.write(td, '序号:');
  row.appendChild(td);
  // 序号-编辑区域
  td = document.createElement('td');
  row.appendChild(td);
  var orderControl = document.createElement('div');
  orderControl.style.marginTop = '-15px';
  orderControl.setAttribute('id', 'f_order');
  orderControl.setAttribute('class', 'f_order_div');
  td.appendChild(orderControl);
  
  // 自适应模型-预览区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.padding = 0;
  td.setAttribute('colspan', 4);
  var flow_iframe = document.createElement('iframe');
  flow_iframe.style.border = '1px solid rgba(0,0,0,.125)';
  flow_iframe.setAttribute('id', 'adaptiveDesignIframeView');
  flow_iframe.setAttribute('frameborder', 0);
  flow_iframe.setAttribute('scrolling', 'no');
  flow_iframe.setAttribute('width', '100%');
  flow_iframe.setAttribute('height', '450');
  flow_iframe.setAttribute('src', '../../floweditor/index.html?lang=zh&sidebar-entries=large&tv=outside-adaptive-design-view' + ((ParameterJSON && ParameterJSON['f_adaptive_design_id']) ? '&ad='+ParameterJSON['f_adaptive_design_id'] : ''));
  td.appendChild(flow_iframe);
  row.appendChild(td);
  tbody.appendChild(row);
  // 生成按钮区域
  row = document.createElement('tr');
  td = document.createElement('td');
  td.colSpan = 4;
  td.style.paddingTop = '10px';
  td.style.whiteSpace = 'nowrap';
  td.setAttribute('align', 'right');
  // 取消按钮
  var cancelBtn = mxUtils.button('取消', function()
  {
    editorUi.hideDialog();
  });
  cancelBtn.className = 'geBtn geBtnMini3';
  cancelBtn.setAttribute('type', 'button');
  td.appendChild(cancelBtn);
  // 确定按钮
  var genericBtn = mxUtils.button('保存', function(){});
  genericBtn.className = 'geBtn gePrimaryBtn geBtnMini3';
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
    // 自适应模型-封装下拉列表
    var adaptiveDesignSelect = $('#f_adaptive_design_id').select2({
      ajax: {
        type:'POST',
        url: "/s/flow/queryAdaptiveDesignSelect",
        dataType: 'json',
        data: function (params) {
          return {
            search: params.term
          };
        },
        delay: 800,
        cache: true
      },
      language: "zh-CN"
    });
    adaptiveDesignSelect.on("select2:select",function(e){
      if (e.params.data) {
        if (e.params.data.id == 0) {
          $('#adaptiveDesignIframeView')[0].contentWindow.cleanFlowGraph();
        } else {
          $('#adaptiveDesignIframeView')[0].contentWindow.openAdaptiveDesign(e.params.data.id);
        }
      }
    })
    // 排序
    $('.f_order_div').numInput({
      width: 100, //宽度
      positive: true, //允许输入正数
      negative: false, //允许输入负数
      floatCount: 0, //小数点后保留位数
      wrapperClass: 'f_order_wrap num-input-wrap', //最外层容器
      inputClass: 'f_order_input num-input', //input输入框
      addClass: 'f_order_add add', //增加按钮
      subtractClass: 'f_order_subtract subtract', //减少按钮
      interval: 1 //增加&减少按钮每次变化的值
    });
    // 序号-修改状态
    if (op == 2) {
      $('.f_order_input').val(ParameterJSON['f_order']);
    }
    // 表单验证
    that.validator = $('#adaptiveDesignStepCountDetailEditForm').validate({
      rules: {
        f_adaptive_design_id: {
          required: true
        }
      },
      messages: {
        f_adaptive_design_id: {
          required: "*请选择自适应模型"
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
 * 弹出保存结果列表框
 */
var FlowAdaptiveSaveDataTableDialog = function(editorUi)
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
  tbody.appendChild(row);
  td = document.createElement('td'); // 自适应宽度补位用
  row.appendChild(td);
  td = document.createElement('td'); // 名称标题
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '名称:');
  row.appendChild(td);
  td = document.createElement('td'); // 名称控件
  td.style.width = '200px';
  var NameInput = document.createElement('input');
  NameInput.style.marginBottom = '3px';
  NameInput.setAttribute('class', 'form-control form-flow-adaptive-save-data-text-name');
  NameInput.style.width = '200px';
  td.appendChild(NameInput);
  row.appendChild(td);
  td = document.createElement('td'); // 说明标题
  td.style.width = '40px';
  td.style.textAlign = 'right';
  td.style.fontWeight = 600;
  mxUtils.write(td, '说明:');
  row.appendChild(td);
  td = document.createElement('td'); // 说明控件
  td.style.width = '200px';
  var MemoInput = document.createElement('input');
  MemoInput.style.marginBottom = '3px';
  MemoInput.setAttribute('class', 'form-control form-flow-adaptive-save-data-text-memo');
  MemoInput.style.width = '200px';
  td.appendChild(MemoInput);
  row.appendChild(td);
  td = document.createElement('td'); // 查询按钮
  td.style.width = '60px';
  var I_Search = document.createElement('i');
  I_Search.setAttribute('class', 'fas fa-search');
  var Button_Search = mxUtils.button('', function()
  {
    $('#mainFlowAdaptiveSaveDataTable').DataTable().ajax.reload(null, false);
  });
  Button_Search.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Search.appendChild(I_Search);
  mxUtils.write(Button_Search, '查询');
  td.appendChild(Button_Search);
  row.appendChild(td);
  // 保存信息列表
  row = document.createElement('tr');
  td = document.createElement('td');
  td.style.margin = '0';
  td.style.verticalAlign = 'top';
  td.setAttribute('colspan', 8);
  var DateTable = document.createElement('table');
  DateTable.setAttribute('id', 'mainFlowAdaptiveSaveDataTable');
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
    $('#mainFlowAdaptiveSaveDataTable').DataTable({
      processing: true,
      serverSide: true,
      ajax: {
        type: 'POST',
        contentType: 'application/json',
        url: '/s/flow/queryFlowAdaptiveSaveData', // 调用地址
        data: function ( d ) {
          return JSON.stringify( $.extend( {}, d, {
            page: d.start/d.length+1, // 当前页
            results: d.length, // 每页显示条数
            adaptive_design_id: nowFlowAdaptiveID,
            name: $('.form-flow-adaptive-save-data-text-name').val(),
            memo: $('.form-flow-adaptive-save-data-text-memo').val()
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
            if (row['key'] == nowFlowAdaptiveSaveDataID) {
              return '<i class="fas fa-hand-point-right"></i>&nbsp;' + data;
            } else {
              return data;
            }
          }
        },
        { 
          title: '说明',
          data: 'f_memo',
          orderable: false,
          render: function (data, type, row) {
            return '<span title="' + data + '">' + (data.length > 30 ? data.subData(30)+'...' : data) + '</span>';
          }
        },
        { title: '创建时间', data: 'f_c_date', width: 120 },
        { title: '最后修改时间', data: 'f_u_date', width: 120 },
        {
          title: '操作',
          data: 'key',
          width: 110,
          orderable: false,
          render: function (data, type, row) {
            var temp = '';
            temp += '<button type="button" class="geBtn gePrimaryBtn" onclick="FlowAdaptiveSaveDataTableDialogChooseAction(\''+data+'\','+row['f_state']+')"><i class="fas fa-folder-open"></i>选择</button>';
            if (data == nowFlowAdaptiveSaveDataID) {
              temp +=  '';
            } else {
              temp +=  '<button type="button" class="geBtn geDangerBtn" onclick="FlowAdaptiveSaveDataTableDialogDelAction(\''+data+'\')"><i class="fas fa-trash"></i>删除</button>';
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
var FlowAdaptiveSaveDataTableDialogChooseAction = function(id, state)
{
  // 加载面板
  $('body').mLoading('show');
  setTimeout(function(){
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: '/s/flow/queryFlowAdaptiveSaveDataInfo', // 调用地址
      data: JSON.stringify({
        id: id
      }),
      async: false,
      success: function(data) {
        $('body').mLoading('hide');
        if (data.result == 'success') {
          var f_data_json = data.info.f_data_json;
          var graph = mainEditorUi.editor.graph;
          var cells = graph.getModel()['cells'];
          for (var id in cells) {
            var cell = cells[id];
            // 清空
            AdaptiveSaveNames.forEach(name => {
              delete cell[name];
            });
            // 赋值
            var save = f_data_json[id];
            if (save) {
              AdaptiveSaveNames.forEach(name => {
                if (save[name]) {
                  cell[name] = save[name];
                }
              });
            }
          }
          // 公共变量赋值
          nowFlowAdaptiveSaveDataID = data.info.key;
          nowFlowAdaptiveSaveDataName = data.info.f_name;
          nowFlowAdaptiveSaveDataMemo = data.info.f_memo;
          nowFlowAdaptiveSaveDataSaveOp = 0;
          // 回执
          mainEditorUi.adaptiveDesign.Refresh(nowSelectNode);
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
var FlowAdaptiveSaveDataTableDialogDelAction = function(id)
{
  var dlg = new TipsDialog(mainEditorUi, mxUtils.bind(this, function()
  {
    // 加载面板
    $('body').mLoading('show');
    setTimeout(function(){
      $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/s/flow/delFlowAdaptiveSaveData',
        data: JSON.stringify({
          id: id
        }),
        success: function(data) {
          $('body').mLoading('hide');
          if(data.result == 'success'){
            $('#mainFlowAdaptiveSaveDataTable').DataTable().ajax.reload(null, false);
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
 * 弹出自适应模型数据保存编辑提示框
 */
var FlowAdaptiveSaveDataEditDialog = function(editorUi, fn, op, ParameterJSON)
{
  var that = this;
  // 验证
  that.validator = null;
  // 表单
  var form = document.createElement('form');
  form.setAttribute('id', 'flowAdaptiveSaveDataForm');
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
  var memoTextarea = document.createElement('textarea');
  memoTextarea.style.marginBottom = '1px';
    memoTextarea.setAttribute('id', 'f_memo');
    memoTextarea.setAttribute('name', 'f_memo');
  if (op == 1) { // 新建
    mxUtils.write(memoTextarea, '');
  } else { // 修改
    mxUtils.write(memoTextarea, ParameterJSON['f_memo']);
  }
  memoTextarea.setAttribute('rows', '5');
  memoTextarea.setAttribute('cols', '34');
  memoTextarea.setAttribute('class', 'form-control');
  td.appendChild(memoTextarea);
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
    that.validator = $('#flowAdaptiveSaveDataForm').validate({
      rules: {
        f_name: {
          required: true
        },
        f_memo: {
          required: true
        }
      },
      messages: {
        f_name: {
          required: "*请输入名称"
        },
        f_memo: {
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