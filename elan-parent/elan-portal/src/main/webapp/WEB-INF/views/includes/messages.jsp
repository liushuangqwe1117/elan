<%@ include file="../includes/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8"%>

<c:if test="${success_messages != null || error_messages != null || (validate_errros_count != null && validate_errros_count >0)}">
	<div class="messages">
		<button type="button" class="close" onclick="$.lightssh.removeMessages();">&times;</button>
		
		<!-- validate errors -->
		<c:if test="${validate_errros_model_obj != null }">
			<c:set value="${validate_errros_model_obj}.*" var="validate_errros_model_obj_var"/>
			<spring:bind path="${validate_errros_model_obj_var}">
				<c:if test="${not empty status.errorMessages}">
					<c:forEach items="${status.errorMessages }" var="em">
						<div class="warning"><c:out value="${em }"/></div>
					</c:forEach>
				</c:if>
			</spring:bind>
		</c:if>
		
		<%-- Success Messages --%>
		<c:if test="${not empty success_messages}">
			<c:forEach var="msg" items="${success_messages}">
				<div class="success"><c:out value="${msg}" escapeXml="false"/></div>
			</c:forEach>
			<c:remove var="success_messages" scope="session" />
		</c:if>
		
		<%-- error Messages --%>
		<c:if test="${not empty error_messages}">
			<c:forEach var="msg" items="${error_messages}">
				<div class="error"><c:out value="${msg}" escapeXml="false"/></div>
			</c:forEach>
			<c:remove var="error_messages" scope="session" />
		</c:if>
	</div>
</c:if>