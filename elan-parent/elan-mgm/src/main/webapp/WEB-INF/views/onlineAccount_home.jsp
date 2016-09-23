<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我的首页</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script type="text/javascript">
	//加载页面
	function loadPage(){
		var action = $("#homeForm").attr("action")+"?t=" + new Date().getTime();
		var params = $("#homeForm").serialize();
		$("#homeForm").submit();
	}
	$(function() {
		//日志类型
		$("#system,#nlevel,#type").change(function() {
			loadPage();
		});
		$.post("${contextPath }/home/online/clsReviewCount.html","",function(value){
			//备付金挑拨审核笔数
			if(value.reserveCount > 0){
				$("#coverAuditCountID").text(value.reserveCount);
			}else{
				$("#frontcoverAuditCountID").removeAttr("class");
				$("#coverAuditCountID").removeAttr("class");
			}
			
			//账户挑拨审核笔数
			if(value.transferCount > 0){
				$("#accountAuditCountID").text(value.transferCount);
			}else{
				$("#frontaccountAuditCountID").removeAttr("class");
				$("#accountAuditCountID").removeAttr("class");
			}
			
			//商户结算审核笔数
			if(value.merSettCount > 0){
				$("#merAuditCountID").text(value.merSettCount);
			}else{
				$("#frontmerAuditCountID").removeAttr("class");
				$("#merAuditCountID").removeAttr("class");
			}
			
			//内部对账不平帐笔数
			if(value.innerCheckCount > 0){
				$("#inCheckCountID").text(value.innerCheckCount);
			}else{
				$("#frontinCheckCountID").removeAttr("class");
				$("#inCheckCountID").removeAttr("class");
			}
			
			//外部对账不平帐笔数
			if(value.channelCheckCount > 0){
				$("#outCheckCountID").text(value.channelCheckCount);
			}else{
				$("#frontoutCheckCountID").removeAttr("class");
				$("#outCheckCountID").removeAttr("class");
			}
			
		},"json");
		/* //备付金挑拨审核笔数
		$.post("${contextPath }/home/online/coverAuditCount.html","",function(value){
			$("#coverAuditCountID").text(value);
		},"json");
		//账户挑拨审核笔数
		$.post("${contextPath }/home/online/accountAuditCount.html","",function(value){
			$("#accountAuditCountID").text(value);
		},"json");
		//商户结算审核笔数
		$.post("${contextPath }/home/online/merAuditCount.html","",function(value){
			$("#merAuditCountID").text(value);
		},"json");
		//内部对账不平帐笔数
		$.post("${contextPath }/home/online/inCheckCount.html","",function(value){
			$("#inCheckCountID").text(value);
		},"json");
		//外部对账不平帐笔数
		$.post("${contextPath }/home/online/outCheckCount.html","",function(value){
			$("#outCheckCountID").text(value);
		},"json"); */
		//自动刷新
		var intervalTime = 60;
		function refresh() {
			intervalTime = intervalTime-1;
			$("#refreshTime").val(intervalTime+"秒");
			if(intervalTime == 0){
				intervalTime = 60;
				//加载页面
				loadPage();
			}
		}
		var interval = setInterval(function(){refresh();},1000);
		$("#autoRefresh").change(function() {
			if ($(this).attr("checked") == "checked") {
				interval = setInterval(function(){refresh();},1000);
			} else {
				clearInterval(interval);
			}
		});
		//待办事件
		$(".open_coverAuditCountID_TABID").click(function(){
			//子页面调用父类后跳转新窗口
			window.parent.window.openNewWindow("open_coverAuditCountID_TABID", "备付金调拨审核","${contextPath }/clear/callAcct/audit/list.html?t=" + new Date().getTime());
		});
		$(".open_accountAuditCountID_TABID").click(function(){
			window.parent.window.openNewWindow("open_accountAuditCountID_TABID", "账户调拨审核","${contextPath  }/pay/accountAdjust/audit/list.html?t=" + new Date().getTime());
		});
		$(".open_merAuditCountID_TABID").click(function(){
			window.parent.window.openNewWindow("open_merAuditCountID_TABID", "商户结算审核","${contextPath }/clear/mersettle/redo.html?t=" + new Date().getTime());
		});
		$(".open_inCheckCountID_TABID").click(function(){
			window.parent.window.openNewWindow("open_inCheckCountID_TABID", "内部对账不平帐","${contextPath }/clear/checkinner/review_list.html?t=" + new Date().getTime());
		});
		$(".open_outCheckCountID_TABID").click(function(){
			window.parent.window.openNewWindow("open_outCheckCountID_TABID", "外部对账不平帐","${contextPath }/clear/checkchannel/checklist.html?t=" + new Date().getTime());
		});
		$(".open_gotomoninglog_TABID").click(function(){
			window.parent.window.openNewWindow("open_gotomoninglog_TABID", "日志监控","${contextPath }/bis/exceptionlog/list.html?t=" + new Date().getTime());
		});
	});
	
