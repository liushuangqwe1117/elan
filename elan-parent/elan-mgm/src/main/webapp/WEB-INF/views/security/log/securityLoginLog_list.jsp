<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
		function queryForm(){
			$("#listQueryForm").submit();
		}
		function resetForm(){
			$("input[type='text']","#listQueryForm").val("");
			$("select[name='loginStatus']").val("");
		}
	</script>
</head>
<body class="p-iframe">
	<%@include file="/WEB-INF/views/includes/messages.jsp" %>
	
	<div class="m-searchform">
		<form id="listQueryForm" action="${contextPath }/security/loginlog/list.html" method="post">
			<div class="ipt">
				<label>登录账号：</label>
				<input type="text" id="operator" name="operator" value="${param.operator }" />
			</div>
			<div class="ipt">
				<label>登录状态：</label>
				<select name="loginStatus">
					<option></option>
					<c:forEach items="${loginStatuses }" var="en">
						<option value="${en.value }" <c:if test="${en.value == param.loginStatus }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="btn">
				<div class="f-fr">
					<a href="javascript:void(0)" onclick="queryForm();"><i class="i-query"></i>查询</a>
					<a href="javascript:void(0)" onclick="resetForm();"><i class="i-reset"></i>重置</a>
				</div>
				<div class="f-cb"></div>
			</div>
		</form>
	</div>

	<div class="m-table">
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>登录账号</th>
					<th>IP地址</th>
					<th>登录状态</th>
					<th>备注</th>
					<th>登录时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
					<tr class="${vs.count%2 == 0?'gray':''}">
						<td><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}"/></td>
						<td><c:out value="${row.operator }"/></td>
						<td><c:out value="${row.loginIp }"/></td>
						<td><c:out value="${row.loginStatus.displayName }"/></td>
						<td><c:out value="${row.description }"/></td>
						<td><fmt:formatDate value="${row.loginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
				<c:if test="${empty pageData.rows}">
					<tr><td colspan="6" class="emptyRecord">没有记录</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 分页控件引入 -->
		<%@include file="/WEB-INF/views/includes/pagination.jsp" %>
	</div>
</body>
</html>