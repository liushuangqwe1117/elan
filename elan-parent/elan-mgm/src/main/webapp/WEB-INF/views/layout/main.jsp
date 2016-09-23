<!DOCTYPE html>
<%@ page language ="java" pageEncoding = "UTF-8" contentType="text/html;charset=UTF-8" %> 
<%@ include file="../includes/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Elan管理平台</title> 
	<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/images/favicon.ico" media="screen" />
    <link rel="stylesheet" type="text/css" href="${contextPath }/styles/project/red/style.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath }/scripts/ligerUI/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath }/styles/project/red/custom.ligerui.css" />
	<style type="text/css">
		body,html{height:100%;}
   		body{ padding:0px; margin:0;   overflow:hidden;}	
	</style>
	<script type="text/javascript" src="${contextPath }/scripts/jquery/jquery-1.11.3.min.js"></script> 
	<script type="text/javascript" src="${contextPath }/scripts/jquery/jquery-ui-1.11.4.min.js"></script>
	<script type="text/javascript" src="${contextPath }/scripts/jquery/jquery.layout-1.2.0.min.js"></script>
	<script type="text/javascript" src="${contextPath }/scripts/jquery/jquery.fix.js"></script>
    <script type="text/javascript" src="${contextPath }/scripts/ligerUI/js/ligerui.min.js"></script>
	
	<script type="text/javascript">
	var menuTab = null;
	$(function () {
		//初始化顶级菜单样式
		if ($(".m-nav ul li").length == 1){
			$(".m-nav ul li").addClass("one");
			clearOuterClick($(".m-nav ul li"));
		}
		if ($(".m-nav ul li").length == 2){
			$(".m-nav ul li:eq(0)").addClass("last");
			$(".m-nav ul li:eq(1)").addClass("first");
			clearOuterClick($(".m-nav ul li:eq(1)"));
		}
		if ($(".m-nav ul li").length == 3){
			$(".m-nav ul li:eq(0)").addClass("last");
			$(".m-nav ul li:eq(2)").addClass("first");
			clearOuterClick($(".m-nav ul li:eq(2)"));
		}
		function clearOuterClick(jObj){
			if(!(jObj.attr("outer") == "1" || jObj.attr("outer") == 1)) {
				jObj.addClass("active");	
			}
		}
		//top栏导航菜单
		$(".m-nav span").mouseover(function() {
			$(this).next().show();
		});
		$(".m-nav .dropdown").mouseleave(function() {
			$(this).hide();
		});
		$(".m-nav li").click(function() {
			$(this).siblings().removeClass("activeprev");
			$(this).siblings().removeClass("active");
			$(this).addClass("active").removeClass("activeprev");
			$(this).next().addClass("activeprev");
			if($(this).attr("ref")) {
				//关闭其他系统菜单
				$("ul.m-menu").css("display","none");
				$("ul." + $(this).attr("ref")).css("display","");
			}
		});
		//初始菜单
		if($(".m-nav li.active") && $(".m-nav li.active").attr("ref")) {
			$("ul." + $(".m-nav li.active").attr("ref")).css("display","");
		}
		
		//布局
        $("#mainLayout").ligerLayout({
            leftWidth : 210,
            height: '100%',
            heightDiff: -1,
            allowLeftResize : false,
            space: 0,
            onHeightChanged: f_heightChanged,
            onLeftToggle: l_toggle
        });
        function f_heightChanged(options) {
            if (menuTab)
            	menuTab.addHeight(options.diff);
            $("#accordion1").each(function () {
                $(this).height($(this).parent().height() - $(this).prev().height());
            });
        }
        
      	//隐藏显示菜单栏
        function l_toggle(){
        	$(window).resize();
        }
        //window窗口变化改变菜单高度
        $(window).bind("resize",function(){
        	 $("#accordion1").each(function () {
                 $(this).height($(this).parent().height() - $(this).prev().height());
             });
        });
		
		var height = $(".l-layout-center").height();//此.l-layout-center为ligerui自动添加的DIV层
        //Tab
        $("#framecenter").ligerTab({ height: height});
		menuTab = $("#framecenter").ligerGetTabManager();
		
		//菜单初始化高度
	 	$("#accordion1").each(function () {
            $(this).height($(this).parent().height() - $(this).prev().height());
        });
		
		//菜单
		//左侧菜单点击显示隐藏
		//一级菜单显示与隐藏
		$("li.one > span",".m-menu").click(function(){
			if($(this).parent("li.one").hasClass("menuCls")) {
				return ;	
			}
			if($(this).siblings("ul") && $(this).siblings("ul").css("display") == "block"){
				//如果点击的当前菜单为打开状态，则关闭该菜单且置为未被选中状态
				$(this).siblings("ul").hide();
				$(this).parent("li.one").removeClass("active");
			} else {
				//关闭所有一级菜单，打开当前点击的一级菜单，同时添加选中样式
				$("li.one > ul",".m-menu").hide();
				$(this).siblings("ul").show();
				$(this).parent("li.one").addClass("active");
			}
		});

		//二级菜单显示与隐藏
		$("ul.two > li > a",".m-menu").click(function(){
			//默认二级菜单都为打开状态
			if($(this).siblings("ul") && $(this).siblings("ul").css("display") == "block") {
				//关闭菜单，同时箭头设置为关闭状态
				$(this).siblings("ul").hide();
			} else {
				$(this).siblings("ul").show();
			}
		});
		
		//点击菜单
		$("li.menucls").click(function(){
			var t = new Date().getTime();
			if(!$(this).hasClass("active")){
				$(".menucls.active").removeClass("active");
				$(this).addClass("active");
				//寻找二级菜单
				var liObj = $(this).parents("ul.two > li");
				if(liObj) {
					$("ul.two > li.active").removeClass("active");
					liObj.addClass("active");
				}
			};
			var tabid = $(this).attr("tabid");
			var text = $(this).children("a").text();
			var url = $(this).children("a").attr("rel") + "?t=" + t;
			if(!text) {
				text = $(this).find("span > a").text();
				url = $(this).find("span").children("a").attr("rel") + "?t=" + t;
			}
			f_addTab(tabid, text,url);
		});
		
		function f_addTab(tabid, text, url) {
			if(menuTab.isTabItemExist(tabid)) {
				menuTab.setTabItemSrc(tabid,url);
			}
			menuTab.addTabItem({ tabid: tabid, text: text, url: url});	
        }
		//首页
		$(".homeBtn").click(function(){
			f_addTab("home", "首页","${contextPath }/home.html?t=" + new Date().getTime());
		});
		//查看自己的信息
		$(".viewSelfBtn").click(function(){
			f_addTab("VIEW_SELF_TABID", "用户信息","${contextPath }/security/user/ncview.html?id=${login_account.id}&t=" + new Date().getTime());
		});
		//修改密码
		$(".changePwdBtn").click(function(){
			f_addTab("EDIT_PASSWORD_TABID", "修改密码","${contextPath }/security/user/changepwd.html?t=" + new Date().getTime());
		});
		
	}); 
	//子页面调用父类后跳转新窗口
	function openNewWindow(tabid, text, url) {
	   if(menuTab.isTabItemExist(tabid)) {
			menuTab.setTabItemSrc(tabid,url);
		}
		menuTab.addTabItem({ tabid: tabid, text: text, url: url});	
	  }
	</script>
