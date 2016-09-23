<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemUser" value="admin" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
		$(function(){
			//激活用户
			$(".activeBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要激活用户[<font color='blue'>"+$(this).attr("data")+"</font>]吗？","提示",function(rtn){
					if(rtn){
						window.location.href=action;
					}
				});
			});
			//启用用户
			$(".unlockBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要解冻用户[<font color='blue'>"+$(this).attr("data")+"</font>]吗？","提示",function(rtn){
					if(rtn){
						window.location.href=action;
					}
				});
			});
			//停用用户
			$(".lockBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要冻结用户[<font color='blue'>"+$(this).attr("data")+"</font>]吗，该操作会导致该用户无法登录？","提示",function(rtn){
					if(rtn){
						window.location.href=action;
					}
				});
			});
			//注销用户
			$(".deleteBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要注销用户[<font color='blue'>"+$(this).attr("data")+"</font>]吗，该操作会导致该用户无法登录？","提示",function(rtn){
					if(rtn){
						window.location.href=action;
					}
				});
			});
			
			//重置密码
			$(".resetBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要重置用户[<font color='blue'>"+$(this).attr("data")+"</font>]的登录密码吗？","提示",function(rtn){
					if(rtn){
						window.location.href=action;
					}
				});
			});
			
		});
		function queryForm(){
			$("#listQueryForm").submit();
		}
		function resetForm(){
			$("input[type='text']","#listQueryForm").val("");
			 //对select元素重置无效
			 $("select[name='roleType']").val("");
			 $("select[name='status']").val("");
		}
	</script>
</head>
<body class="p-iframe">
	<%@include file="/WEB-INF/views/includes/messages.jsp" %>
	
	<div class="m-searchform">
		<form id="listQueryForm" action="${contextPath }/security/user/list.html" method="post">
			<div class="ipt">
				<label>登录账号：</label>
				<input type="text" name="loginName" value="${param.loginName }" />
			</div>
			<div class="ipt">
				<label>姓名：</label>
				<input type="text" name="realName" value="${param.realName }" />
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
			<div class="ipt">
				<label>状态：</label>
				<select name="status">
					<option></option>
					<c:forEach items="${userStatuses }" var="en">
						<option value="${en.value }" <c:if test="${en.value == param.status }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="btn">
				<div class="f-fl">
					<a href="${contextPath }/security/user/edit.html" class="export"><i class="i-export"></i>新增用户</a>
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
					<th>登录账号</th>
					<th>姓名</th>
					<th>状态</th>
					<th>手机号码</th>
					<th>邮箱</th>
					<th>角色类型</th>
					<th>创建时间</th>
					<th class="operCol">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
					<tr class="${vs.count%2 == 0?'gray':''}">
						<td><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}"/></td>
						<td><c:out value="${row.loginName }"/></td>
						<td><c:out value="${row.realName }"/></td>
						<td><c:out value="${row.status.displayName }"/></td>
						<td><c:out value="${row.mobile }"/></td>
						<td><c:out value="${row.email }"/></td>
						<td><font style="color: green;"><c:out value="${row.roleType.displayName }" /></font></td>
						<td><fmt:formatDate value="${row.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<div class="set">
								<i class="i-set"></i> 
								<span>
									<a href="${contextPath }/security/user/view.html?id=${row.id}">用户详情</a>
									<c:if test="${row.status.value != 'DELETE' }">
										<a href="${contextPath }/security/user/edit.html?id=${row.id}">编辑用户</a>
										<c:if test="${row.loginName != systemUser }">
											<c:if test="${row.status.value == 'WAIT_ACTIVE' }">
											<a href="javascript:void(0)" class="activeBtnCls" data="<c:out value="${row.loginName }"/>" rel="${contextPath }/security/user/active.html?id=${row.id}&loginName=${row.loginName}">激活用户</a>
											</c:if>
											<c:if test="${row.status.value == 'DISABLED' }">
											<a href="javascript:void(0)" class="unlockBtnCls" data="<c:out value="${row.loginName }"/>" rel="${contextPath }/security/user/unlock.html?id=${row.id}&loginName=${row.loginName}">解冻用户</a>
											</c:if>
											<c:if test="${row.status.value == 'EFFECTIVE' }">
											<a href="javascript:void(0)" class="lockBtnCls" data="<c:out value="${row.loginName }"/>" rel="${contextPath }/security/user/lock.html?id=${row.id}&loginName=${row.loginName}">冻结用户</a>
											</c:if>
											<a href="javascript:void(0)" class="resetBtnCls" data="<c:out value="${row.loginName }"/>" rel="${contextPath }/security/user/resetpwd.html?id=${row.id}&loginName=${row.loginName}">重置密码</a>
											<a href="javascript:void(0)" class="deleteBtnCls" data="<c:out value="${row.loginName }"/>" rel="${contextPath }/security/user/delete.html?id=${row.id}&loginName=${row.loginName}">删除用户</a>
										</c:if>
									</c:if>
								</span>
							</div>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty pageData.rows}">
					<tr><td colspan="9" class="emptyRecord">没有记录</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 分页控件引入 -->
		<%@include file="/WEB-INF/views/includes/pagination.jsp" %>
	</div>
</body>
</html>