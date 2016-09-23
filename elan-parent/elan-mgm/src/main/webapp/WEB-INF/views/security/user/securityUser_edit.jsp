<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemUser" value="admin" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑用户</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script src="${contextPath }/scripts/jquery/jquery.cookie.js" type="text/javascript"></script>
	<script src="${contextPath }/scripts/jquery/plugins/treeview/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${contextPath }/scripts/jquery/plugins/treeview/jquery.treeview.css" type="text/css">
	
	<script type="text/javascript">
	$(function(){
		//登录名称校验
		jQuery.validator.addMethod("valideLoginName", function(value, element) { 
			return this.optional(element) ||  /^[a-zA-Z_]+[0-9a-zA-Z_]*$/.test(value);   
		},"只能包含数字[0-9],英文字母[a-z,A-Z],下划线[_]，且只能以字符和下划线开头！");
		//密码校验
		jQuery.validator.addMethod("validePwd", function(value, element) { 
			return this.optional(element) ||  /^[a-zA-Z]+[0-9a-zA-Z]*$/.test(value);   
		},"只能包含数字[0-9],英文字母[a-z,A-Z]，且只能以字母开头！");
		//唯一性检查
		var checkLoginNameUrl = '${contextPath}/security/user/checkLoginName.html?id='+$("#id").val();
		/**
		 * 数据校验
		 */
		$('#profile_form').validate({
			rules: {
				'loginName': {
					required: true,
					rangelength : [2,30] ,
					valideLoginName : true,
					remote: {
						url: checkLoginNameUrl,
						type: 'post',
						dataType: 'json',
						data: {
							'loginName': function() {
								return $('#loginName').val();
							}
						}
					}
				},
				'realName': {
					required: true ,
					rangelength : [2,10]
				},
				'mobile' : {
					mobile : true
				},
				'email' : {
					email : true
				}
			},
			messages: {
				'loginName': {
					remote: '该登录账号已经存在'
				}
			}
		});
		
		/**
		 * 保存
		 */
		$(".formbtn.save").click(function(){
			//页面校验成功后则，则去掉保存按钮的click事件，防止用户重复提交
			if($("#profile_form").valid()) {
				$("#profile_form").submit();
				$(this).unbind("click");
			}
		});
	});
	</script>
</head>
<body class="p-iframe">
	<div class="m-edit">
		<%@include file="/WEB-INF/views/includes/messages.jsp" %>
		<form:form id="profile_form" class="m-form" action="${contextPath }/security/user/save.html" commandName="obj" method="post">
			<ofa:token/>
			<form:hidden path="id"/>
			<div class="ipt">
				<label>*登录账号：</label>
				<c:choose>
					<c:when test="${obj.id == null || obj.id == '' }">
						<form:input path="loginName" maxlength="30" />
					</c:when>
					<c:otherwise>
						<form:hidden path="loginName" maxlength="30" />
						<c:out value="${obj.loginName }" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="ipt">
				<label>*姓名：</label>
				<form:input path="realName" maxlength="20" />
			</div>
			<div class="ipt">
				<label>固定电话：</label>
				<form:input path="phone" maxlength="16" />
			</div>
			<div class="ipt">
				<label>手机号码：</label>
				<form:input path="mobile" maxlength="11" />
			</div>
			<div class="ipt">
				<label>邮箱：</label>
				<form:input path="email" maxlength="60" />
			</div>
			<c:if test="${!(obj.loginName != null && obj.loginName != '' && obj.loginName == systemUser) }">
			<div class="ipt">
				<label>角色类型：</label>
				<form:select path="roleType" items="${roleTypes}" itemValue="value" itemLabel="displayName" />	
			</div>
			</c:if>
			<div class="ipt">
				<label>描叙：</label>
				<form:textarea path="description" maxlength="80" cssStyle="width:200px;" />
			</div>
			<div class="btn"><a href="javascript:void(0)" class="formbtn save">保存</a></div>
		</form:form>
	</div>
</body>
</html>