/**
 * 自适应设计面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container
 */
function AdaptiveDesign(editorUi, container) {
  this.editorUi = editorUi;
  this.container = container;
  this.adaptiveDesignMainContainer;
  this.init();
}
// 初始化
var geAdaptiveDesignContainerPerfectScrollbar;
AdaptiveDesign.prototype.init = function() {
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
  mxUtils.write(tittleDiv, '设计面板');
  this.container.appendChild(tittleDiv);
  // 自适应设计面板
  this.adaptiveDesignMainContainer = document.createElement('div');
  this.adaptiveDesignMainContainer.style.width = '240px';
  this.adaptiveDesignMainContainer.style.overflow = 'hidden';
  this.adaptiveDesignMainContainer.style.paddingTop = '34px';
  this.container.appendChild(this.adaptiveDesignMainContainer);
  // 滚动条
  setTimeout(function(){ // 缓冲后创建自定义滚动条(不使用setTimeout的话,会出现首次创建无法加载横向条的问题)
    geAdaptiveDesignContainerPerfectScrollbar = new PerfectScrollbar('.geAdaptiveDesignContainer');
  }, 100);
};
// 刷新面板
AdaptiveDesign.prototype.Refresh = function(cell) {
  // 清除内容
  this.adaptiveDesignMainContainer.innerHTML = '';
  // 主面板
  var div = document.createElement('div');
  div.style.width = '240px';
  div.style.overflow = 'hidden';
  div.style.paddingLeft = '2px';
  this.adaptiveDesignMainContainer.appendChild(div);
  // 判断是否为点击节点状态
  if (cell) { // 点击节点
    // 根据节点类型显示选项卡
    if (cell['adaptiveData']) {
      switch (cell['adaptiveData']['type']) {
        case 0: // 功能
          var MainDIV = document.createElement('div');
          div.appendChild(MainDIV);
          var NODIV = document.createElement('div');
          NODIV.style.display = 'inline-block';
          NODIV.style.fontSize = '15px';
          NODIV.style.fontWeight = 600;
          NODIV.style.color = 'rgb(112, 112, 112)';
          NODIV.style.border = '1px solid #ccc';
          NODIV.style.padding = '5px 10px';
          NODIV.style.backgroundColor = '#f5f5f5';
          NODIV.style.borderRadius = '2px';
          NODIV.style.margin = '10px 0';
          NODIV.style.width = '210px';
          mxUtils.write(NODIV, cell['adaptiveData']['fname']);
          MainDIV.appendChild(NODIV);
          break;
        case 1: // 结构
          var MainDIV = document.createElement('div');
          div.appendChild(MainDIV);
          var NODIV = document.createElement('div');
          NODIV.style.display = 'inline-block';
          NODIV.style.fontSize = '15px';
          NODIV.style.fontWeight = 600;
          NODIV.style.color = 'rgb(112, 112, 112)';
          NODIV.style.border = '1px solid #ccc';
          NODIV.style.padding = '2px 4px';
          NODIV.style.backgroundColor = '#f5f5f5';
          NODIV.style.borderRadius = '2px';
          NODIV.style.margin = '10px 0';
          mxUtils.write(NODIV, cell['adaptiveData']['fname']);
          MainDIV.appendChild(NODIV);
          break;
        case 2: // 参数
          // 业务相关
          ParamAdaptiveDesignPanel(this.editorUi, div, cell);
          // 显示相关
          ParamViewAdaptiveDesignPanel(this.editorUi, div, cell);
          break;

        default:
          var NODIV = document.createElement('div');
          mxUtils.write(NODIV, '无操作内容');
          div.appendChild(NODIV);
          break;
      }
    }
  } else { // 点击绘图区域(显示全部参数)
    AllParamAdaptiveDesignPanel(this.editorUi, div);
  }
  // 功能按钮
  FnButtonAdaptiveDesignPanel(this.editorUi, div);
  // 更新滚动条组件
  geAdaptiveDesignContainerPerfectScrollbar.update();
}
// 清空
AdaptiveDesign.prototype.Clear = function() {
  // 清除内容
  this.adaptiveDesignMainContainer.innerHTML = '';
}
/**
 * 创建参数组件
 * @param {操作面板} container 
 * @param {节点对象} cell 
 * @param {显示方式} type 1只输入 2只展示 3输入+展示
 */
