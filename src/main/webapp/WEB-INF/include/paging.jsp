<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>


<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<c:if test="${pageVO.nowBlock>1}">
			<li class="page-item"><a class="page-link" href="#none" aria-label="Previous" onclick="fnGoPage(${pageVO.startPage-1})">
					<span aria-hidden="true">«</span>
					<span class="sr-only">Previous</span>
				</a></li>
		</c:if>
		<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}">
			<c:choose>
				<c:when test="${i==pageVO.page }">
					<li class="page-item active"><a class="page-link" href="#none">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="#none" onclick="fnGoPage(${i})">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageVO.nowBlock<pageVO.totBlock }">
			<li class="page-item"><a class="page-link" href="#none" aria-label="Next" onclick="fnGoPage(${pageVO.endPage+1})">
					<span aria-hidden="true">»</span>
					<span class="sr-only">Next</span>
				</a></li>
		</c:if>
	</ul>
</nav>


