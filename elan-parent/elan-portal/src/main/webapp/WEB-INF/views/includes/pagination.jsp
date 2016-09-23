<%@ include file="../includes/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<div class="m-pages">
	<div class="f-fr">
		共<c:out value="${pagination.total }" />条记录，共<c:out value="${pagination.lastPage }" />页，每页显示 
		<select onchange="location.href='?pageNumber=1<c:out value="${paginationQueryParam}" />&pageSize='+this.value">
			<option value="10" <c:if test="${pagination.pageSize == 10 }">selected="selected"</c:if>>10</option>
			<option value="15" <c:if test="${pagination.pageSize == 15 }">selected="selected"</c:if>>15</option>
			<option value="20" <c:if test="${pagination.pageSize == 20 }">selected="selected"</c:if>>20</option>
			<option value="50" <c:if test="${pagination.pageSize == 50 }">selected="selected"</c:if>>50</option>
			<option value="100" <c:if test="${pagination.pageSize == 100 }">selected="selected"</c:if>>100</option>
		</select> 条记录
	</div>
	<div class="f-fr">
		<c:if test="${pagination.currentPage == 1 }">
			<a href="javascript:void(0);" title="上一页" class="active">&lt;</a>
		</c:if>
		<c:if test="${pagination.currentPage != 1 }">
			<a href="?pageNumber=${pagination.prePage }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />" title="上一页" class="i-prev">&lt;</a>
		</c:if>
		
		<c:forEach items="${pagination.pageNumList }" var="num">
			<c:choose>
				<c:when test="${fn:indexOf(num , '_') > -1 }">
					<a href="?pageNumber=${fn:split(num,'_')[0] }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />">...</a>
				</c:when>
				<c:otherwise>
					<c:if test="${(pagination.currentPage) == num }">
						<a href="javascript:void(0)" class="active" ><c:out value="${num }" /></a>
					</c:if>
					<c:if test="${(pagination.currentPage) != num }">
						<a href="?pageNumber=${num }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />" ><c:out value="${num }" /></a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${pagination.currentPage == pagination.lastPage }">
			<a href="javascript:void(0);" title="下一页" class="active">&gt;</a>
		</c:if>
		<c:if test="${pagination.currentPage != pagination.lastPage }">
			<a href="?pageNumber=${pagination.nextPage }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />" title="下一页">&gt;</a>
		</c:if>
	</div>
	<div class="f-cb"></div>
</div>