function createParamControl(container, cell, type) {
  if (cell['adaptiveData']) {
    var adaptiveData = cell['adaptiveData'];
    // 判断参数类型
    if (adaptiveData['Classify']) {
      switch (adaptiveData['Classify']) {
        case '1': { // 普通参数
          switch (adaptiveData['ValueType']) {
            case '1': // [关系]
              if (type == 2 || type == 3) {
                // 布局表格
                var table = document.createElement('table');
                table.style.marginTop = '2px';
                table.style.marginBottom = '2px';
                table.style.paddingLeft = '1px';
                table.style.paddingRight = '1px';
                table.style.backgroundColor = 'whitesmoke';
                table.style.border = '1px solid lightgrey';
                table.style.width = '235px';
                container.appendChild(table);
                var tbody = document.createElement('tbody');
                table.appendChild(tbody);
                var row, td;
                // 标题
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.style.paddingLeft = '3px';
                td.style.backgroundColor = 'steelblue';
                td.style.border = '1px solid dimgray';
                td.style.height = '18px';
                row.appendChild(td);
                var LeaderSpan = document.createElement('span');
                LeaderSpan.style.display = 'block';
                LeaderSpan.style.color = 'rgb(112, 112, 112)';
                LeaderSpan.style.fontWeight = '600';
                LeaderSpan.style.fontSize = '15px';
                LeaderSpan.style.color = 'white';
                mxUtils.write(LeaderSpan, '['+adaptiveData['fname']+']'+adaptiveData['Subtitle']);
                td.appendChild(LeaderSpan);
                // 值
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.style.paddingBottom = '5px';
                td.style.fontWeight = '600';
                row.appendChild(td);
                mxUtils.write(td, '关系');
                // 成本&重要度
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                row.appendChild(td);
                var otherTable = document.createElement('table');
                td.appendChild(otherTable);
                var otherTbody = document.createElement('tbody');
                otherTable.appendChild(otherTbody);
                var otherRow = document.createElement('tr');
                otherTbody.appendChild(otherRow);
                var otherTd;
                // 成本
                otherTd = document.createElement('td');
                otherTd.setAttribute('width', '50%');
                otherRow.appendChild(otherTd);
                createKostenmControl(otherTd, cell, 85);
                // 重要度
                otherTd = document.createElement('td');
                otherTd.setAttribute('width', '50%');
                otherRow.appendChild(otherTd);
                createBedeutungControl(otherTd, cell, 85);
              }
              break;
  
            case '2': // [固定值]
              if (type == 2 || type == 3) {
                // 布局表格
                var table = document.createElement('table');
                table.style.marginTop = '2px';
                table.style.marginBottom = '2px';
                table.style.paddingLeft = '1px';
                table.style.paddingRight = '1px';
                table.style.backgroundColor = 'whitesmoke';
                table.style.border = '1px solid lightgrey';
                table.style.width = '235px';
                container.appendChild(table);
                var tbody = document.createElement('tbody');
                table.appendChild(tbody);
                var row, td;
                // 标题
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.style.paddingLeft = '3px';
                td.style.backgroundColor = 'steelblue';
                td.style.border = '1px solid dimgray';
                td.style.height = '18px';
                row.appendChild(td);
                var LeaderSpan = document.createElement('span');
                LeaderSpan.style.display = 'block';
                LeaderSpan.style.color = 'rgb(112, 112, 112)';
                LeaderSpan.style.fontWeight = '600';
                LeaderSpan.style.fontSize = '15px';
                LeaderSpan.style.color = 'white';
                mxUtils.write(LeaderSpan, '['+adaptiveData['fname']+']'+adaptiveData['Subtitle']);
                td.appendChild(LeaderSpan);
                // 值
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.style.paddingBottom = '5px';
                td.style.fontWeight = '600';
                td.style.fontSize = '15px';
                row.appendChild(td);
                mxUtils.write(td, '[ ' + cell['adaptiveValue'] + adaptiveData['ValueUnit'] + ' ]');
                // 成本&重要度
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                row.appendChild(td);
                var otherTable = document.createElement('table');
                td.appendChild(otherTable);
                var otherTbody = document.createElement('tbody');
                otherTable.appendChild(otherTbody);
                var otherRow = document.createElement('tr');
                otherTbody.appendChild(otherRow);
                var otherTd;
                // 成本
                otherTd = document.createElement('td');
                otherTd.setAttribute('width', '50%');
                otherRow.appendChild(otherTd);
                createKostenmControl(otherTd, cell, 85);
                // 重要度
                otherTd = document.createElement('td');
                otherTd.setAttribute('width', '50%');
                otherRow.appendChild(otherTd);
                createBedeutungControl(otherTd, cell, 85);
              }
              break;
  
            case '3': // [区间]
            case '4': // [输入值]
              if (type == 1 || type == 3) {
                // 布局表格
                var table = document.createElement('table');
                table.style.marginTop = '2px';
                table.style.marginBottom = '2px';
                table.style.paddingLeft = '1px';
                table.style.paddingRight = '1px';
                table.style.backgroundColor = 'whitesmoke';
                table.style.border = '1px solid lightgrey';
                table.style.width = '235px';
                container.appendChild(table);
                var tbody = document.createElement('tbody');
                table.appendChild(tbody);
                var row, td;
                // ****** 输入框 ******
                // 标题
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.style.paddingLeft = '3px';
                td.style.backgroundColor = 'steelblue';
                td.style.border = '1px solid dimgray';
                td.style.height = '18px';
                td.setAttribute('colspan', '2');
                row.appendChild(td);
                var LeaderSpan = document.createElement('span');
                LeaderSpan.style.display = 'block';
                LeaderSpan.style.color = 'rgb(112, 112, 112)';
                LeaderSpan.style.fontWeight = '600';
                LeaderSpan.style.fontSize = '15px';
                LeaderSpan.style.color = 'white';
                mxUtils.write(LeaderSpan, '['+adaptiveData['fname']+']'+adaptiveData['Subtitle']);
                td.appendChild(LeaderSpan);
                // 控件
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.style.paddingTop = '5px';
                td.style.paddingLeft = '5px';
                row.appendChild(td);
                var valueControl = document.createElement('div');
                valueControl.setAttribute('id', 'adaptive_' + adaptiveData['id']);
                valueControl.setAttribute('class', 'adaptive_' + adaptiveData['id'] + '_div');
                td.appendChild(valueControl);
                td = document.createElement('td');
                td.style.width = '60px';
                td.style.whiteSpace = 'pre-line';
                td.style.wordBreak = 'break-all';
                mxUtils.write(td, adaptiveData['ValueUnit']);
                row.appendChild(td);
                // 初始化小数点后保留位数
                var floatCount = 4;
                var interval = 0.0001;
                var MaxDecimalLen;
                var MinDecimalLen;
                // 获取最大值小数位数
                if (adaptiveData['MaxValue'] && adaptiveData['MaxValue'] != '') {
                  var MaxValue = adaptiveData['MaxValue'].split(".");
                  var decimalStr = MaxValue[1];
                  MaxDecimalLen = decimalStr.length;
                }
                // 获取最小值小数位数
                if (adaptiveData['MinValue'] && adaptiveData['MinValue'] != '') {
                  var MinValue = adaptiveData['MinValue'].split(".");
                  var decimalStr = MinValue[1];
                  MinDecimalLen = decimalStr.length;
                }
                // 判断小数点位数
                // if (MaxDecimalLen || MinDecimalLen) {
                //   var max = MaxDecimalLen ? MaxDecimalLen : 0;
                //   var min = MinDecimalLen ? MinDecimalLen : 0;
                //   if (max != 0 || min != 0) {
                //     if (max >= min) {
                //       floatCount = max;
                //     } else {
                //       floatCount = min;
                //     }
                //   }
                // }
                // 判断增加&减少按钮每次变化的值
                // switch (floatCount) {
                //   case 1: interval = 0.1; break;
                //   case 2: interval = 0.01; break;
                //   case 3: interval = 0.001; break;
                //   case 4: interval = 0.0001; break;
                //   case 5: interval = 0.00001; break;
                //   case 6: interval = 0.000001; break;
                //   case 7: interval = 0.0000001; break;
                //   case 8: interval = 0.00000001; break;
                //   case 9: interval = 0.000000001; break;
                //   default: break;
                // }
                // 封装输入框
                $('.adaptive_' + adaptiveData['id'] + '_div').numInput({
                  width: 150, //宽度
                  positive: true, //允许输入正数
                  negative: true, //允许输入负数
                  floatCount: floatCount, //小数点后保留位数
                  wrapperClass: 'adaptive_' + adaptiveData['id'] + '_wrap num-input-wrap', //最外层容器
                  inputClass: 'adaptive_' + adaptiveData['id'] + '_input num-input', //input输入框
                  addClass: 'adaptive_' + adaptiveData['id'] + '_add add', //增加按钮
                  subtractClass: 'adaptive_' + adaptiveData['id'] + '_subtract subtract', //减少按钮
                  interval: interval //增加&减少按钮每次变化的值
                });
                // 输入框赋值
                if (cell['adaptiveValue']) {
                  $('.adaptive_' + adaptiveData['id'] + '_input').val(cell['adaptiveValue']);
                }
                // 改变事件
                var valueControlChange = function(e) {
                  var paramId = e.target.className.match(/_(\S*)_/)[1]; // 获取对应参数id
                  var graph = mainEditorUi.editor.graph;
                  var cells = graph.getModel()['cells'];
                  // 通过参数id获取节点id,再根据节点id获取对应节点
                  var cell = cells[adaptiveDesignJSON[paramId]];
                  var adaptiveData = cell['adaptiveData']; // 获取参数信息
                  var value = $('.adaptive_' + adaptiveData['id'] + '_input').val(); // 获取文本框值
                  if (cell) {
                    if (adaptiveData['MaxValue'] && adaptiveData['MaxValue'] != '') {
                      if (value > adaptiveData['MaxValue']) {
                        value = adaptiveData['MaxValue'];
                      }
                    }
                    if (adaptiveData['MinValue'] && adaptiveData['MinValue'] != '') {
                      if (value < adaptiveData['MinValue']) {
                        value = adaptiveData['MinValue'];
                      }
                    }
                    $('.adaptive_' + adaptiveData['id'] + '_input').val(value);
                    // 将控件值写入节点属性中
                    cell['adaptiveValue'] = value;
                    // 重新渲染节点
                    var valueStr = createAdaptiveCellView(adaptiveData, value);
                    cell.setValue(valueStr);
                    graph.refresh(cell);
                    // 设置为可保存状态
                    nowFlowAdaptiveSaveDataSaveOp = 1;
                  } else {
                    // 找不到对应节点的情况下清空文本框
                    $('.adaptive_' + adaptiveData['id'] + '_input').val('');
                  }
                };
                $('.adaptive_' + adaptiveData['id'] + '_input').bind('input', valueControlChange);
                $('.adaptive_' + adaptiveData['id'] + '_add').bind('click', valueControlChange);
                $('.adaptive_' + adaptiveData['id'] + '_subtract').bind('click', valueControlChange);
                // 编辑器中阻止文本选择的解决方法
                $('.adaptive_' + adaptiveData['id'] + '_input').bind('mousedown', function(evt) {
                  if (evt.stopPropagation) {
                    evt.stopPropagation();
                  }
                });
                // 创建其它输入框容器
                row = document.createElement('tr');
                tbody.appendChild(row);
                td = document.createElement('td');
                td.setAttribute('colspan', '2');
                row.appendChild(td);
                // 布局表格
                var otherTable = document.createElement('table');
                td.appendChild(otherTable);
                var otherTbody = document.createElement('tbody');
                otherTable.appendChild(otherTbody);
                var otherRow = document.createElement('tr');
                otherTbody.appendChild(otherRow);
                var otherTd;
                // 成本
                otherTd = document.createElement('td');
                otherTd.setAttribute('width', '50%');
                otherRow.appendChild(otherTd);
                createKostenmControl(otherTd, cell, 85);
                // 重要度
                otherTd = document.createElement('td');
                otherTd.setAttribute('width', '50%');
                otherRow.appendChild(otherTd);
                createBedeutungControl(otherTd, cell, 85);
              }
              break;
  
            default:
              // // 输入框(!!!!!!!!!!!!!!!!!!!!普通输入框无法点击输入框输入数据的解决方法备份!!!!!!!!!!!!!!!!!!!!)
              // var valueControl = document.createElement('input');
              // valueControl.setAttribute('id', 'adaptive_' + adaptiveData['id']);
              // if (cell['adaptiveValue']) { // 赋值
              //   valueControl.setAttribute('value', cell['adaptiveValue']);
              // }
              // container.appendChild(valueControl);
              // // 编辑器中阻止文本选择的解决方法
              // mxEvent.addListener(valueControl, 'mousedown', function(evt) {
              //   if (evt.stopPropagation) {
              //     evt.stopPropagation();
              //   }
              // });
              break;
          }
          break;
        }
        case '2': { // 标准查询参数
          var NODIV = document.createElement('div');
          mxUtils.write(NODIV, '标准查询参数');
          container.appendChild(NODIV);
          break;
        }
        case '3': { // 自定义查询参数
          var NODIV = document.createElement('div');
          mxUtils.write(NODIV, '自定义查询参数');
          container.appendChild(NODIV);
          break;
        }
        case '4': { // 公式参数
          if (type == 2 || type == 3) {
            // 布局表格
            var table = document.createElement('table');
            table.style.marginTop = '2px';
            table.style.marginBottom = '2px';
            table.style.paddingLeft = '1px';
            table.style.paddingRight = '1px';
            table.style.backgroundColor = 'whitesmoke';
            table.style.border = '1px solid lightgrey';
            table.style.width = '235px';
            container.appendChild(table);
            var tbody = document.createElement('tbody');
            table.appendChild(tbody);
            var row, td;
            // 标题
            row = document.createElement('tr');
            tbody.appendChild(row);
            td = document.createElement('td');
            td.style.paddingLeft = '3px';
            td.style.backgroundColor = 'steelblue';
            td.style.border = '1px solid dimgray';
            td.style.height = '18px';
            row.appendChild(td);
            var LeaderSpan = document.createElement('span');
            LeaderSpan.style.display = 'block';
            LeaderSpan.style.color = 'rgb(112, 112, 112)';
            LeaderSpan.style.fontWeight = '600';
            LeaderSpan.style.fontSize = '15px';
            LeaderSpan.style.color = 'white';
            mxUtils.write(LeaderSpan, '['+adaptiveData['fname']+']'+adaptiveData['Subtitle']);
            td.appendChild(LeaderSpan);
            // 成本&重要度
            row = document.createElement('tr');
            tbody.appendChild(row);
            td = document.createElement('td');
            row.appendChild(td);
            var otherTable = document.createElement('table');
            td.appendChild(otherTable);
            var otherTbody = document.createElement('tbody');
            otherTable.appendChild(otherTbody);
            var otherRow = document.createElement('tr');
            otherTbody.appendChild(otherRow);
            var otherTd;
            // 成本
            otherTd = document.createElement('td');
            otherTd.setAttribute('width', '50%');
            otherRow.appendChild(otherTd);
            createKostenmControl(otherTd, cell, 85);
            // 重要度
            otherTd = document.createElement('td');
            otherTd.setAttribute('width', '50%');
            otherRow.appendChild(otherTd);
            createBedeutungControl(otherTd, cell, 85);
            // 公式变量
            row = document.createElement('tr');
            tbody.appendChild(row);
            td = document.createElement('td');
            td.style.fontSize = '15px';
            td.style.fontWeight = '600';
            row.appendChild(td);
            var funStr = '';
            adaptiveData['ParamRelation'] && adaptiveData['ParamRelation'].forEach(relation => {
              funStr += ', ' + relation.FVaribleName;
            });
            if (funStr.length > 0) {
              funStr = funStr.substring(2);
            }
            mxUtils.write(td, 'function (' + funStr + ')');
            // 公式内容
            row = document.createElement('tr');
            tbody.appendChild(row);
            td = document.createElement('td');
            td.style.fontWeight = '600';
            row.appendChild(td);
            var pre = document.createElement('pre');
            pre.style.position = 'relative';
            pre.style.width = '218px';
            pre.style.height = '200px';
            pre.style.paddingLeft = '5px';
            pre.style.marginTop = '0';
            pre.style.marginBottom = '0';
            pre.innerHTML = adaptiveData['Calculation'];
            pre.setAttribute('id', 'adaptiveDesignCalculationDiv');
            pre.setAttribute('class', 'prettyprint');
            td.appendChild(pre);
            setTimeout(function(){ // 缓冲后创建自定义滚动条(不使用setTimeout的话,会出现首次创建无法加载横向条的问题)
              new PerfectScrollbar('#adaptiveDesignCalculationDiv');
            }, 100);
            // 值
            row = document.createElement('tr');
            tbody.appendChild(row);
            td = document.createElement('td');
            td.style.paddingBottom = '5px';
            td.style.fontWeight = '600';
            row.appendChild(td);
            mxUtils.write(td, '计算结果：' + cell['adaptiveValue'] + adaptiveData['ValueUnit']);
          }
          break;
        }
        case "5": { // 网址参数
          mxUtils.write(container, '网址参数');
          break;
        }
        default:
          break;
      }
    }
  }
}
/**
 * 成本(万元)
 * @param {操作面板} container
 * @param {节点对象} cell
 * @param {控件宽度} width
 */
