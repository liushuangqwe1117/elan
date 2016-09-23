<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<head>
	<title>选择角色</title>
</head>
<body>
	<div class="m-modal">
		<div class="table" style="width: 350px;border-right: 1px solid #eda7a7;">
			<table>
				<thead>
					<tr>
						<th><input type="checkbox" class="checkRoleAll" /></th>
						<th>序号</th>
						<th>角色名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roles}" var="role" varStatus="vs">
					<tr>
						<td><input type="checkbox" class="checkRoleOne" value="${role.id }" rel="${role.name }" /></td>
						<td><c:out value="${vs.count}"/></td>
						<td><c:out value="${role.name }" /></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="m-ztree" style="border-top: 0px;border-right: 0px;padding: 0px;width: 338px;height: auto;min-height: 300px;">
			<h2><i class="i-quanxian"></i>权限集：</h2>
			<ul id="navigation_tree" style="margin-top:45px;">
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//全选
			$(".checkRoleAll").click(function(){
				$(".checkRoleOne").attr("checked",!!$(".checkRoleAll").attr("checked"));
				loadPerms(getRoleId());
			});
			//单选
			$(".checkRoleOne").click(function(){
				if($(".checkRoleOne:checked").length == $(".checkRoleOne").length){
					$(".checkRoleAll").attr("checked",true);		
				} else {
					$(".checkRoleAll").attr("checked",false);
				}
				loadPerms(getRoleId());
			});
			
			function getRoleId(){
				var selRoleId = "";
				var len = $(".checkRoleOne:checked").length;
				if(len > 0) {
					$(".checkRoleOne:checked").each(function(){
						selRoleId += "," + $(this).val();
					});
				}
				if(selRoleId && selRoleId.length > 1)selRoleId = selRoleId.substring(1);
				return selRoleId;
			}
			
			function loadPerms(selRoleId){
				if(selRoleId){
					//加载对应的权限
					//权限导航
					$("#navigation_tree").load("${contextPath }/security/role/load/selectPerms.html",
						"hasRoleoleId=" + selRoleId,
						function(){
							$("#navigation_tree").treeview({
								persist : "cookie",
								collapsed : false,
								cookieId : "treeview-navigation-permission"
							});
						}	
					);
				} else {
					$("#navigation_tree").html("");
				}
			}
			//选中曾经被选中的角色
			var hasRoleoleId = '${hasRoleoleId}';
			if(hasRoleoleId){
				var roleids = hasRoleoleId.split(",");
				for(var i=0 ;i < roleids.length;i++){
					$("input[type='checkbox'][value='"+roleids[i]+"']").attr("checked",true);
				}
				if($(".checkRoleOne:checked").length == $(".checkRoleOne").length){
					$(".checkRoleAll").attr("checked",true);		
				} else {
					$(".checkRoleAll").attr("checked",false);
				}
				loadPerms(hasRoleoleId);
			}
			
		});
	</script>
</body>