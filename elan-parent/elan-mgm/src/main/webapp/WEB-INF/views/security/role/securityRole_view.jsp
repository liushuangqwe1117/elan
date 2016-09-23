<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色详情</title>
	<page:applyDecorator name="html_head" page="/WEB-INF/views/includes/jsAndCss.jsp" />
	<script src="${contextPath }/scripts/jquery/jquery.cookie.js" type="text/javascript"></script>
	<script src="${contextPath }/scripts/jquery/plugins/treeview/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${contextPath }/scripts/jquery/plugins/treeview/jquery.treeview.css" type="text/css">
	<script type="text/javascript">
		$(function(){
			//权限导航
			$("#navigation_tree").treeview({
				persist : "cookie",
				collapsed : false,
				cookieId : "treeview-navigation-permission"
			});
		});
	</script>
<body class="p-iframe">
	<div class="m-juese">
		<table>
			<tr>
				<th class="f-fw"><label>角色名称：</label></th>
				<td><c:out value="${obj.name }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>角色类型：</label></th>
				<td><c:out value="${obj.roleType.displayName }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>备注：</label></th>
				<td><c:out value="${obj.description }" /></td>
			</tr>
			<tr>
				<th class="f-fw"><label>创建时间：</label></th>
				<td><fmt:formatDate value="${obj.createdTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>			
			<tr>
				<th class="f-fw"><label>权限集：</label></th>
				<td>
					<ul id="navigation_tree" >
						<li><c:set var="inRole" value="${fn:length(rolePerms) > 0 ? true:false }" /> 
							<span class="<c:out value="${inRole?'node_name':'none'}"/>">系统权限</span>
							<ul>
								<!-- 一级循环 -->
								<c:forEach items="${permList }" var="perm">
									<li><c:set var="inRole" value="${fn:contains(rolePerms,perm.id)?true:false}" /> 
										<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${perm.name}" /></span> 
										<!-- 二级循环 --> 
										<c:forEach items="${perm.children }" var="childPerm" varStatus="vs">
											<c:if test="${vs.first }"><ul></c:if>
											<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm.id)?true:false}" />
												<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm.name}" /></span> 
												<!-- 三级循环 --> 
												<c:forEach items="${childPerm.children }" var="childPerm2" varStatus="vs2">
													<c:if test="${vs2.first }"><ul></c:if>
													<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm2.id)?true:false}" />
														<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm2.name}" /></span> 
														<!-- 四级循环 --> 
														<c:forEach items="${childPerm2.children }" var="childPerm3" varStatus="vs3">
															<c:if test="${vs3.first }"><ul></c:if>
															<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm3.id)?true:false}" />
																<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm3.name}" /></span>
																<!-- 五级循环 -->
																<c:forEach items="${childPerm3.children }" var="childPerm4" varStatus="vs4">
																	<c:if test="${vs4.first }"><ul></c:if>
																	<li><c:set var="inRole" value="${fn:contains(rolePerms,childPerm4.id)?true:false}" />
																		<span class="<c:out value="${inRole?'node_name':'none'}"/>"><c:out value="${childPerm4.name}" /></span>
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
		</tbody>
	</table>
	<div class="btn"><a class="formbtn back" href="${contextPath }/security/role/list.html">返回</a></div>
	</div>
</body>
</html>