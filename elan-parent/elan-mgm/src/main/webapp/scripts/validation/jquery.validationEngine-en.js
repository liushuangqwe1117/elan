
(function($) {
	$.fn.validationEngineLanguage = function() {
	};
	$.validationEngineLanguage = {
		newLang : function() {
			$.validationEngineLanguage.allRules = {
				"required" : { // Add your regex rules here, you can take
								// telephone as an example
					"regex" : "none",
					"alertText" : "不能为空",
					"alertTextCheckboxMultiple" : "请选择一个选项",
					"alertTextCheckboxe" : "* This checkbox is required"
				},
				"exemptString" : {
					"alertText" : "不允许输入该值"
				},
				"minSize" : {
					"regex" : "none",
					"alertText" : "* Minimum ",
					"alertText2" : " characters allowed"
				},
				"maxSize" : {
					"regex" : "none",
					"alertText" : "最大允许输入 ",
					"alertText2" : " 个字符"
				},
				"min" : {
					"regex" : "none",
					"alertText" : "* 最小值为 "
				},
				"max" : {
					"regex" : "none",
					"alertText" : "* Maximum value is "
				},
				"length" : {
					"regex" : "none",
					"alertText" : "*Between ",
					"alertText2" : " and ",
					"alertText3" : " characters allowed"
				},
				"maxCheckbox" : {
					"regex" : "none",
					"alertText" : "* Checks allowed Exceeded"
				},
				"minCheckbox" : {
					"regex" : "none",
					"alertText" : "  最少选择 ",
					"alertText2" : " 个项目"
				},
				"confirm" : {
					"regex" : "none",
					"alertText" : "* Your field is not matching"
				},
				"mobile" : {
					"regex" : /^(1(([34578][0-9])|(47)|[8][0126789]))\\d{8}$/,
					"alertText" : "手机号码输入不正确"
				},
				"telephone" : {
					"regex" : /^[0-9\-\(\)\ ]+$/,
					"alertText" : "电话号码输入不正确"
				},
				"postcode" : {
					"regex" : /^([0-9]{6})?$/,
					"alertText" : "邮政编码输入不正确"
				},
				"email" : {
					"regex" : /^([a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4})?$/,
					"alertText" : "邮箱地址输入不正确"
				},
				"date" : {
					"regex" : /^[0-9]{4}\-[0-9]{1,2}\-[0-9]{1,2}$/,
					"alertText" : "日期格式必须为YYYY-MM-DD."
				},
				"number" : {
					// Number, including positive, negative, and floating
					// decimal. credit: orefalo
					"regex" : /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
					"alertText" : "请输入正确的数字"
				},
				"integer" : {
					// Number, including positive, negative, and floating
					// decimal. credit: orefalo
					"regex" : /^[\-\+]?([1-9]\d*|0)$/,
					"alertText" : "请输入正确的整数"
				},
				"second" : {
					// Number, including positive, negative, and floating
					// decimal. credit: orefalo
					"regex" : /^[1-9]\d*$|0/,
					"alertText" : "请输入正确的秒数"
				},
				"latitude" : {
					// 纬度
					"regex" : /^(([NS]((\d|[1-8]\d)°(\d|[0-5]\d)′(\d|[0-5]\d)(\.\d{1,2})?″$))|((\d|[1-8]\d)(([\.,]([0-9]+))?)))/,
					"alertText" : "请输入正确的纬度，如N50°33′22″或者数字"
				},
				"longitude" : {
					// 经度
					"regex" : /^((([EW](\d|[1-9]\d|1[0-7]\d)°(\d|[0-5]\d)′(\d|[0-5]\d)((\.\d{1,2})?)″))|((\d|[1-9]\d|1[0-7]\d)(([\.,]([0-9]+))?)))$/,
					"alertText" : "请输入正确的经度，如E150°33′22″ 或者数字 "
				},
				"onlyNumber" : {
					"regex" : /^(0|([1-9]\d*))$/,
					"alertText" : "请输入正整数"
				},
				"userName" : {
					"regex" : /^[a-zA-Z0-9][a-zA-Z0-9_]{2,15}$/,
					"alertText" : "必须是数字，字母，下划线的组合"
				},
				"code" : {
					"regex" : /^[a-zA-Z0-9_]*$/,
					"alertText" : "必须是数字，字母，下划线的组合"
				},
				"ajaxUser" : {
					"file" : "validateUser.php",
					"extraData" : "name=eric",
					"alertTextOk" : "* This user is available",
					"alertTextLoad" : "* Loading, please wait",
					"alertText" : "* This user is already taken"
				},
				"ajaxName" : {
					"file" : "validateUser.php",
					"alertText" : "* This name is already taken",
					"alertTextOk" : "* This name is available",
					"alertTextLoad" : "* Loading, please wait"
				},
				"onlyLetter" : {
					"regex" : /^[a-zA-Z]+$/,
					"alertText" : "* Letters only"
				},
				"onlyNumberOrLetter" : {
					"regex" : /^[a-zA-Z0-9]+$/,
					"alertText" : "只允许字母和数字"
				},
				"validate2fields" : {
					"nname" : "validate2fields",
					"alertText" : "* You must have a firstname and a lastname"
				}
			};

		}
	};
	$(document).ready(function() {
		$.validationEngineLanguage.newLang();
	});
})(jQuery);