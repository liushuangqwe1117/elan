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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
<title></title>
<style type="text/css">
.details_con li .tb-out-of-stock{
	border: 1px dashed #bbb;
	color:#bbb;
	cursor: not-allowed;
}
.gdetail {
	width:100%;
}
.gdetail th ,.gdetail td {
	padding: 3px;
}
.gdetail th {
	text-align: right;
}
.gdetail td {
	text-align: left;
}
.secondth {
	padding-left: 20px;
}
</style>
</head>

<body>
<header class="header">
	<div class="fix_nav">
		<div style="max-width:768px;margin:0 auto;background:#000;position: relative;">
			<a class="nav-left back-icon" href="javascript:history.back();">返回</a>
			<div class="tit">商品详细</div>
		</div>
	</div>
</header>
<input type="hidden" id="prodId" value="663"/>
<input id="currSkuId" value="" type="hidden"/>
<div class="container">
    <div class="row white-bg">
        <div id="slide">
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="position: relative; overflow: hidden; padding: 0px; margin: 0px;">
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        	<a href="javascript:void(0);"><img style="max-width:100vw;max-height:80vw;margin:auto;" src="${contextPath }/file/download.html?id=${obj.maxPic}"></a>
						</li>
                    </ul>
               </div>
            </div>
        </div>
    </div>
    <div class="row gary-bg">
         <div class="white-bg p10 details_con">
         <table class="gdetail">
         	<tr>
         		<th>商城价格：</th><td>¥<span id="prodCash"><c:out value="${obj.marketPrice }" /></span></td>
         		<th class="secondth">编号：</th><td><c:out value="${obj.code }" /></td>
         	</tr>
         	<tr>
         		<th>种类：</th><td><c:out value="${obj.categoryShow }" /></td>
         		<th class="secondth">款式：</th><td><c:out value="${obj.styleShow }" /></td>
         	</tr>
         </table>
         <div style="text-align: right;"><a class="btn btn-info btn-cart"  href="javascript:void(0);">加入购物车</a></div>
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
		<a href="${contextPath }/user/index.html" >
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
</body>
</html>