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
			$(".deleteBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要删除属性[<font color='blue'>"+$(this).attr("data")+"</font>]吗？","提示",function(rtn){
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
			 $("select[name='type']").val("");
		}
	</script>
</head>
<body class="p-iframe">
	<%@include file="/WEB-INF/views/includes/messages.jsp" %>
	
	<div class="m-searchform">
		<form id="listQueryForm" action="${contextPath }/productproperty/list.html" method="post">
			<div class="ipt">
				<label>类型：</label>
				<select name="type">
					<option></option>
					<c:forEach items="${ppes }" var="en">
						<option value="${en.value }" <c:if test="${en.value == param.type }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="btn">
				<div class="f-fl">
					<a href="${contextPath }/productproperty/edit.html" class="export"><i class="i-export"></i>新增产品属性</a>
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
					<th>类型</th>
					<th>名称</th>
					<th>排序</th>
					<th class="operCol">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
					<tr class="${vs.count%2 == 0?'gray':''}">
						<td><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}"/></td>
						<td><c:out value="${row.type.displayName }"/></td>
						<td><c:out value="${row.name }"/></td>
						<td><c:out value="${row.nord }"/></td>
						<td>
							<div class="set">
								<i class="i-set"></i> 
								<span>
									<a href="${contextPath }/productproperty/edit.html?id=${row.id}">修改</a>
									<a href="javascript:void(0)" class="deleteBtnCls" data="<c:out value="${row.name }"/>" rel="${contextPath }/productproperty/delete.html?id=${row.id}">删除</a>
								</span>
							</div>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty pageData.rows}">
					<tr><td colspan="5" class="emptyRecord">没有记录</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 分页控件引入 -->
		<%@include file="/WEB-INF/views/includes/pagination.jsp" %>
	</div>
</body>
</html>