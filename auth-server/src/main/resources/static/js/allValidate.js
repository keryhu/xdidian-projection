'use strict';

function allValidate() {
    var username = $.trim($("#username").val());
    var usernameEmpty = isEmpty(username);
    var password = $.trim($("#password").val());
    var passwordEmpty = isEmpty(password);
    var noEmpty = (!usernameEmpty && !passwordEmpty);
    var errorNum = 0;
    //统计页面错误显示的个数。
    $(".control-group").find('.help-inline')
        .each(function () {
                var e = $(this).text().trim();
                if (!isEmpty(e))
                    errorNum++;
            }
        )

    return noEmpty && errorNum === 0;
}



