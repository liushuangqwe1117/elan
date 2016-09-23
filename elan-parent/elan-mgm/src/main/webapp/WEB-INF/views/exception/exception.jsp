<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出错啦</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
</head>
<body>
	<div style="padding:15px;font-size: 14px;color: red;">
		服务器端错误: ${exception.message}.
		 <!-- 
		 <c:forEach items="${exception.stackTrace}" var="stackTrace"> 
			${stackTrace} 
		</c:forEach>
	 	 --> 
	</div>
</body>
</html>