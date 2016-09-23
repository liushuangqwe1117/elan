<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript" src="${contextPath }/scripts/jquery/plugins/cryptography/jquery.md5.js"></script>
	<script type="text/javascript">
	/**
	 * 数据校验
	 */
	$(function(){
		//密码校验
		jQuery.validator.addMethod("validePwd", function(value, element) { 
			return this.optional(element) ||  /^[a-zA-Z]+[0-9a-zA-Z]*$/.test(value);   
		},"只能包含数字[0-9],英文字母[a-z,A-Z]，且只能以字母开头！");
		
		jQuery.validator.addMethod("notEqualTo", function(value, element) { 
			return !(value == $("#soldPwd").val());   
		},"新密码不能与原密码相同");
		
		$("#password_form").validate({
			rules:{
				'soldPwd': { 
					required: true
				},
				"snewPwd":{
					required:true,
					minlength:8,
					maxlength:30,
					notEqualTo:true,
					validePwd : true
				},
				"sconfirmNewPwd":{
					required:true,
					equalTo:"#snewPwd"
				}
			},
			messages:{
				'soldPwd': { 
					required: '请输入原密码！'
				},
				'snewPwd' : {
					required : '请输入新密码',
					minlength : '密码最小长度为8位',
					maxlength : '密码最大长度为30位',
				},
				"sconfirmNewPwd": {
					required : '请输入重复新密码',
					equalTo:"'重复新密码'必须与'新密码'相同！"
				}
			}
		});
	});
	
	function submitForm() {
		if($("#password_form").valid()) {
			$("#oldPwd").val($.md5($.trim($("#soldPwd").val())));
			$("#newPwd").val($.md5($.trim($("#snewPwd").val())));
			$("#confirmNewPwd").val($.md5($.trim($("#sconfirmNewPwd").val())));
			$("#password_form").submit();
		}
	}
	</script>
</head>
<body class="p-iframe">
	<div class="m-edit">
		<%@include file="/WEB-INF/views/includes/messages.jsp" %>
		<form id="password_form" class="m-form" action="${contextPath }/security/user/changepwd.html" method="POST">
			<ofa:token/>
			<input type="hidden" name="fromPage" value="${fromPage}" />
			<input type="hidden" name="userId" value="${login_account.id }" />
			<input type="hidden" id="oldPwd" name="oldPwd" />
			<input type="hidden" id="newPwd" name="newPwd" />
			<input type="hidden" id="confirmNewPwd" name="confirmNewPwd" />
			<div class="ipt">
				<label>用户账号：</label>
				<shiro:principal />
			</div>
			<div class="ipt">
				<label>*原密码：</label>
				<input type="password" id="soldPwd" name="soldPwd" maxlength="64">
			</div>
			<div class="ipt">
				<label>*新密码：</label>
				<input type="password" id="snewPwd" name="snewPwd" maxlength="64">
			</div>
			<div class="ipt">
				<label>*重复新密码：</label>
				<input type="password" id="sconfirmNewPwd" name="sconfirmNewPwd" maxlength="64">
			</div>
			<div class="btn"><a href="javascript:void(0)"  onclick="submitForm();" class="formbtn save">修改密码</a></div>
		</form>
	</div>
</body>
</html>