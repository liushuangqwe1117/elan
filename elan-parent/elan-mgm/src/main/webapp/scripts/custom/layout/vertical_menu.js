/**
 * 用法：
 * $(document).ready(function(){
 * 		initVerticalMenu( "my_nav" );
 * })
 */
/**
 * 初始化菜单
 */
function initVerticalMenu( menu_id ){
	addLevelClass( menu_id );
	
	// 展开，折叠
	var ul_elements = $( '#'+menu_id + ' ul');
	$.each( ul_elements , function(index,ul_menu ){
		$(ul_menu).parent().children("a").bind("click",menuExpandOrCollapse );
	});
	// 初始化选中菜单
	var location = window.location.href;
	var flag = false;
	var key = $.cookie("JSESSIONID");
	$("#"+menu_id+" a").each(function(i){
		if(location.indexOf($(this).attr("href"))>-1){
			$(this).parents("ul.ullevel1").parent().children("a").click();
			$(this).addClass("current");
			locToMiddle($(this));
			// 同时将菜单信息写入cookie
			$.cookie(key,$(this).attr("rel"), {path:'/'});
			flag = true;
			return false;				
		}
	});
	//显示菜单
	$("#vertical_navigation").css("visibility","visible");
	
	if(!flag){
		// 从cookie中读取当前菜单信息
		if(location.indexOf("/home") == -1){
			var v = $.cookie(key);
			if(v != null && v != undefined && v != ''){
				var obj = $("a[rel='"+v+"']","#"+menu_id);
				if(obj != null){
					obj.parents("ul.ullevel1").parent().children("a").click();
					obj.addClass("current");
					locToMiddle(obj);
				}
			}
		}
	}
	//
	$(window).load(function(){
		if(_mp_menu_scroll_top > 0){
			$(".ui-layout-west").scrollTop(_mp_menu_scroll_top);
		}
	});
}

/**
 * 将被选中的菜单居中显示
 */
var _mp_menu_scroll_top = 0 ;
function locToMiddle(obj){
	// 获取文档可见区域高度
	if(obj && document.documentElement && document.documentElement.clientHeight){
		var documentH = document.documentElement.clientHeight;
		var southH = $(".ui-layout-south").height();
		var northH = $(".ui-layout-north").height();
		var offset = obj.offset();
		var top = 0 ;
		if(offset){
			top = obj.offset().top;
		}
		if((top+southH+northH) > documentH){
			_mp_menu_scroll_top = northH+top+southH-documentH;
		}
	}
}

/**
 * 样式添加,最多支持三级样式
 * 
 * @return
 */
function addLevelClass( menu_id ){
	addOneLevelClass( $('#'+menu_id + ' > li > a'),1);// 一级菜单
	addOneLevelClass( $('#'+menu_id + ' > li > ul > li > a'),2);// 二级菜单
	addOneLevelClass( $('#'+menu_id + ' > li > ul > li > ul > li > a'),3);// 三级菜单
}

/**
 * 添加一级样式
 */
function addOneLevelClass( elements,level){
	$.each( elements , function(index,a_menu ){
		var no_child = ($(a_menu).parent().has("ul").length ==0);
		$(a_menu).html("<span>&nbsp;</span>" + $(a_menu).html() );
		
		$(a_menu).addClass("level"+level);
		$(a_menu).parent().children("ul").addClass("ullevel"+level);
		
		// 默认折叠一级菜单菜单
		if( level == 1){
			$(a_menu).addClass("collapse");
			$(a_menu).parent().children("ul").css("display","none");
		}else if(level==2 && !no_child){
			$(a_menu).parent().children("ul").css("display","");
			$(a_menu).addClass("expand");
		}else if(level ==2 && no_child){
			$(a_menu).addClass("menuleaf");
		}
	});
}

/**
 * 菜单的展开与折叠
 */
function menuExpandOrCollapse(){
	var ul_menu = $(this).parent().children("ul");
	// 关闭所有菜单
	if($(this).hasClass("level1")){
		var show = $(ul_menu).css("display");
		// 当前点击的是一级菜单,则收起所有一级菜单
		var openedL1 = $("ul").siblings(".level1.expand");
		$(openedL1).removeClass("expand").addClass("collapse");
		$(openedL1).parent().children("ul").css("display","none");
		// 设置自己最初的显示状态
		$(ul_menu).css("display",show);
	}
	
	// 隐藏或显示子菜单
	var isExpanded = $(ul_menu).css("display") != "none";
	$(ul_menu).css("display",isExpanded? "none":"");
	
	// 当前菜单样式
	$(this).removeClass("expand").removeClass("collapse");
	var style = isExpanded?"collapse":"expand";
	$(this).addClass( style );
}