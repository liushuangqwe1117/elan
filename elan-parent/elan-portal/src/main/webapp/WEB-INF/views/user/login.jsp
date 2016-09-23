<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="${contextPath }/js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="${contextPath }/js/global.js?v=01291"></script>
<script charset="utf-8" src="${contextPath }/js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="${contextPath }/js/template.js?v=01291"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css?v=01291">
<link rel="stylesheet" href="${contextPath }/css/style.css?v=1?v=01291">
<link rel="stylesheet" href="${contextPath }/css/member.css?v=01291">
<link rel="stylesheet" href="${contextPath }/css/order3.css?v=01291">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
<title>会员登录</title>
</head>
<body class="" style="background-color:#fff">
<header class="header">
    <div class="fix_nav">
        <div style="max-width:768px;margin:0 auto;background:#000000;position: relative;">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit" style="font-size:18px;">会员登陆</div>
        </div>
    </div>
</header>
<div class="maincontainer">
   <div class="container itemdetail mini-innner">
    <div class="row">
        <div class="col-md-12 tal mt20">
            <form  id="theForm"  name="theForm" class="form-signin"  method="POST" >  
              <input name="j_username"   id="username" type="text" style="margin-bottom: -2px;-webkit-appearance:none; " class="form-control" placeholder="帐户名/手机号码" message="账号" required="true" autofocus=""  tabindex="1" >
              <br>
              <input id="pwd" name="j_password" type="password" class="form-control" placeholder="请输入密码" message="密码" required="true" style="-webkit-appearance:none;" autocomplete="off"  tabindex="2" >
	          <label class="checkbox">
	                     <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox"   tabindex="4" /> 记住我
	                	 <a href="javascript:void(0)" style="float:right">忘记密码</a> 
	              </label>
                <div class="clear"></div>
                <div class="col-xs-6 p0"><button type="button" class="btn btn-info btn-block"  tabindex="5" >登  陆</button></div>
                <div class="col-xs-5 p0" style="margin-left:10px;"><button type="button" class="btn btn-default btn-block"  tabindex="6">注 册</button></div>
            </form>
        </div>
     </div>
	</div>
</div>
<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="${contextPath }/main/index.html">
			<i class="navIcon home"></i>
			<span class="text">首页</span>
		</a>
		<a href="${contextPath }/main/category.html">
			<i class="navIcon sort"></i>
			<span class="text">分类</span>
		</a>
		<a href="${contextPath }/shopcart/index.html">
			<i class="navIcon shop"></i>
			<span class="text">购物车</span>		
		</a>
		<a href="${contextPath }/user/index.html" class="active">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
</body>
</html>