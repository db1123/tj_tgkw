// 用户信息
var accessInfo;
try {
    //通过当前项目进来，如果通过外链则报错
    accessInfo = top.accessInfo;
} catch (e) {
}
if (!accessInfo) {
    //通过外链进来
    var accessInfos = getQueryStringByName('accessInfo');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/s/entry/jiemi', // 调用地址
        data: JSON.stringify({
            accessInfo: accessInfos
        }),
        async: false,
        success: function (data) {
            if (data.result == "success") {
                accessInfo = JSON.parse(data.accessInfoss);
            }
        },
        error: function (e) {
            toastr.error(e.status);
            toastr.error(e.responseText);
        }
    });

}
if (!accessInfo) {
    window.location.href = '/module/access404.html';
}