function createKostenmControl(container, cell, width) {
  var adaptiveData = cell['adaptiveData']; // 获取参数信息
  // 布局表格
  var table = document.createElement('table');
  table.style.borderCollapse = 'collapse';
  table.style.border = 'none';
  container.appendChild(table);
  var tbody = document.createElement('tbody');
  table.appendChild(tbody);
  var row, td;
  // 标题
  row = document.createElement('tr');
  tbody.appendChild(row);
  td = document.createElement('td');
  td.setAttribute('colspan', '2');
  row.appendChild(td);
  var KostenSpan = document.createElement('span');
  KostenSpan.style.display = 'block';
  KostenSpan.style.color = 'rgb(112, 112, 112)';
  KostenSpan.style.fontWeight = '600';
  KostenSpan.style.fontSize = '15px';
  mxUtils.write(KostenSpan, '成本(万元)');
  td.appendChild(KostenSpan);
  // 控件
  row = document.createElement('tr');
  tbody.appendChild(row);
  td = document.createElement('td');
  td.style.paddingBottom = '5px';
  row.appendChild(td);
  var valueControl = document.createElement('div');
  valueControl.setAttribute('id', 'kosten_' + adaptiveData['id']);
  valueControl.setAttribute('class', 'kosten_' + adaptiveData['id'] + '_div');
  td.appendChild(valueControl);
  td = document.createElement('td');
  td.style.width = '60px';
  row.appendChild(td);
  // 封装输入框
  $('.kosten_' + adaptiveData['id'] + '_div').numInput({
    width: width, //宽度
    positive: true, //允许输入正数
    negative: false, //允许输入负数
    floatCount: 3, //小数点后保留位数
    wrapperClass: 'kosten_' + adaptiveData['id'] + '_wrap num-input-wrap', //最外层容器
    inputClass: 'kosten_' + adaptiveData['id'] + '_input num-input', //input输入框
    addClass: 'kosten_' + adaptiveData['id'] + '_add add', //增加按钮
    subtractClass: 'kosten_' + adaptiveData['id'] + '_subtract subtract', //减少按钮
    interval: 0.001 //增加&减少按钮每次变化的值
  });
  // 输入框赋值
  if (cell['adaptiveKosten']) {
    $('.kosten_' + adaptiveData['id'] + '_input').val(cell['adaptiveKosten']);
  }
  // 改变事件
  var kostenControlChange = function(e) {
    var paramId = e.target.className.match(/_(\S*)_/)[1]; // 获取对应参数id
    var graph = mainEditorUi.editor.graph;
    var cells = graph.getModel()['cells'];
    // 通过参数id获取节点id,再根据节点id获取对应节点
    var cell = cells[adaptiveDesignJSON[paramId]];
    var adaptiveData = cell['adaptiveData']; // 获取参数信息
    var value = $('.kosten_' + adaptiveData['id'] + '_input').val(); // 获取文本框值
    if (cell) {
      $('.kosten_' + adaptiveData['id'] + '_input').val(value);
      // 将控件值写入节点属性中
      cell['adaptiveKosten'] = value;
      // 设置为可保存状态
      nowFlowAdaptiveSaveDataSaveOp = 1;
    } else {
      // 找不到对应节点的情况下清空文本框
      $('.kosten_' + adaptiveData['id'] + '_input').val('');
    }
  };
  $('.kosten_' + adaptiveData['id'] + '_input').bind('input', kostenControlChange);
  $('.kosten_' + adaptiveData['id'] + '_add').bind('click', kostenControlChange);
  $('.kosten_' + adaptiveData['id'] + '_subtract').bind('click', kostenControlChange);
  // 编辑器中阻止文本选择的解决方法
  $('.kosten_' + adaptiveData['id'] + '_input').bind('mousedown', function(evt) {
    if (evt.stopPropagation) {
      evt.stopPropagation();
    }
  });
}
/**
 * 重要度
 * @param {操作面板} container
 * @param {节点对象} cell
 * @param {控件宽度} width
 */