</head>
<body>
<!----------------  logo部分 -------------->
<div class="g-head">
	<div class="m-logo-bak">
		<a href=""></a>
	</div>
	<div class="m-toolbar">
		<p>
			<a href="javascript:void(0);" class="viewSelfBtn"><i class="i-user"></i><shiro:principal/></a>
			<a href="javascript:void(0);" class="changePwdBtn"><i class="i-setpass"></i>修改密码</a>
			<a href="${contextPath }/logout.html"><i class="i-quit"></i>退出</a>
		</p>
	</div>
	<div class="m-nav">
		<c:if test="${fn:length(ttmenus) > 0 }">
		<ul>
			<c:forEach items="${ttmenus }" var="menu">
			<li class="" ref="<c:out value="${menu.id }" />">
				<a href="javascript:void(0)"><c:out value="${menu.name }" /></a>
			</li>
			</c:forEach>
		</ul>
		<c:if test="${fn:length(ddmenus) > 0 }">
		<span></span>
		<div class="dropdown">
			<c:forEach items="${ddmenus }" var="menu">
				<a ref="<c:out value="${menu.id }" />" href="javascript:void(0)"><c:out value="${menu.name }" /></a>
			</c:forEach>
		</div>
		</c:if>
		</c:if>
	</div>
</div>
<!----------------  logo部分 end -------------->

<div id="mainLayout" style="margin:0 auto;">
<!-- class="outer-layout-west" -->

<div position="left" title="功能菜单" id="accordion1" style="overflow: auto;background: #fef8f8;">
	<div class="g-body">
		<div class="g-side">
			<!-- 系统管理菜单 -->
			<ul class="m-menu">
				<shiro:hasPermission name="USER_LIST">
				<li class="one"><span><i class="i-menu2"></i><a href="javascript:void(0);">用户管理</a></span>
					<ul class="three"  style="display: none;">
						<li class="menucls" tabid="SECURITY_USER_LIST_TABID"><a href="javascript:void(0);" rel="${contextPath }/security/user/list.html">用户列表</a></li>
					</ul>
				</li>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="PRODUCT">
				<li class="one"><span><i class="i-menu3"></i><a href="javascript:void(0);">产品</a></span>
					<ul class="three"  style="display: none;">
						<shiro:hasPermission name="PEARL">
						<li class="menucls" tabid="PEARL_LIST_TABID"><a href="javascript:void(0);" rel="${contextPath }/pearl/list.html">珍珠</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="ACCESSORY">
						<li class="menucls" tabid="ACCESSORY_LIST_TABID"><a href="javascript:void(0);" rel="${contextPath }/accessory/list.html">配件</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="GOODS">
						<li class="menucls" tabid="GOODS_LIST_TABID"><a href="javascript:void(0);" rel="${contextPath }/goods/list.html">成品</a></li>
						</shiro:hasPermission>
					</ul>
				</li>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="BASE_INFO">
				<li class="one"><span><i class="i-menu3"></i><a href="javascript:void(0);">基础信息</a></span>
					<ul class="three"  style="display: none;">
						<li class="menucls" tabid="PRODUCT_PROPERTY_TABID"><a href="javascript:void(0);" rel="${contextPath }/productproperty/list.html">产品属性</a></li>
					</ul>
				</li>
				</shiro:hasPermission>
			</ul>
		</div>
	</div>
</div>

<!-- 主体内容部分 -->
<!-- class="outer-layout-center"  -->
<div position="center" id="framecenter">
	<div tabid="home" title="欢迎页">
	 	<iframe name="home" src="${contextPath }/home.html" style="overflow: auto;" width="0" height="0" frameborder="no" border="0"></iframe>
   	</div>
</div>
<!-- 主体内容部分 end -->
</div>

<!-- class="outer-layout-south"  -->
<div style="height: 1px;line-height: 1px;"></div>
</body>
</html>