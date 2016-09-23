<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemUser" value="admin" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>产品属性</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
	$(function(){
		/**
		 * 数据校验
		 */
		$('#profile_form').validate({
			rules: {
				'name': {
					required: true ,
					rangelength : [1,40]
				},
				'nord' : {
					required: true ,
					digits: true
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
		<form:form id="profile_form" class="m-form" action="${contextPath }/productproperty/save.html" commandName="obj" method="post">
			<ofa:token/>
			<form:hidden path="id"/>
			<div class="ipt">
				<label>类型：</label>
				<c:choose>
					<c:when test="${obj.id != null && obj.id !=''}">
						<form:hidden path="type"/>
						<label style="text-align:left;"><c:out value="${obj.type.displayName }" /></label>
					</c:when>
					<c:otherwise>
						<form:select path="type" items="${ppes }" itemLabel="displayName" itemValue="value" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="ipt">
				<label>名称：</label>
				<form:input path="name" maxlength="40" />
			</div>
			<div class="ipt">
				<label>排序：</label>
				<form:input path="nord" maxlength="6" />
				(数字越小排序越靠前)
			</div>
			<div class="btn"><a href="javascript:void(0)" class="formbtn save">保存</a></div>
		</form:form>
	</div>
</body>
</html>