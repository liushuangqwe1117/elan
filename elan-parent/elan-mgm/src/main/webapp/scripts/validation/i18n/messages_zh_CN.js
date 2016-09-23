/*
 * Translated default messages for the jQuery validation plugin.
 * Language: CN
 * Author: Fayland Lam <fayland at gmail dot com>
 */
jQuery.extend( jQuery.validator.messages, {
	required: "必填字段",
	remote: "请修正该字段",
	mobile : "手机号码输入不正确",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format( "请输入一个长度最多是 {0} 的值" ),
	minlength: jQuery.validator.format( "请输入一个长度最少是 {0} 的值" ),
	rangelength: jQuery.validator.format( "请输入一个长度介于 {0} 和 {1} 之间的值" ),
	range: jQuery.validator.format( "请输入一个介于 {0} 和 {1} 之间的值" ),
	max: jQuery.validator.format( "请输入一个最大为 {0} 的值" ),
	min: jQuery.validator.format( "请输入一个最小为 {0} 的值" )
} );

/**
 * 大于.
 * 
 * @author 潘瑞峥
 * @date 2013-01-23
 */
$.validator.addMethod( 'gt', function( value, element, param ) {
	return this.optional( element ) || value > param;
}, $.validator.format( '请输入一个大于 {0} 的值' ) );

/**
 * 小于.
 * 
 * @author 潘瑞峥
 * @date 2013-01-23
 */
$.validator.addMethod( 'lt', function( value, element, param ) {
	return this.optional( element ) || value < param;
}, $.validator.format( '请输入一个小于 {0} 的值' ) );



