<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:set var="systemUser" value="admin" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>珍珠</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	
	<script type="text/javascript">
	$(function(){
		var maxPic = $("#maxPic").val();
		/**
		 * 数据校验
		 */
		$('#profile_form').validate({
			rules: {
				'code': {
					required: true ,
					rangelength : [1,30]
				},
				'nlevel': {
					required: true 
				},
				'point': {
					required: true 
				},
				'circle': {
					required: true 
				},
				'quality': {
					required: true 
				},
				'luminosity': {
					required: true 
				},
				'category': {
					required: true 
				},
				'prodPlace': {
					required: true 
				},
				'marketPrice': {
					required: true,
					digits: true
				},
				'inventory': {
					required: true,
					digits: true
				},
				'file': {
					required: maxPic?false:true
				}
				,
				'norder': {
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
		<form:form id="profile_form" class="m-form" action="${contextPath }/pearl/save.html" commandName="obj" method="post" enctype="multipart/form-data">
			<ofa:token/>
			<form:hidden path="id"/>
			<form:hidden path="maxPic"/>
			<div class="ipt">
				<label>*编号：</label>
				<form:input path="code" maxlength="30" />
				<label>*点位：</label>
				<form:select path="pointOne" items="${pearlPoints1 }" itemLabel="name" itemValue="id" cssStyle="width:100px;" />
				-
				<form:select path="pointTwo" items="${pearlPoints2 }" itemLabel="name" itemValue="id" cssStyle="width:100px;" />
			</div>
			<div class="ipt">
				<label>*颜色：</label>
				<form:select path="nlevel" items="${pearlNlevels }" itemLabel="name" itemValue="id" />
				<label>*形状：</label>
				<form:select path="circle" items="${pearlCircles }" itemLabel="name" itemValue="id" />
			</div>
			<div class="ipt">
				<label>*光泽：</label>
				<form:select path="luminosity" items="${pearlNuminositys }" itemLabel="name" itemValue="id" />
				<label>*瑕疵度：</label>
				<form:select path="quality" items="${pearlQualitys }" itemLabel="name" itemValue="id" />
			</div>
			<div class="ipt">
				<label>*种类：</label>
				<form:select path="category" items="${pearlCategorys }" itemLabel="name" itemValue="id" />
				<label>*产地：</label>
				<form:select path="prodPlace" items="${pearlProdPlaces }" itemLabel="name" itemValue="id" />
			</div>
			<div class="ipt">
				<label>*市场价(元)：</label>
				<form:input path="marketPrice" maxlength="18" />
				<label>*库存：</label>
				<form:input path="inventory" maxlength="64" />
			</div>
			<div class="ipt">
				<label>排序：</label>
				<form:input path="norder" maxlength="10" />
			</div>
			<div class="ipt">
				<label>备注：</label>
				<form:textarea path="remark" maxlength="300" cssStyle="width:538px;" />
			</div>
			<div class="ipt">
				<label>*图片：</label>
				<input type="file" name="file" />
			</div>
			<c:if test="${obj.maxPic != null && obj.maxPic !='' }">
			<div class="ipt">
				<label>&nbsp;</label>
				<img src="${contextPath }/file/download.html?id=${obj.maxPic}" style="width: 200px;height: 200px;" />
			</div>
			</c:if>
			<div class="btn"><a href="javascript:void(0)" class="formbtn save">保存</a></div>
		</form:form>
	</div>
</body>
</html>