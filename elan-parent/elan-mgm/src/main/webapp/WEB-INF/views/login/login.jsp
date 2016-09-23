<!DOCTYPE html>
<%@ page language ="java" pageEncoding = "UTF-8" contentType="text/html;charset=UTF-8" %> 
<%@ include file="/WEB-INF/views/includes/taglibs.jsp" %>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>管理平台登录页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/images/favicon.ico" media="screen" />
		<link rel="stylesheet" type="text/css" href="${contextPath }/styles/project/red/style.css" />
		<script type="text/javascript" src="${contextPath }/scripts/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${contextPath }/scripts/jquery/jquery.fix.js"></script>
		<script type="text/javascript" src="${contextPath }/scripts/jquery/plugins/cryptography/jquery.md5.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#username,#password,#captcha").keydown(function(){
				$('div.ipt-error').hide(200);
				$('div.ipt-error').html('');	
			});
			//后台错误显示
			if($.trim($('div.ipt-error').text()) != '') {
				$('div.ipt-error').show(200);
			}
		});

		/**
		 * 显示提示信息
		 */
		function showMessage( msg ){
			$('div.ipt-error').text( msg );
			$('div.ipt-error').show(200);
		}
		
		function handleEnter(event) {
			var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
			if (keyCode == 13) {
				login();
			}
		}

		function login(){
			if($.trim($("#username").val()) == ''){
				showMessage("账号不能为空！");
				$("#username").focus();
				return false;
			} else if($.trim($("#password").val()) == ''){
				showMessage("密码不能为空！");
				$("#password").focus();
				return false;
			} else if(document.getElementById("captcha") && $.trim($("#captcha").val()) == ''){
				showMessage("验证码不能为空！");
				$("#captcha").focus();
				return false;
			}
			$("#j_password").val($.md5($.trim($("#password").val())));
			$("#login_form").submit();
			return true;
		}
		
		function refresh(){
			var src = "${contextPath}/images/kaptcha.jpg?rnd=" + Math.random();
			$("#captchaImg").attr("src",src);
		}
		//如果当前登录页面不在顶级窗口则，顶级窗口返回到登录页面
		if(self.location != top.location) {
			top.location.href = self.location.href;
		}
	</script>
	</head>

	<body class="p-login" onkeydown="handleEnter(event)">
		<div class="g-row-pic">
			<div class="m-ship"></div>
			<div class="m-login">
				<h2>管理登录</h2>
				<div class="ipt-error" style="display:none;">
					<jsp:include page="login_error.jsp" />
				</div>
				<form id="login_form" action="${contextPath}/login.html" method="post">
					<input type="hidden" id="j_password" name="j_password" />
					<div class="ipt">
						<label>账&nbsp;&nbsp;&nbsp;号</label>
						<input id="username" name="j_username" type="text" value="admin"/>
					</div>
					<div class="ipt">
						<label>密&nbsp;&nbsp;&nbsp;码</label>
						<input id="password" type="password" value="admin123456" />
					</div>
					<div class="ipt ipt-code">
						<label>验证码</label>
						<input id="captcha" name="j_captcha" type="text" value="" maxlength="5"/>
                  		<img width="100px" id="captchaImg" height="28px" alt="刷新验证码" title="刷新验证码" src="${contextPath}/images/kaptcha.jpg"  onclick="refresh();" style="line-height: 30px;cursor: pointer;vertical-align:middle;">
					</div>
					<div class="ipt ipt-btn">
						<a href="javascript:void(0)" class="btn" onclick="login();">登录</a>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>