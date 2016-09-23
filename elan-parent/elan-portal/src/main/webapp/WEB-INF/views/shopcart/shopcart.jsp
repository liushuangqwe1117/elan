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
<link rel="stylesheet" href="${contextPath }/css/order3.css?v=01291"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
<title>购物车</title>
<style type="text/css">
.list-group{
	margin-bottom:0px;
}
.p-info .p-title {
	font-size:14px;
	height: 40px;
}
</style>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
<header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">购物车</div>
        </div>
    </div>
</header>
<div class="container" style="padding-bottom: 50px;">
	<c:if test="${pearObj != null }">
    <div class="row rowcar ${pearObj.id }">
        <ul class="list-group">
		    <li class="list-group-item text-primary">
		        <div class="icheck pull-left mr5">
				   <input type="checkbox" checked="checked" id="${pearObj.id }" class="checkOne ids" price="${pearObj.marketPrice }"/>
					   <label class="checkLabel" for="${pearObj.id }">
						<span></span>
					</label>
		        </div>
		        <c:out value="${pearObj.categoryShow }" />[<c:out value="${pearObj.code }" />]
			</li>
		    <li class="list-group-item hproduct clearfix">
				<div class="p-pic"><a href="${contextPath }/main/view.html?type=PEARL_CATEGORY&id=${pearObj.id}"><img class="img-responsive" src="${contextPath }/file/download.html?id=${pearObj.maxPic}"></a></div>
				<div class="p-info">
					<a href="javascript:void(0)">
						<p class="p-title">
							<c:out value="${pearObj.categoryShow }" />&nbsp;
							<c:out value="${pearObj.pointOneShow }" />-<c:out value="${pearObj.pointTwoShow }" />&nbsp;
							<c:out value="${pearObj.circleShow }" />&nbsp;
							<c:out value="${pearObj.qualityShow }" />&nbsp;
							<c:out value="${pearObj.luminosityShow }" />&nbsp;
							<c:out value="${pearObj.nlevelShow }" />&nbsp;
						</p>
					</a>
					<p class="p-origin">
						<a class="close p-close" onclick="deleteShopCart('${pearObj.id }','PEARL_CATEGORY','')" href="javascript:void(0);">×</a>
						<em class="price">¥<c:out value="${pearObj.marketPrice }" /></em>
					</p>
				</div>
		    </li>
		</ul>
	</div>
	</c:if>
	
	<c:forEach items="${accessoryList }" var="accessoryObj">
	<div class="row rowcar ${accessoryObj.id }">
        <ul class="list-group">
		    <li class="list-group-item text-primary">
		        <div class="icheck pull-left mr5">
				   <input type="checkbox" checked="checked" id="${accessoryObj.id }" class="checkOne ids" price="${accessoryObj.marketPrice }"/>
					   <label class="checkLabel" for="${accessoryObj.id }">
						<span></span>
					</label>
		        </div>
		        <c:out value="${accessoryObj.categoryShow }" />[<c:out value="${accessoryObj.code }" />]
			</li>
		    <li class="list-group-item hproduct clearfix">
				<div class="p-pic"><a href="${contextPath }/main/view.html?type=ACCESSORY_CATEGORY&id=${accessoryObj.id}"><img class="img-responsive" src="${contextPath }/file/download.html?id=${accessoryObj.maxPic}"></a></div>
				<div class="p-info">
					<a href="javascript:void(0)">
						<p class="p-title">
							<c:out value="${accessoryObj.categoryShow }" />&nbsp;
							<c:out value="${accessoryObj.styleShow }" />&nbsp;
							<c:out value="${accessoryObj.materialShow }" />&nbsp;
							<c:out value="${accessoryObj.nsizeShow }" />&nbsp;
							<c:out value="${accessoryObj.weightShow }" />&nbsp;
						</p>
					</a>
					<p class="p-origin">
						<a class="close p-close" onclick="deleteShopCart('${accessoryObj.id }','ACCESSORY_CATEGORY','${accessoryObj.category }')" href="javascript:void(0);">×</a>
						<em class="price">¥<c:out value="${accessoryObj.marketPrice }" /></em>
					</p>
				</div>
		    </li>
		</ul>
	</div>
	</c:forEach>
	
	<c:forEach items="${goodsList }" var="goodsObj">
	<div class="row rowcar ${goodsObj.id }">
        <ul class="list-group">
		    <li class="list-group-item text-primary">
		        <div class="icheck pull-left mr5">
				   <input type="checkbox" checked="checked" id="${goodsObj.id }" class="checkOne ids" price="${goodsObj.marketPrice }"/>
					   <label class="checkLabel" for="${goodsObj.id }">
						<span></span>
					</label>
		        </div>
		        <c:out value="${goodsObj.categoryShow }" />[<c:out value="${goodsObj.code }" />]
			</li>
		    <li class="list-group-item hproduct clearfix">
				<div class="p-pic"><a href="${contextPath }/main/view.html?type=GOODS_CATEGORY&id=${goodsObj.id}"><img class="img-responsive" src="${contextPath }/file/download.html?id=${goodsObj.maxPic}"></a></div>
				<div class="p-info">
					<a href="javascript:void(0)">
						<p class="p-title">
							<c:out value="${goodsObj.categoryShow }" />&nbsp;
							<c:out value="${goodsObj.styleShow }" />&nbsp;
						</p>
					</a>
					<p class="p-origin">
						<a class="close p-close" onclick="deleteShopCart('${goodsObj.id }','GOODS_CATEGORY','${goodsObj.category }')" href="javascript:void(0);">×</a>
						<em class="price">¥<c:out value="${goodsObj.marketPrice }" /></em>
					</p>
				</div>
		    </li>
		</ul>
	</div>
	</c:forEach>
</div>
<div class="fixed-foot">
<div class="fixed_inner">
    <div class="pay-point">
        <div class="icheck pull-left mr10">
            <input type="checkbox" checked="checked" class="checkAll" id="buy-sele-all" value="1">
            <label for="buy-sele-all">
                <span class="mt10"></span>全选
            </label>
        </div>
        <p>合计：<em class="red f22">¥<span id="totalPrice">179.00</span></em></p>
    </div>
</div>
</div>
<div class="clear"></div>
<script charset="utf-8" src="${contextPath }/js/shopCart.js?v=01291"></script>
</body>
</html>