
$(function() {
    validateRule();
    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green-login"});
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
		login();
    }
});

function login() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $.common.trim($("input[name='username']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');

    if(password.length < 5 || password.length > 20){
        layer.msg("密码长度不正确,密码长度5-20位", {icon: 2});
        return;
    }
    if(username.length < 2 || username.length > 20){
        layer.msg("用户名长度不正确,用户名长度5-20位", {icon: 2});
        return;
    }

    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "type": "00",
            "validateCode" : validateCode,
            "rememberMe": rememberMe,
            "isAdmin":true
        },
        success: function(r) {

            if (r.code == 200) {
                location.href = ctx + 'index';
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}
