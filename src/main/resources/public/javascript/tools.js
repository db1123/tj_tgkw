/*********************************************
 ** ----------------- 表单类 --------------- **
 *********************************************/
// 获取表单所有参数集合
function form2Json(formId) {
    var paramArray = $('#' + formId).serializeArray();
    /*请求参数转json对象*/
    var jsonObj = {};
    $(paramArray).each(function () {
        jsonObj[this.name] = this.value;
    });
    return jsonObj;
}
function form2JsonString(formId) {
    // json对象再转换成json字符串
    return JSON.stringify(form2Json(formId));
}
// 清空表单
function reset_data(formId) {
    // 重置下拉列表
    $('#' + formId + ' select').val(null).trigger('change');
    // 重置其它组件
    $(':input', '#' + formId)
        .not(':button', ':reset', ':hidden', ':submit')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

/*********************************************
 ** ----------------- 判断类 --------------- **
 *********************************************/
// 判断是否为空 true空 false不为空
function isNull(value) {
    if (typeof value == 'undefined' || !value || value == null || value == 0) {
        return true; // 空
    } else {
        return false; // 不为空
    }
}
// 判断是否为JSON格式
function isJSON(obj) {
    return typeof (obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
}

/*********************************************
 ** --------------- 处理文本类 ------------- **
 *********************************************/
// 多余显示省略号
function wordlimit(str, len) {
    // length属性读出来的汉字长度为 1
    if (str.length * 2 <= len) {
        return str;
    }
    var strlen = 0;
    var s = "";
    for (var i = 0; i < str.length; i++) {
        s = s + str.charAt(i);
        if (str.charCodeAt(i) > 128) {
            strlen = strlen + 2;
            if (strlen >= len) {
                return s.substring(0, s.length - 1) + "...";
            }
        } else {
            strlen = strlen + 1;
            if (strlen >= len) {
                return s.substring(0, s.length - 2) + "...";
            }
        }
    }
    return s;
}
// 计算字符串宽度
function getLenPx(text, fontSize) {
    // 创建临时元素
	const _span = document.createElement('span')
	// 放入文本
	_span.innerText = text
	// 设置文字大小
	_span.style.fontSize = fontSize + 'px'
	// span元素转块级
	_span.style.position = 'absolute'
	// span放入body中
	document.body.appendChild(_span)
	// 获取span的宽度
	let width = _span.offsetWidth
	// 从body中删除该span
	document.body.removeChild(_span)
	// 返回span宽度
	return width;
}

/*********************************************
 ** --------------- 数据获取类 ------------- **
 *********************************************/
// 获取随机数
function getRandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    return (Min + Math.round(Rand * Range));
}
// 获取Cookie参数
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
// 获取url参数
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return decodeURIComponent(result[1]);
}
// 获取id
function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

/*********************************************
 ** -------------- HTML处理类 ------------- **
 *********************************************/
// 在头部加入js或css文件
function loadJsCssFile(filename, filetype) {
    var file, doc = document;
    if (filetype == 'js') {
        file = doc.createElement('script');
        file.setAttribute('type', 'text/javascript');
        file.setAttribute('src', filename);
    } else if (filetype == 'css') {
        file = doc.createElement('link');
        file.setAttribute('rel', 'stylesheet');
        file.setAttribute('type', 'text/css');
        file.setAttribute('href', filename);
    }
    if (typeof file != 'undefined') {
        doc.getElementsByTagName('head')[0].appendChild(file);
    }
}
// 过滤所有HTML标签
function cleanHTML(str) {
    return str.replace(/<[^<>]+>/g, '');
}
// 设置iframe高度
function setIframeHeight(iframe, offset) {
    if (iframe) {
        iframe.height = (document.documentElement.scrollHeight || document.body.scrollHeight) - offset;
    }
};

/*********************************************
 ** ---------------- 日期类 --------------- **
 *********************************************/
// 获取当期系统日期+时间
function nowDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var strDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var strHours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var strMinutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var strSeconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    var currentDate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + strHours + seperator2 + strMinutes + seperator2 + strSeconds;
    return currentDate;
}
// 比较两个时间的大小
function bjDate(date1, date2) {
    var date1 = new Date(date1);
    var date2 = new Date(date2);
    if (date1.getTime() - date2.getTime() < 0) {
        return false; // 第二个时间大
    } else {
        return true; // 第一个时间大
    }
}
// 格式化日期
function formatDate(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
}
// 日期加法-增加天数
function addDDate(date, days) {
    var d = new Date(date);
    d.setDate(d.getDate() + parseInt(days));
    return formatDate(d);
}
// 日期加法-增加月数
function addDMonth(date, months) {
    var d = new Date(date);
    d.setMonth(d.getMonth() + parseInt(months));
    return formatDate(d);
}
// 日期加法-增加年数
function addDYear(date, years) {
    var d = new Date(date);
    d.setYear(d.getFullYear() + parseInt(years));
    return formatDate(d);
}

/*********************************************
 ** ---------------- 其它类 --------------- **
 *********************************************/
// 格式化graph的cells
function formatCells(cells) {
    var resultJSON = {};
    var jsonObject = {};
    var jsonArray = [];
    for (var name in cells) {
        var element = cells[name];
        var jsonObj = {};
        for (var item in element) {
            if (item != 'children' && item != 'parent' && typeof (element[item]) != 'function') {
                if (item == 'source' || item == 'target') {
                    jsonObj[item] = isNull(element[item]) ? element[item] : element[item]['id'];
                } else if (item == 'edges') {
                    if (isNull(element[item])) {
                        jsonObj[item] = element[item];
                    } else {
                        var edgeArray = [];
                        $(element[item]).each(function () {
                            var obj = {};
                            obj['id'] = this.id;
                            edgeArray.push(obj);
                        });
                        jsonObj[item] = edgeArray;
                    }
                } else {
                    jsonObj[item] = element[item];
                }
            }
        }
        jsonObject[jsonObj['id']] = jsonObj;
        jsonArray.push(jsonObj);
    }
    resultJSON['objectData'] = jsonObject;
    resultJSON['arrayData'] = jsonArray;
    return resultJSON;
}
// 休眠
function sleep(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}
//动态处理保留小数点后几位的问题
function paramXSD(jg) {

    var y = String(jg).indexOf(".") + 1;//获取小数点的位置
    var vv = 100;
    var z = 0;//非零个数
    if (y > 0) {
        var aaa = jg.toString().split(".")[1];
        if (aaa.length > 2) {
          if(aaa[0]!=0 || aaa[1] !=0){
            vv = 100;
          }else{
            var index = 0;
            var indexo = 0;
            for (var i = 0; i < aaa.length; i++) {
              if (aaa[i] != 0) {
                index = i;
                z++;
              }
              if (index != 0 && aaa[i] != 0 && (i + 1) == aaa.length) {
                indexo = i;
                break;
              }
              if (index != 0 && aaa[i] == 0 && (i + 1) < aaa.length) {
                indexo = i;
                break;
              }
            }

            var len = 0;
            if (indexo < 2) {
              len = 2;
            } else {

              if (z >= 3) {
                len = indexo;
              } else {
                len = indexo + 1;
              }
            }

            vv = Math.pow(10, len);
          }
        }

    }
    return Math.round(jg * vv) / vv;
}
// 请求JQuery Ajax请求拦截器
$.ajaxSetup({
    beforeSend:function(xhr) {
        xhr.setRequestHeader("simulation_dmsnid",localStorage.getItem("simulation_dmsnid"));
    }
});