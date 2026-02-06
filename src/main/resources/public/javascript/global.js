// 系统常量
var WEB_TITLE = '教学多元数据管理系统平台';//页面标题
var WEB_TITLE_MINI = '教学多元数据管理系统平台';//登录后标题
var WEB_TITLE2 = '';//供应商页面标题
var WEB_TITLE_MINI2 = '';
var YULANIP = 'http://39.102.231.88:9199';//系统部署的服务器ip:端口号
var YULANIP2 = 'http://39.102.231.88:8012/';//预览部署的服务器ip:端口号
var SERVERURL = '127.0.0.1:8080';//项目URL
var FOOTER_TEXT ="天津仁爱学院 数智传媒与设计艺术学院";//企业名称
var errormsg = "不好意思，您无权操作！";
var FSchooltree="天津仁爱学院 数智传媒与设计艺术学院"
// 初始化预设置变量
var VariableName = [
  'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
  'aa','bb','cc','dd','ee','ff','gg','hh','ii','jj','kk','ll','mm','nn','oo','pp','qq','rr','ss','tt','uu','vv','ww','xx','yy','zz',
  'aaa','bbb','ccc','ddd','eee','fff','ggg','hhh','iii','jjj','kkk','lll','mmm','nnn','ooo','ppp','qqq','rrr','sss','ttt','uuu','vvv','www','xxx','yyy','zzz'
];
// 加载公共 js 和 css
(function (){
  // ICON
  document.write('<link rel="Bookmark" href="/images/favicon.ico" />',
                 '<link rel="Shortcut Icon" href="/images/favicon.ico" />');
  // 浏览器版本判断
  document.write('<!--[if lt IE 9]>',
                 '<script type="text/javascript" src="/nui-ad/lib/html5shiv.js"></script>',
                 '<script type="text/javascript" src="/nui-ad/lib/respond.min.js"></script>',
                 '<![endif]-->');
  // 核心CSS文件加载
  document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/static/h-ui/css/H-ui.min.css" />');
  document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/static/h-ui.admin/css/style.css" />');
  if (golbal_type == 'login') {
    document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/static/h-ui.admin/css/H-ui.login.css" />');
  }else if (golbal_type == 'supplierlogin') {
    document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/static/h-ui.admin/css/H-ui.supplierlogin.css" />');
  }
  else if (golbal_type == 'admin') {
    document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/static/h-ui.admin/css/H-ui.admin.css" />');
  }
  document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/static/h-ui.admin/skin/blue/skin.css" />');
  document.write('<link rel="stylesheet" type="text/css" href="/nui-ad/lib/Hui-iconfont/1.0.8/iconfont.css" />');
  document.write('<link rel="stylesheet" type="text/css" href="/plugins/fontawesome-free/css/all.min.css" />');
  document.write('<link rel="stylesheet" type="text/css" href="/plugins/font-awesome-4.7.0/css/font-awesome.min.css" />');
  document.write('<link rel="stylesheet" type="text/css" href="/plugins/toastr/toastr.min.css" />');
  document.write('<link rel="stylesheet" type="text/css" href="/plugins/mloading/jquery.mloading.css" />');
  // 核心JS文件加载
  document.write('<script type="text/javascript" src="/nui-ad/lib/jquery/1.9.1/jquery.min.js"></script>',
                 '<script type="text/javascript" src="/nui-ad/static/h-ui/js/H-ui.min.js"></script>',
                 '<script type="text/javascript" src="/nui-ad/lib/layer/2.4/layer.js"></script>',
                 '<script type="text/javascript" src="/plugins/toastr/toastr.min.js"></script>',
                 '<script type="text/javascript" src="/plugins/mloading/jquery.mloading.js"></script>',
                 '<script type="text/javascript" src="/javascript/Extension.js"></script>',
                 '<script type="text/javascript" src="/javascript/tools.js"></script>');
  // 判断是否为admin框架页
  if (golbal_type == 'admin') {
    document.write('<script type="text/javascript" src="/nui-ad/static/h-ui.admin/js/H-ui.admin.js"></script>');
  }
  // 浏览器版本判断
  document.write('<!--[if IE 6]>',
                 '<script type="text/javascript" src="/nui-ad/lib/DD_belatedPNG_0.0.8a-min.js" ></script>',
                 '<script>DD_belatedPNG.fix('*');</script>',
                 '<![endif]-->');
  // 组件默认配置
  document.write('<script type="text/javascript" src="/javascript/DataTablesPublicParam.js"></script>',
                 '<script type="text/javascript" src="/javascript/DateRangePickerParam.js"></script>');
  // 初始化
  document.write(
    '<script type="text/javascript">'+
      'toastr.options = {'+
        'closeButton: false,'+ // 是否显示关闭按钮（提示框右上角关闭按钮）
        'debug: false,'+ // 是否为调试
        'progressBar: true,'+ // 是否显示进度条（设置关闭的超时时间进度条）
        'positionClass: "toast-top-center",'+ // 消息框在页面显示的位置
        'onclick: null,'+ // 点击消息框自定义事件
        'showDuration: "300",'+ // 显示动作时间
        'hideDuration: "1000",'+ // 隐藏动作时间
        'timeOut: "3000",'+ // 自动关闭超时时间
        'extendedTimeOut: "1000",'+ // 设置当你鼠标滑入后的timeout，该timeout会更新关闭所需的timeout
        'showEasing: "swing",'+
        'hideEasing: "linear",'+
        'showMethod: "fadeIn",'+ // 显示的方式
        'hideMethod: "fadeOut"'+ //隐藏的方式
      '};'+
    '</script>'
  );
})();