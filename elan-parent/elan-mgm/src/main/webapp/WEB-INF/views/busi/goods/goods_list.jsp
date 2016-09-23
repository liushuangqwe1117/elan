<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemUser" value="admin" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>成品列表</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
		$(function(){
			$(".deleteBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要删除成品[<font color='blue'>"+$(this).attr("data")+"</font>]吗？","提示",function(rtn){
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
			 $("select",".m-searchform").val("");
		}
	</script>
</head>
<body class="p-iframe">
	<%@include file="/WEB-INF/views/includes/messages.jsp" %>
	
	<div class="m-searchform">
		<form id="listQueryForm" action="${contextPath }/goods/list.html" method="post">
			<div class="ipt">
				<label>编号：</label>
				<input type="text" name="code" value="${param.code }" />
			</div>
			<div class="ipt">
				<label>种类：</label>
				<select name="category">
					<option></option>
					<c:forEach items="${goodsCategorys }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.category }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="ipt">
				<label>款式：</label>
				<select name="style">
					<option></option>
					<c:forEach items="${goodsStyles }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.style }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="btn">
				<div class="f-fl">
					<a href="${contextPath }/goods/edit.html" class="export"><i class="i-export"></i>新增成品</a>
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
					<th>编号</th>
					<th>种类</th>
					<th>款式</th>
					<th>市场价</th>
					<th>库存</th>
					<th class="operCol">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
					<tr class="${vs.count%2 == 0?'gray':''}">
						<td><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}"/></td>
						<td><c:out value="${row.code }"/></td>
						<td><c:out value="${row.categoryShow }"/></td>
						<td><c:out value="${row.styleShow }"/></td>
						<td><c:out value="${row.marketPrice }"/></td>
						<td><c:out value="${row.inventory }"/></td>
						<td>
							<div class="set">
								<i class="i-set"></i> 
								<span>
									<a href="${contextPath }/goods/view.html?id=${row.id}">详情</a>
									<shiro:hasPermission name="GOODS_EDIT">
									<a href="${contextPath }/goods/edit.html?id=${row.id}">编辑</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="GOODS_DELETE">
									<a href="javascript:void(0)" class="deleteBtnCls" data="${row.code }" rel="${contextPath }/goods/delete.html?id=${row.id}">删除</a>
									</shiro:hasPermission>
								</span>
							</div>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty pageData.rows}">
					<tr><td colspan="7" class="emptyRecord">没有记录</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 分页控件引入 -->
		<%@include file="/WEB-INF/views/includes/pagination.jsp" %>
	</div>
</body>
</html>