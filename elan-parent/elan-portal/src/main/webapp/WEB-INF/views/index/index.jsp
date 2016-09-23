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
<link rel="stylesheet" href="${contextPath }/css/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
<style type="text/css">
.current {
	color:#8A6D3B;
}
</style>
<title>Elan报价系统</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
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
					<li><a href="${contextPath }/main/index.html?type=${nav.type.value}&catId=${nav.id}" class="<c:out value="${nav.id == curSelectedBpp.id?'current':'' }" />"><c:out value="${nav.name }" /></a></li>	
				</c:forEach>
			</ul>
		</div>
	</div>
</header>

<div class="container">
	<div class="row">
		<!--产品块-->
		<div class="tb_box" style="margin-bottom:0px;">
			<h3 class="tab_tit" style="font-size:16px;">
				<a class="more" href="${contextPath }/main/category.html?type=${curSelectedBpp.type.value}&catId=${curSelectedBpp.id}">更多</a>
				<c:out value="${curSelectedBpp.name }"/>
			</h3>
			<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
			<c:if test="${vs.count%2 == 1}">
			<div class="tb_type clearfix" style="position: relative;padding-bottom: 20px;">
			</c:if>
				<a class="${vs.count%2 == 1?'tb_floor':'th_link'} abox" href="${contextPath }/main/view.html?type=${curSelectedBpp.type.value }&id=${row.id}" >
					<img class="tb_pic" src="${contextPath }/file/download.html?id=${row.maxPic}">
					&nbsp;
					<span style="position: absolute;bottom: 0;">
					<input type="checkbox" class="ids" id="${row.id }" catType="${curSelectedBpp.type.value == 'PEARL_CATEGORY'?'':row.category}" rel="${row.id }" />
                    <label class="checkLabel" for="${row.id }">
                    	<c:out value="${row.categoryShow }"/>
                    	<c:out value="${row.code }"/>
                    	￥<c:out value="${row.marketPrice }"/>
                    </label>
                    </span>
				</a>
			<c:if test="${vs.count%2 == 0 || vs.count == pageData.total}">
			</div>
			</c:if>
			</c:forEach>
		</div>
	</div>
</div>
<footer class="footer" style="margin-top:2px;">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="${contextPath }/main/index.html" class="active">
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
				jQuery.setCookie(mainCategory+type,id+"-1");	
			} else {
				jQuery.setCookie(mainCategory+type,id+"-0");
			}
		});
	});
</script>
</body>
</html>