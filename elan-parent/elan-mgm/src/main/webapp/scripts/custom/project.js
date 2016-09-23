$(function(){
	//不使用缓存
	$.ajaxSetup({cache:false,
		error:function (XMLHttpRequest, textStatus, errorThrown) {
		    //alert("global ajax error!");
		},
	    complete : function (XMLHttpRequest, textStatus) {
	    	if(XMLHttpRequest.statusText.toUpperCase() == "ERROR"){
	    		//服务关闭后
	    		//window.location.href=GlobalContextPath+"/index.jsp";
	    	}else{
	    		try {
	    			var response = eval("("+XMLHttpRequest.responseText+")");
			    	if(response && response.sessiontimeout === true){
			    		//Session超时，跳转到登录页面
			    		//window.location.href=GlobalContextPath+"/error/timeout.jsp";
			    	}
				} catch (e) {
				}
	    	}
	    	//销毁对象，防止占用内存
	    	XMLHttpRequest = null;
	    }
	});
	
	//当列表没有操作的时候，则修改为<td></td>
	$("td > div.set",".m-table").each(function(){
		if($(this).find("a").length == 0){
			$(this).remove();
		}
	});
	
	//隐藏列表操作列表
	$(".m-table div.set").mouseout(function(){
		$(".m-table div.set").find("span").each(function(){
			$(this).hide();
		});
	});
	
	//显示列表操作列表
	$(".m-table div.set").mouseover(function(event){
		var evt = event;
		if (evt == null)
			evt = window.event;
		//隐藏列表操作列表
		$(".m-table div.set").find("span").each(function(){
			$(this).hide();
		});
		//显示当前
		var popupMenu = $(this).find("span");
		
		var td_element = $(popupMenu).parent().parent();
		var tr_element = $(td_element).parent();
		var tr_width = $(tr_element).width();
		var tr_top = tr_element.offset().top;
		var td_width = $(td_element).outerWidth();
		var td_height = $(td_element).outerHeight();

		var top = 20;
		if (((tr_top + $(popupMenu).height() - (td_height / 2) + 30) > $(document.body).outerHeight())
				&& (tr_top > $(popupMenu).height())) {
			top = (td_height / 2) - $(popupMenu).height();
		}
		$(popupMenu).css("top", top + "px");
		$(popupMenu).show();
	});
});

//处理键盘事件
function doKey(e){
	var ev = e || window.event;//获取event对象
	var obj = ev.target || ev.srcElement;//获取事件源
	var t = obj.type || obj.getAttribute('type');//获取事件源类型
	var ro = obj.getAttribute("readonly");
	if(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"){
		return false;
	}else if(ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && ro == "readonly"){
		return false;
	}
}
//禁止后退键 作用于Firefox、Opera
//document.onkeypress=doKey;
//禁止后退键  作用于IE、Chrome
document.onkeydown=doKey;

//不能直接访问链接地址
var mainUri=window.globalApplication + "/main";
var smsTemplateUserUrl="bis/smsTemplate/ucsSec/list";
var emailTemplateUserUrl="bis/emailTemplate/ucsSec/list";
var merchants="bis/serviceAccessParty/merchants";

if(top.location.pathname.indexOf(mainUri) == -1 && 
		top.location.pathname.indexOf(smsTemplateUserUrl) == -1 &&
		top.location.pathname.indexOf(emailTemplateUserUrl) == -1 &&
		top.location.pathname.indexOf(merchants) == -1){
	alert("请通过合法路径访问！");
	top.location.href="http://" + location.host + mainUri+"。html";
}
