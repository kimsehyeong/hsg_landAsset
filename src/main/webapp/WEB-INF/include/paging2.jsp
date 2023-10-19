<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="pagination">
	<ul>
		<c:if test="${pageVO.nowBlock>1}">
			<li><a href="#none" onclick="showInfo('${vo.registerNo }','${pageVO.startPage-1}');">&lt; </a></li>
		</c:if>
		
		<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}">
			 <c:choose>
			<c:when test="${i==pageVO.page }">
				<li class="current-page"><a href="#" class="page-link" >${i}</a></li>
			</c:when>
			<c:otherwise> 
				<li><a id="abcdefg" href="#none" onclick="showInfo('${vo.registerNo }','${i}');">${i}</a></li>
			  </c:otherwise>
			</c:choose> 
		</c:forEach>
		<c:if test="${pageVO.nowBlock<pageVO.totBlock }">
			<li><a href="#none" onclick="showInfo('${vo.registerNo }','${pageVO.endPage+1}');">&gt; </a></li>
		</c:if>
	</ul>
</div>



