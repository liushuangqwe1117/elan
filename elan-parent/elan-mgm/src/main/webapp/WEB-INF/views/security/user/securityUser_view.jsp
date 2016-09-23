<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>账号详情</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
</head>
<body class="p-iframe">
	<div class="m-juese">
		<table>
			<tr>
				<th class="f-fw"><label>登录账号：</label></th>
				<td><c:out value="${obj.loginName }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>姓名：</label></th>
				<td><c:out value="${obj.realName }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>固定电话：</label></th>
				<td><c:out value="${obj.phone }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>手机号码：</label></th>
				<td><c:out value="${obj.mobile }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>邮箱：</label></th>
				<td><c:out value="${obj.email }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>状态：</label></th>
				<td><c:out value="${obj.status.displayName }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>角色类型：</label></th>
				<td><c:out value="${obj.roleType.displayName }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>描述：</label></th>
				<td><c:out value="${obj.description }" /></td>
			</tr>
			<tr>
				<th  class="f-fw"><label>创建时间：</label></th>
				<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</table>
		<c:if test="${reqAction !='ncview' }">
		<div class="btn"><a class="formbtn back" href="${contextPath }/security/user/list.html">返回</a></div>
		</c:if>
	</div>
</body>
</html>