</script>
</head>
	<body class="p-iframe">
		<div class="m-todo">
			<h2>【待办事件】</h2>
			<div class="prev"></div>
			<div class="list">
				<ul>
					<li><a href="javascript:void(0);" class="open_coverAuditCountID_TABID" ><span class="bf"><i class="rl" id="frontcoverAuditCountID"><i class="rr" id="coverAuditCountID"></i></i></span><em>备付金调拨审核</em></a></li>
					<li><a href="javascript:void(0);" class="open_accountAuditCountID_TABID" ><span class="nb"><i class="rl"  id="frontcoverAuditCountID"><i class="rr" id="accountAuditCountID"></i></i></span><em>账户调拨审核</em></a></li>
					<li><a href="javascript:void(0);" class="open_merAuditCountID_TABID" ><span class="sh"><i class="rl" id="frontcoverAuditCountID"><i class="rr" id="merAuditCountID"></i></i></span><em>商户结算审核</em></a></li>
					<li><a href="javascript:void(0);" class="open_inCheckCountID_TABID" ><span class="sh"><i class="rl" id="frontcoverAuditCountID"><i class="rr" id="inCheckCountID"></i></i></span><em>内部对账不平帐</em></a></li>
					<li><a href="javascript:void(0);" class="open_outCheckCountID_TABID" ><span class="wb"><i class="rl" id="frontcoverAuditCountID"><i class="rr" id="outCheckCountID"></i></i></span><em>外部对账不平帐</em></a></li>
				</ul>
			</div>
			<div class="next"></div>
			<div class="f-cb"></div>
		</div>
		<div class="m-table" >
			<h2>【监控日志】</h2>
			<form id="homeForm" action="${contextPath }/home/online/main.html" method="post">
				<div class="ipt">
					<label>子系统：</label>
					<select id="system" name="system" style="color: #040404;">
						<option></option>
						<c:forEach items="${bisSmsSystemList }" var="en">
							<option value="${en }" <c:if test="${en == param.system }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
						</c:forEach>
					</select>
				</div>
				<div class="ipt">
					<label>日志等级：</label>
					<select id="nlevel" name="nlevel" style="color: #040404;">
						<option></option>
						<c:forEach items="${bisExceptionLogNlevelList }" var="en">
							<option value="${en }" <c:if test="${en == param.nlevel }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
						</c:forEach>
					</select>
				</div>
				<div class="ipt">
					<label>日志类型：</label>
					<select id="type" name="type"  style="color: #040404;">
					<option></option>
					<c:forEach items="${bisExceptionLogTypeList }" var="en">
						<option value="${en }" <c:if test="${en == param.type }">selected="selected"</c:if>><c:out value="${en.displayName }" /></option>	
					</c:forEach>
				</select>
				</div>
				<div class="ipt">
				&nbsp;
				</div>
				<div class="ipt" style="padding-top:-35px;">
					<input type="button" id="refreshTime" class="action" value="60秒" style="margin-top:3px;position:relative;bottom:2px;
					background: #fef8f9;border: 1px solid #f8e0de;display: inline-block;text-align: center;line-height: 20px;width: 43px;" />
				</div>
				<div class="ipt">
					<input type="checkbox" id="autoRefresh" checked="checked" /><label for="autoRefresh">自动刷新</label> 
				</div>	
				<div class="ipt" >
				<a  href="javascript:void(0);" class="open_gotomoninglog_TABID">查看更多>></a>
				</div>
			</form>
			<table>
				<thead>
					<tr>
						<th>序号</th>
						<th>记录时间</th>
						<th>子系统</th>
						<th>日志等级</th>
						<th>日志类型</th>
						<th>日志全路径</th>
						<th>错误内容</th>
					</tr>
				</thead>
				<tbody >
				<c:forEach items="${pageData.rows }" var="row" varStatus="vs">
									<tr class="${vs.count%2 == 0?'gray':''}">
										<td><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}"/></td>
										<td><fmt:formatDate value="${row.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><c:out value="${row.system.displayName }"/></td>						
										<td><c:out value="${row.nlevel.displayName }"/></td>
										<td><c:out value="${row.type.displayName }"/></td>
										<td><c:out value="${row.allpath }"/></td>
										<td class="red"><c:out value="${cfn:substrby15(row.content) }"/></td>
									</tr>
				</c:forEach>
				<c:if test="${empty pageData.rows}">
					<tr><td colspan="7" class="emptyRecord" align="center">今天没有日志记录</td></tr>
				</c:if>
								
				</tbody>
			</table>
				<!-- 分页控件引入 -->
				<%@include file="/WEB-INF/views/includes/pagination.jsp" %>
		</div>

		<script>
			var l = $(".m-table tr").length;
			$(".m-pages .num").click(function() {
				$(this).children(".select").toggle();
				$(this).toggleClass("index");
			});
			for (i = 0; i < l; i++) {
				$(".m-table .set").eq(i).css("z-index", l - i);
			}
			var cds = $(".m-todo .list ul").children("li");
			var ulw = cds.length * 190;
			var ml2 = ulw - 950;
			var ml3 = ulw - 190;
			$(".m-todo .list ul").width(ulw);
			var ml = 0;
			//图片缩略图向右
			$(".m-todo .prev").click(function() {
				if (Math.abs(ml) < ml3) {
					ml -= 190;
					$(".m-todo .list ul").animate({
						marginLeft: ml + "px"
					}, 200, "swing");
				} else {
					$(".m-todo .list ul").animate({
						marginLeft: 0 + "px"
					}, 1000, "swing");
					ml = 0;
				}
			});
			//图片缩略图向左
			$(".m-todo .next").click(function() {
				if (ml >= 760) {
					$(".m-todo .list ul").animate({
						marginLeft: -ml2 + "px"
					}, 1000, "swing");
					ml = -ml2;
				} else {
					ml += 190;
					$(".m-todo .list ul").animate({
						marginLeft: ml + "px"
					}, 200, "swing");
				}
			});
		</script>
	</body>
</html>