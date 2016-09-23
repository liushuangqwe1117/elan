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
				<th class="f-fw"><label>品类 ：</label></th>
				<td><c:out value="${obj.categoryShow }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>款式：</label></th>
				<td><c:out value="${obj.styleShow }" /></td>
				<th class="f-fw"><label>尺寸：</label></th>
				<td><c:out value="${obj.nsize }" /></td>
				
			</tr>
			<tr>
				<th class="f-fw"><label>材质：</label></th>
				<td><c:out value="${obj.materialShow }" /></td>
				<th class="f-fw"><label>镶嵌：</label></th>
				<td><c:out value="${obj.weightShow }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>市场价(元)：</label></th>
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