function createBedeutungControl(container, cell, width) {
  var adaptiveData = cell['adaptiveData']; // 获取参数信息
  // 布局表格
  var table = document.createElement('table');
  table.style.borderCollapse = 'collapse';
  table.style.border = 'none';
  container.appendChild(table);
  var tbody = document.createElement('tbody');
  table.appendChild(tbody);
  var row, td;
  // 标题
  row = document.createElement('tr');
  tbody.appendChild(row);
  td = document.createElement('td');
  td.setAttribute('colspan', '2');
  row.appendChild(td);
  var BedeutungSpan = document.createElement('span');
  BedeutungSpan.style.display = 'block';
  BedeutungSpan.style.color = 'rgb(112, 112, 112)';
  BedeutungSpan.style.fontWeight = '600';
  BedeutungSpan.style.fontSize = '15px';
  mxUtils.write(BedeutungSpan, '重要度');
  td.appendChild(BedeutungSpan);
  // 控件
  row = document.createElement('tr');
  tbody.appendChild(row);
  td = document.createElement('td');
  td.style.paddingBottom = '5px';
  row.appendChild(td);
  var valueControl = document.createElement('div');
  valueControl.setAttribute('id', 'bedeutung_' + adaptiveData['id']);
  valueControl.setAttribute('class', 'bedeutung_' + adaptiveData['id'] + '_div');
  td.appendChild(valueControl);
  td = document.createElement('td');
  td.style.width = '60px';
  row.appendChild(td);
  // 封装输入框
  $('.bedeutung_' + adaptiveData['id'] + '_div').numInput({
    width: width, //宽度
    positive: true, //允许输入正数
    negative: false, //允许输入负数
    floatCount: 0, //小数点后保留位数
    wrapperClass: 'bedeutung_' + adaptiveData['id'] + '_wrap num-input-wrap', //最外层容器
    inputClass: 'bedeutung_' + adaptiveData['id'] + '_input num-input', //input输入框
    addClass: 'bedeutung_' + adaptiveData['id'] + '_add add', //增加按钮
    subtractClass: 'bedeutung_' + adaptiveData['id'] + '_subtract subtract', //减少按钮
    interval: 1 //增加&减少按钮每次变化的值
  });
  // 输入框赋值
  if (cell['adaptiveBedeutung']) {
    $('.bedeutung_' + adaptiveData['id'] + '_input').val(cell['adaptiveBedeutung']);
  }
  // 改变事件
  var bedeutungControlChange = function(e) {
    var paramId = e.target.className.match(/_(\S*)_/)[1]; // 获取对应参数id
    var graph = mainEditorUi.editor.graph;
    var cells = graph.getModel()['cells'];
    // 通过参数id获取节点id,再根据节点id获取对应节点
    var cell = cells[adaptiveDesignJSON[paramId]];
    var adaptiveData = cell['adaptiveData']; // 获取参数信息
    var value = $('.bedeutung_' + adaptiveData['id'] + '_input').val(); // 获取文本框值
    if (cell) {
      $('.bedeutung_' + adaptiveData['id'] + '_input').val(value);
      // 将控件值写入节点属性中
      cell['adaptiveBedeutung'] = value;
      // 设置为可保存状态
      nowFlowAdaptiveSaveDataSaveOp = 1;
    } else {
      // 找不到对应节点的情况下清空文本框
      $('.bedeutung_' + adaptiveData['id'] + '_input').val('');
    }
  };
  $('.bedeutung_' + adaptiveData['id'] + '_input').bind('input', bedeutungControlChange);
  $('.bedeutung_' + adaptiveData['id'] + '_add').bind('click', bedeutungControlChange);
  $('.bedeutung_' + adaptiveData['id'] + '_subtract').bind('click', bedeutungControlChange);
  // 编辑器中阻止文本选择的解决方法
  $('.bedeutung_' + adaptiveData['id'] + '_input').bind('mousedown', function(evt) {
    if (evt.stopPropagation) {
      evt.stopPropagation();
    }
  });
}
/**
 * 参数设置面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function ParamAdaptiveDesignPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  createParamControl(container, cell, 3);
}
/**
 * 全部参数设置面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function AllParamAdaptiveDesignPanel(editorUi, container) {
  this.editorUi = editorUi;
  this.container = container;
  var graph = editorUi.editor.graph;
  // 获取参数节点并生成控件
  var cells = graph.getModel()['cells'];
  for (var name in cells) {
    var element = cells[name];
    createParamControl(container, element, 1);
  }
}
/**
 * 参数信息展示
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
function ParamViewAdaptiveDesignPanel(editorUi, container, cell) {
  this.editorUi = editorUi;
  this.container = container;
  if (cell['adaptiveData']) {
    var adaptiveData = cell['adaptiveData'];
    // 布局表格
    var table = document.createElement('table');
    container.appendChild(table);
    var tbody = document.createElement('tbody');
    table.appendChild(tbody);
    var row, td;
    // 图片
    if (adaptiveData['Fimg'] != '' || adaptiveData['Fimg'] != '') {
      row = document.createElement('tr');
      tbody.appendChild(row);
      td = document.createElement('td');
      td.style.paddingBottom = '5px';
      td.style.fontWeight = '600';
      row.appendChild(td);
      var img = document.createElement('img');
      img.style.cursor = 'pointer';
      img.setAttribute('width', '230');
      img.setAttribute('src', adaptiveData['Fimg']);
      img.onclick = function() {
        window.open('', '预览').document.write('<img src="' + adaptiveData['Fimg'] + '" />');
      };
      td.appendChild(img);
    }
    // 裕量
    if (adaptiveData['MinMargin'] != '' || adaptiveData['MaxMargin'] != '') {
      row = document.createElement('tr');
      tbody.appendChild(row);
      td = document.createElement('td');
      td.style.paddingBottom = '5px';
      td.style.fontWeight = '600';
      row.appendChild(td);
      mxUtils.write(td, '裕量：' + adaptiveData['MinMargin'] + '~' + adaptiveData['MaxMargin']);
    }
    // 说明
    if (adaptiveData['Memo'] != '') {
      row = document.createElement('tr');
      tbody.appendChild(row);
      td = document.createElement('td');
      td.style.fontWeight = '600';
      row.appendChild(td);
      mxUtils.write(td, '说明：');
      var i = document.createElement('i');
      i.setAttribute('class', 'fas fa-search');
      var searchButton = mxUtils.button('', function()
      {
        // 打开面板
        var dlg = new AdaptiveDesignMemoDialog(editorUi, {memo:adaptiveData['Memo']});
        editorUi.showDialog(dlg.container, 720, 520, true, true);
      });
      searchButton.style.marginLeft = '65px';
      searchButton.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
      searchButton.appendChild(i);
      mxUtils.write(searchButton, '点击查看详情');
      td.appendChild(searchButton);
      row = document.createElement('tr');
      tbody.appendChild(row);
      td = document.createElement('td');
      td.style.paddingBottom = '10px';
      td.style.fontWeight = '600';
      row.appendChild(td);
      var content = document.createElement('div');
      content.style.position = 'relative';
      content.style.width = '218px';
      content.style.height = '200px';
      content.style.backgroundColor = '#f7f7f9';
      content.style.border = '1px solid #e1e1e8';
      content.style.padding = '5px';
      content.style.borderRadius = '4px';
      content.innerHTML = adaptiveData['Memo'];
      content.setAttribute('id', 'adaptiveDesignMemoDiv');
      td.appendChild(content);
      setTimeout(function(){ // 缓冲后创建自定义滚动条(不使用setTimeout的话,会出现首次创建无法加载横向条的问题)
        new PerfectScrollbar('#adaptiveDesignMemoDiv');
      }, 100);
    }
  }
}
/**
 * 功能按钮面板
 * @param {主面板} editorUi 
 * @param {业务属性面板} container 
 * @param {选中节点} cell 
 */
 function FnButtonAdaptiveDesignPanel(editorUi, container) {
  this.editorUi = editorUi;
  this.container = container;
  // 生成【计算按钮】
  var I_Count = document.createElement('i');
  I_Count.style.paddingRight = '5px';
  I_Count.setAttribute('class', 'fas fa-calculator');
  var Button_Count = mxUtils.button('', function()
  {
    $('body').mLoading('show');
    setTimeout(function(){
      var graph = mainEditorUi.editor.graph;
      // 获取所有节点
      var cells = graph.getModel()['cells'];
      // 遍历所有函数进行计算
      adaptiveDesignList.forEach(item => {
        if (item['Classify'] && item['Classify'] == 4) {
          // 获取节点
          var cell = cells[adaptiveDesignJSON[item.id]];
          // 获取值
          var value = getAdaptiveValue(cell)['returnData'];
          // 设置值
          cell['adaptiveValue'] = value;
          // 刷新节点内容
          var valueStr = createAdaptiveCellView(cell['adaptiveData'], value);
          cell.setValue(valueStr);
          graph.refresh(cell);
          // 判断裕量
          if (cell['adaptiveData']['MaxMargin'] != '') {
            if (parseFloat(value) > parseFloat(cell['adaptiveData']['MaxMargin'])) {
              setAdaptiveNodeColor(cell, 'orange');
            }
          }
          if (cell['adaptiveData']['MinMargin'] != '') {
            if (parseFloat(value) < parseFloat(cell['adaptiveData']['MinMargin'])) {
              setAdaptiveNodeColor(cell, 'cornflowerblue');
            }
          }
        }
      });
      // 遍历所有参数节点为无值节点设置警告
      checkAdaptiveNodeComputeWarning();
      // 关闭提示
      $('body').mLoading('hide');
    }, 200);
  });
  Button_Count.style.marginTop = '3px';
  Button_Count.style.width = '45%';
  Button_Count.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Count.appendChild(I_Count);
  mxUtils.write(Button_Count, '模型计算');
  container.appendChild(Button_Count);
  // 生成【报告按钮】
  var I_Report = document.createElement('i');
  I_Report.style.paddingRight = '2px';
  I_Report.setAttribute('class', 'fas fa-sticky-note');
  var Button_Report = mxUtils.button('', function()
  {
    // 打开面板
    var dlg = new AdaptiveDesignReportTableDialog(editorUi);
    editorUi.showDialog(dlg.container, 1100, 550, true, true);
    dlg.init();
  });
  Button_Report.style.marginLeft = '8px';
  Button_Report.style.width = '45%';
  Button_Report.setAttribute('class', 'geBtn geSuccessBtn geBtnMini2');
  Button_Report.appendChild(I_Report);
  mxUtils.write(Button_Report, '决策报告');
  container.appendChild(Button_Report);
  // 生成【关系按钮】
  var I_Relationship = document.createElement('i');
  I_Relationship.setAttribute('class', 'fas fa-sitemap');
  var Button_Relationship = mxUtils.button('', function()
  {
    if (!nowSelectNode || nowSelectNode == null) {
      toastr.warning('请选择节点');
    } else {
      $('body').mLoading('show');
      setTimeout(function(){
        // 获取当前节点
        var nowCell = nowSelectNode;
        // 存入显示关系列表
        AdaptiveRelationshipSelectNodes[nowCell.id] = { cell: nowCell, relationCells: {} };
        // 处理上下级染色
        createAdaptiveRelationshipView(nowCell, 1);
        createAdaptiveRelationshipView(nowCell, 2);
        // 重新染色说有关系源
        for (var id in AdaptiveRelationshipSelectNodes) {
          var node = AdaptiveRelationshipSelectNodes[id]['cell'];
          setAdaptiveNodeColor(node, 'cadetblue');
        }
        // 关闭提示
        $('body').mLoading('hide');
      }, 200);
    }
  });
  Button_Relationship.style.marginTop = '5px';
  Button_Relationship.style.width = '45%';
  Button_Relationship.setAttribute('class', 'geBtn gePrimaryBtn geBtnMini2');
  Button_Relationship.appendChild(I_Relationship);
  mxUtils.write(Button_Relationship, '显示关系');
  container.appendChild(Button_Relationship);
  // 生成【关系列表】
  var I_Relationship_List = document.createElement('i');
  I_Relationship_List.style.paddingRight = '2px';
  I_Relationship_List.setAttribute('class', 'fas fa-share-alt');
  var Button_Relationship_List = mxUtils.button('', function()
  {
    // 弹出关系显示面板
    var dlg = new AdaptiveDesignRelationshipTableDialog(editorUi);
    editorUi.showDialog(dlg.container, 1000, 520, true, true);
    dlg.init();
  });
  Button_Relationship_List.style.marginLeft = '8px';
  Button_Relationship_List.style.width = '45%';
  Button_Relationship_List.setAttribute('class', 'geBtn geSuccessBtn geBtnMini2');
  Button_Relationship_List.appendChild(I_Relationship_List);
  mxUtils.write(Button_Relationship_List, '关系列表');
  container.appendChild(Button_Relationship_List);
  // 生成【清空关系按钮】
  var I_Relationship_Clean = document.createElement('i');
  I_Relationship_Clean.setAttribute('class', 'fas fa-eraser');
  I_Relationship_Clean.style.paddingRight = '3px';
  var Button_Relationship_Clean = mxUtils.button('', function()
  {
    $('body').mLoading('show');
    setTimeout(function(){
      // 清除所有节点的颜色
      cleanAdaptiveNodeColor();
      // 清空选中节点影响的目标节点原始颜色列表
      SaveSelectNodeTargetNodeColor = {};
      // 清空所有选中的显示关系记录节点
      AdaptiveRelationshipSelectNodes = {};
      // 关闭提示
      $('body').mLoading('hide');
    }, 200);
  });
  Button_Relationship_Clean.style.marginTop = '5px';
  Button_Relationship_Clean.style.marginBottom = '5px';
  Button_Relationship_Clean.style.width = '45%';
  Button_Relationship_Clean.setAttribute('class', 'geBtn geWarningBtn geBtnMini2');
  Button_Relationship_Clean.appendChild(I_Relationship_Clean);
  mxUtils.write(Button_Relationship_Clean, '隐藏关系');
  container.appendChild(Button_Relationship_Clean);
}