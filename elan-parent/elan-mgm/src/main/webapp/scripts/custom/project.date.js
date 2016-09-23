/**
 * 格式化时间日期，格式为yyyy-MM-dd HH:mm:ss
 */
Date.prototype.formatToTime = function() {
	var dt = this;
	var year = dt.getFullYear();
	var month = (dt.getMonth() + 1)+"";
	    month = month.length == 1 ? "0" + month : month;
	var day = dt.getDate()+"";
	    day = day.length == 1 ? "0" + day : day;
	var hour = dt.getHours()+"";
		hour = hour.length == 1 ? "0" + hour : hour;
	var minute = dt.getMinutes()+"";
	    minute = minute.length == 1 ? "0" + minute : minute;
	var second = dt.getSeconds()+"";
	    second = second.length == 1 ? "0" + second : second; 
	return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}

/**
 * 格式化日期，格式为yyyy-MM-dd
 */
Date.prototype.formatToDate = function() {
	var dt = this;
	var year = dt.getFullYear();
	var month = (dt.getMonth() + 1)+"";
	    month = month.length == 1 ? "0" + month : month;
	var day = dt.getDate()+"";
	    day = day.length == 1 ? "0" + day : day;
	return year + "-" + month + "-" + day;
}
