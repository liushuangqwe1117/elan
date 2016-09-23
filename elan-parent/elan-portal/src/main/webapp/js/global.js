jQuery.extend({
  getCookie : function(sName) {
    var aCookie = document.cookie.split("; ");
    for (var i=0; i < aCookie.length; i++){
      var aCrumb = aCookie[i].split("=");
      if (sName == aCrumb[0]) return decodeURIComponent(aCrumb[1]);
    }
    return '';
  },
  setCookie : function(sName, sValue, sExpires) {
    var sCookie = sName + "=" + encodeURIComponent(sValue);    
    if (sExpires != null) { 
    	var f = new Date; f.setTime(f.getTime() + 1e3 * sExpires); sExpires = ";expires=" + f.toGMTString();sCookie += "; expires=" + sExpires;    
    }
    document.cookie = sCookie+ ";path=/;";
  },
  removeCookie : function(sName) {
    document.cookie = sName + "=; expires=Fri, 31 Dec 1999 23:59:59 GMT;";
  }
});

$(function(){
	//返回顶部
	$(window).scroll(function(){
		if($(".fanhui_cou")) {
			if($(this).scrollTop()>300){
				$(".fanhui_cou").fadeIn(1500);
			}else{
				$(".fanhui_cou").fadeOut(1500);
			}
		}
	});
	if($(".fanhui_cou")) {
		$(".fanhui_cou").click(function(){
			$("body,html").animate({scrollTop:0},200);
			return false;
		});
	}
	
});

