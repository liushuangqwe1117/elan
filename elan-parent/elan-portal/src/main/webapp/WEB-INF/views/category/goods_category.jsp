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
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>分类</title>
<style type="text/css">
.brand-areat li {
	padding-left:2px;
	padding-top:3px;
	padding-bottom:3px;
}
.current {
	color:#8A6D3B;
}
</style>
</head>
<body>
<header class="header" style="height:142px;">
	<div class="fix_nav">
		<div style="max-width:768px;margin:0 auto;height: 44px;position: relative;background:#000000;padding-left:10px;font-size:16px;font-weight:bold;color:#FFF;">
			Elan报价系统
		</div>
	</div>
	<div class="fix_category">
		<div style="max-width:768px;margin:0 auto;height: 95px;border-bottom:8px solid #eee;position: relative;background:#FFF;padding-left:10px;font-size:16px;font-weight:bold;color:#000;">
			<ul>
				<c:forEach items="${topNavList }" var="nav">
					<li><a href="${contextPath }/main/category.html?type=${nav.type.value}&catId=${nav.id}" class="<c:out value="${nav.id == curSelectedBpp.id?'current':'' }" />"><c:out value="${nav.name }" /></a></li>	
				</c:forEach>
			</ul>
		</div>
	</div>
</header>

<div class="container">
	<div class="row" id="row_5">
		<div class="white-bg">
			<h4 class="sort-tit"><c:out value="${curSelectedBpp.name }"/></h4>
			<div class="sort-arat brand-areat">
				<ul>
					<c:forEach items="${pageData.rows }" var="row">
					<li>
						<input type="checkbox" class="ids" catType="${row.category}" rel="${row.id }"/>
						<a href="${contextPath }/main/view.html?type=${curSelectedBpp.type.value }&id=${row.id}">
							<c:out value="${row.code }" />&nbsp;
							<c:out value="${row.categoryShow }" />&nbsp;
							<c:out value="${row.styleShow }" />&nbsp;
							￥<c:out value="${row.marketPrice }" />
						</a>
					</li>
					</c:forEach>
					<li>
						<c:if test="${pagination.currentPage > 1 }">
						<a href="${contextPath }/main/category.html?pageNumber=${pagination.prePage }&type=${curSelectedBpp.type.value}&catId=${curSelectedBpp.id}" style="background-color:#e5e5e5;text-align:center;font-weight:bold;border-right-color:#fff;">上一页</a>
						</c:if>
						<c:if test="${pagination.currentPage < pagination.lastPage }">
						<a href="${contextPath }/main/category.html?pageNumber=${pagination.nextPage }&type=${curSelectedBpp.type.value}&catId=${curSelectedBpp.id}" style="background-color:#e5e5e5;text-align:center;font-weight:bold;">下一页</a>
						</c:if>
					</li>
				</ul>
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
		<a href="${contextPath }/main/category.html" class="active">
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
<script type="text/javascript">
	$(function(){
		 var mainCategory="${curSelectedBpp.type.value}";
		 //默认选择
		 var ca = document.cookie.split(';');
		 for(var i=0; i<ca.length; i++) {
		   var c = ca[i].trim();
		   var vals=c.split("=");
		   if(vals.length ==2 && vals[1]) {
			   var vs = vals[1].split("-");
			   if(vs[1] == "1") {
				   $("input[type='checkbox'][rel='"+vs[0]+"']").attr("checked",true);   
			   }
		   }
		 }
		//选择
		$("input[type='checkbox'][class='ids']").click(function(){
			var type=$(this).attr("catType");
			var id=$(this).attr("rel");
			$("input[type='checkbox'][rel !='"+id+"']").attr("checked",false);
			
			if(!!$(this).attr("checked")) {
				jQuery.setCookie(type,id+"-1");	
			} else {
				jQuery.setCookie(type,id+"-0");
			}
		});
	});
</script>
</body>
</html>