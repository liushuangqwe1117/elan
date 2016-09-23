<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemUser" value="admin" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>珍珠列表</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
		$(function(){
			$(".deleteBtnCls").click(function(){
				var action = $(this).attr("rel");
				jConfirm("您确定要删除珍珠[<font color='blue'>"+$(this).attr("data")+"</font>]吗？","提示",function(rtn){
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
		<form id="listQueryForm" action="${contextPath }/pearl/list.html" method="post">
			<div class="ipt">
				<label>编号：</label>
				<input type="text" name="code" value="${param.code }" />
			</div>
			<div class="ipt">
				<label>颜色：</label>
				<select name="nlevel">
					<option></option>
					<c:forEach items="${pearlNlevels }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.nlevel }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="ipt">
				<label>形状：</label>
				<select name="circle">
					<option></option>
					<c:forEach items="${pearlCircles }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.circle }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="ipt">
				<label>光泽：</label>
				<select name="luminosity">
					<option></option>
					<c:forEach items="${pearlNuminositys }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.luminosity }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="ipt">
				<label>瑕疵度：</label>
				<select name="quality">
					<option></option>
					<c:forEach items="${pearlQualitys }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.quality }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="ipt">
				<label>种类：</label>
				<select name="category">
					<option></option>
					<c:forEach items="${pearlCategorys }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.category }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="ipt">
				<label>产地：</label>
				<select name="prodPlace">
					<option></option>
					<c:forEach items="${pearlProdPlaces }" var="en">
						<option value="${en.id }" <c:if test="${en.id == param.prodPlace }">selected="selected"</c:if>><c:out value="${en.name }" /></option>	
					</c:forEach>
				</select>
			</div>
			<div class="btn">
				<div class="f-fl">
					<a href="${contextPath }/pearl/edit.html" class="export"><i class="i-export"></i>新增珍珠</a>
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
					<th>点位</th>
					<th>颜色</th>
					<th>形状</th>
					<th>光泽</th>
					<th>瑕疵度</th>
					<th>种类</th>
					<th>产地</th>
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
						<td><c:out value="${row.pointOneShow }"/>-<c:out value="${row.pointTwoShow }"/></td>
						<td><c:out value="${row.nlevelShow }"/></td>
						<td><c:out value="${row.circleShow }"/></td>
						<td><c:out value="${row.luminosityShow }"/></td>
						<td><c:out value="${row.qualityShow }"/></td>
						<td><c:out value="${row.categoryShow }"/></td>
						<td><c:out value="${row.prodPlaceShow }"/></td>
						<td><c:out value="${row.marketPrice }"/></td>
						<td><c:out value="${row.inventory }"/></td>
						<td>
							<div class="set">
								<i class="i-set"></i> 
								<span>
									<a href="${contextPath }/pearl/view.html?id=${row.id}">详情</a>
									<shiro:hasPermission name="PEARL_EDIT">
									<a href="${contextPath }/pearl/edit.html?id=${row.id}">编辑</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="PEARL_DELETE">
									<a href="javascript:void(0)" class="deleteBtnCls" data="${row.code }" rel="${contextPath }/pearl/delete.html?id=${row.id}">删除</a>
									</shiro:hasPermission>
								</span>
							</div>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty pageData.rows}">
					<tr><td colspan="12" class="emptyRecord">没有记录</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 分页控件引入 -->
		<%@include file="/WEB-INF/views/includes/pagination.jsp" %>
	</div>
</body>
</html>