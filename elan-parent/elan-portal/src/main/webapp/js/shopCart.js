$(function(){
	//勾选
	$(".checkOne").click(function(){
		if($(".checkOne:checked").length  == $(".checkOne").length) {
			$(".checkAll").attr("checked",true)
		} else {
			$(".checkAll").attr("checked",false)
		}
		//计算总价
		calculateTotal();
	});
	
	$(".checkAll").click(function(){
		$(".checkOne").attr("checked",!!$(this).attr("checked"));
		//计算总价
		calculateTotal();
	});
	//计算总价
	calculateTotal();
});

//计算总价
function calculateTotal(){
	var allCash = 0;
	$(".checkOne:checked").each(function(){
		allCash += Number($(this).attr("price"));
	});
	$("#totalPrice").html(allCash);
}

//删除购物车商品
function deleteShopCart(id,mainCategory,type){
	$("div."+id).remove();
	jQuery.setCookie(mainCategory+type,id+"-0");
	calculateTotal();
}
