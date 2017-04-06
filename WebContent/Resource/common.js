function PostDataToServ(LayFilter, RequestMethod, RequestUrl, RedirectUrl, OnStartPostFunc, SuccessCallback, ErrorCallback) {
    layui.use('form', function () {
        var form = layui.form();
        form.on('submit(' + LayFilter + ')', function (postData) {
            if (OnStartPostFunc != null) {
                postData = OnStartPostFunc(postData);
            }
            AjaxToServ(postData.field, RequestMethod, RequestUrl, RedirectUrl, OnStartPostFunc, SuccessCallback, ErrorCallback);
            return false;
        });
    });
}

function AjaxToServ(Data, RequestMethod, RequestUrl, RedirectUrl, OnStartPostFunc, SuccessCallback, ErrorCallback) {
    $.ajax({
        type: RequestMethod,
        url: RequestUrl,
        data: Data,
        success: function (data) {
            if (data.IsSuccess) {
                if (SuccessCallback != null) {
                    SuccessCallback(data);
                }
                else {
                    if (RedirectUrl != null && RedirectUrl != '') {
                        window.location.href = RedirectUrl;
                    }
                    else {
                        layui.use('layer', function () {
                            layer.msg(data.Msg);
                        });
                    }
                }
            } else {
                layui.use('layer', function () {
                    layer.msg(data.Msg);
                });
            }
        },
        error: function () {
        }
    });
}

function OpenWindowIframe(url, width, height) {
    layui.use('layer', function () {
        layer.open({
            type: 2,
            area: [width, height],
            fixed: false, //不固定
            maxmin: true,
            content: url
        });
    });
}