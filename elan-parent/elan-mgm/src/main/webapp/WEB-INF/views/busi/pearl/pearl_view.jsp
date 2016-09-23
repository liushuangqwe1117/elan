<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>珍珠详情</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
</head>
<body class="p-iframe">
	<div class="m-juese">
		<table>
			<tr>
				<th class="f-fw"><label>编号：</label></th>
				<td><c:out value="${obj.code }" /></td>
				<th class="f-fw"><label>点位：</label></th>
				<td>
					<c:out value="${obj.pointOneShow }" />
					-
					<c:out value="${obj.pointTwoShow }" />
				</td>
			</tr>
			<tr>
				<th class="f-fw"><label>颜色：</label></th>
				<td><c:out value="${obj.nlevelShow }" /></td>
				<th class="f-fw"><label>形状：</label></th>
				<td><c:out value="${obj.circleShow }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>光泽：</label></th>
				<td><c:out value="${obj.luminosityShow}" /></td>
				<th class="f-fw"><label>瑕疵度：</label></th>
				<td><c:out value="${obj.qualityShow }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>种类：</label></th>
				<td><c:out value="${obj.categoryShow }" /></td>
				<th class="f-fw"><label>产地：</label></th>
				<td><c:out value="${obj.prodPlaceShow }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>市场定价(元)：</label></th>
				<td><c:out value="${obj.marketPrice }" /></td>
				<th class="f-fw"><label>库存：</label></th>
				<td><c:out value="${obj.inventory }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>排序：</label></th>
				<td><c:out value="${obj.norder }" /></td>
				<th class="f-fw"><label>创建时间：</label></th>
				<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>				
			<tr>
				<th class="f-fw"><label>备注：</label></th>
				<td colspan="3"><c:out value="${obj.remark }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>图片：</label></th>
				<td colspan="3">
					<img src="${contextPath }/file/download.html?id=${obj.maxPic}" style="width: 200px;height: 200px;" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>