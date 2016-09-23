<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemRole" value="SUPER_ROLE" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
		$(function(){
			//删除角色
			$(".deleteBtnCls").click(function() {
				var action = $(this).attr("rel");
				jConfirm("您确定要删除角色[<font color='blue'>" + $(this).attr("data") + "</font>]吗？", "提示",
					function(rtn) {
						if (rtn) {
							window.location.href = action;
						}
					}
				);
			});
			
		});
		function queryForm(){
			$("#listQueryForm").submit();
		}
		function resetForm(){
			$("input[type='text']","#listQueryForm").val("");
			$("select[name='roleType']").val("");
		}
	</script>
</head>
<body class="p-iframe">
	<%@include file="/WEB-INF/views/includes/messages.jsp" %>
	
	<div class="m-searchform">
		<form id="listQueryForm" action="${contextPath }/security/role/list.html" method="post">
			<div class="ipt">
				<label>角色名称：</label>
				<input type="text" id="name" name="name" value="${param.name }" />
			</div>
			<div class="ipt">
				<label>角色类型：</label>
				<select name="roleType">
					<option></option>
					<c:forEach items="${roleTypes }" var="en">
						<option value="${en.value }" <c:if test="${en.value == param.roleType }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="btn">
				<div class="f-fl">
					<a href="${contextPath }/security/role/edit.html" class="export"><i class="i-export"></i>新增角色</a>
				</div>
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
					<th>角色名称</th>
					<th>角色类型</th>
					<th>描述</th>
					<th>创建时间</th>
					<th class="operCol">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
					<tr class="${vs.count%2 == 0?'gray':''}">
						<td><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}"/></td>
						<td><c:out value="${row.name }"/></td>
						<td><c:out value="${row.roleType.displayName }"/></td>
						<td><c:out value="${row.description }"/></td>
						<td><fmt:formatDate value="${row.createdTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<div class="set">
								<i class="i-set"></i> 
								<span>
									<a href="${contextPath }/security/role/view.html?roleId=${row.id}">角色详情</a>
									<c:if test="${row.id != systemRole}">
										<a href="${contextPath }/security/role/edit.html?roleId=${row.id}">编辑角色</a>
										<a href="javascript:void(0)" data="<c:out value="${row.name }"/>" class="deleteBtnCls" rel="${contextPath }/security/role/delete.html?roleId=${row.id}&roleName=${row.name}">删除角色</a>
									</c:if>
								</span>
							</div>
						</td>
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