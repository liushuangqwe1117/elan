<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑角色</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script src="${contextPath }/scripts/jquery/jquery.cookie.js" type="text/javascript"></script>
	<script src="${contextPath }/scripts/jquery/plugins/treeview/jquery.treeview.js" type="text/javascript"></script>
	<link href="${contextPath }/scripts/jquery/plugins/treeview/jquery.treeview.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		/* 特殊样式 */
		.m-form input[type='checkbox'] {
    		width: 13px;
    		height: 13px;
   			border: 1px solid #c1c1c1;
   			margin: 3px 2px;
		}
		.treeview {
			color: #d92419;
		}
		.treeview ul li {
			vertical-align:middle;
		    line-height: 18px;
		}
	</style>
	<script type="text/javascript">
	$(function() {
		//权限导航
		$("#navigation_tree").treeview({
			persist : "cookie",
			collapsed : false,
			cookieId : "treeview-navigation-permission"
		});
		//权限选择
		$("input[type='checkbox']","#navigation_tree").click(function(){
			var parent = $(this).attr('parent');	
			var identity = $(this).attr('id');
			if( parent != 'empty' )
				selectParent( parent );	
			selectChildren( identity, !!$(this).attr("checked"));		
		});
		//选择父结点
		function selectParent(id){
			var ele = $("input[id='"+id+"'][type='checkbox']");
		
			var children = $("input[parent='"+id+"'][type='checkbox']");
			var selected_len =0;
			$.each(children,function( index,c ){
				if( $(c).attr("checked") )
					selected_len++;
			});
			if( selected_len==0 )
				$("input[id='"+id+"'][type='checkbox']").attr("checked",false)
			
			if( selected_len > 0 )
				$(ele).attr("checked",true); //选中子节点大于0，父节点选中
			
			var parent = $("input[id='"+id+"'][type='checkbox']").attr("parent");
			if( parent != 'empty' ){
				 selectParent ( parent );
			}
		}
		//选择子节点
		function selectChildren( id , selectedOrNot ){
			var allResources = $("input[parent='"+id+"'][type='checkbox']");
				
			for( var i=0;i<allResources.length ;i++){
				allResources[i].checked = selectedOrNot; 	
				selectChildren( allResources[i].getAttribute('id') , selectedOrNot );
			}
		}
		//数据校验
		$('#profile_form').validate({
			rules : {
				'name' : {
					required : true,
					rangelength : [2,32]
				},
				'description' : {
					rangelength : [0,100]
				}
			}

		});
	});
	/**
	 * 保存
	 */
	function submitForm() {
		$("#profile_form").submit();
	}
	</script>
</head>
<body class="p-iframe">
	<div class="m-addjuese">
		<form:form id="profile_form" class="m-form" action="${contextPath }/security/role/save.html" commandName="obj" method="POST">
			<ofa:token/>
			<form:hidden path="id"/>
			<table>
				<tr>
					<td style="padding: 5px;"><label>*角色名称：</label></td>
					<td><form:input path="name" maxlength="32" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;"><label>角色类型：</label></td>
					<td><form:select path="roleType" items="${roleTypes}" itemValue="value" itemLabel="displayName" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;"><label>角色描述：</label></td>
					<td><form:textarea path="description" maxlength="80" cssStyle="width:200px;" rows="3"/></td>
				</tr>
				<tr>
					<td style="padding: 5px;"><label>权限集：</label></td>
					<td style="padding: 5px;">
						<ul id="navigation_tree">
							<li><c:set var="inRole" value="${fn:length(rolePerms) > 0 ? true:false }" /> 
								<input type="checkbox" id="topParentId"  parent="empty" <c:out value="${inRole?'checked':'' }"/>/>
								<span class="<c:out value="${inRole?'node_name':'none'}"/>">系统权限</span>
								<ul>
									<!-- 一级循环 -->
									<c:forEach items="${permList }" var="perm">
										<li><c:set var="inRole" value="${fn:contains(rolePerms,perm.id)?true:false}" /> 
											<input type="checkbox" value="${perm.id }" id="${perm.id }" name="permId" parent="topParentId" <c:if test="${inRole }">checked</c:if> /> 
											<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${perm.name}" /></span> 
											<!-- 二级循环 --> 
											<c:forEach items="${perm.children }" var="childPerm" varStatus="vs">
												<c:if test="${vs.first }"><ul></c:if>
												<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm.id)?true:false}" />
													<input type="checkbox" value="${childPerm.id }" id="${childPerm.id }" parent="${perm.id }" name="permId" <c:if test="${inRole }">checked</c:if> /> 
													<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm.name}" /></span> 
													<!-- 三级循环 --> 
													<c:forEach items="${childPerm.children }" var="childPerm2" varStatus="vs2">
														<c:if test="${vs2.first }"><ul></c:if>
														<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm2.id)?true:false}" />
															<input type="checkbox" value="${childPerm2.id }" id="${childPerm2.id }" parent="${childPerm.id }" name="permId" <c:if test="${inRole }">checked</c:if> /> 
															<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm2.name}" /></span> 
															<!-- 四级循环 --> 
															<c:forEach items="${childPerm2.children }" var="childPerm3" varStatus="vs3">
																<c:if test="${vs3.first }"><ul></c:if>
																<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm3.id)?true:false}" />
																	<input type="checkbox" value="${childPerm3.id }" id="${childPerm3.id }" parent="${childPerm2.id }" name="permId" <c:if test="${inRole }">checked</c:if> /> 
																	<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm3.name}" /></span>
																	<!-- 五级循环 -->
																	<c:forEach items="${childPerm3.children }" var="childPerm4" varStatus="vs4">
																		<c:if test="${vs4.first }"><ul></c:if>
																		<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm4.id)?true:false}" />
																			<input type="checkbox" value="${childPerm4.id }" id="${childPerm4.id }" parent="${childPerm3.id }" name="permId" <c:if test="${inRole }">checked</c:if> /> 
																			<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm4.name}" /></span>
																		</li>
																		<c:if test="${vs4.last }"></ul></c:if> 
																	</c:forEach>
																</li>
																<c:if test="${vs3.last }"></ul></c:if> 
															</c:forEach>
														</li>
														<c:if test="${vs2.last }"></ul></c:if> 
													</c:forEach>
												</li> 
												<c:if test="${vs.last }"></ul></c:if> 
											</c:forEach>
										</li> 
									</c:forEach>
								</ul>
							</li>
						</ul>
					</td>
				</tr>
			</table>
			<div class="btn"><a href="javascript:void(0)" onclick="submitForm();">保存</a></div>
		</form:form>
	</div>
</body>
</html>