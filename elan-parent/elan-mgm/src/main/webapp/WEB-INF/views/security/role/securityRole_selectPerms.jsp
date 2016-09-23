<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
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