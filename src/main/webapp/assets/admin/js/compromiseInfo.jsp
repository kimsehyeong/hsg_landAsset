<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="indexListAjax">
	<table class="table">
		<thead>
			<tr>
				<th>등록일</th>
				<th>협의상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="status" var="item" items="${list }">
				<tr>
					<td><fmt:formatDate value="${item.insertDate }" pattern="yyyy-MM-dd" /></td>
					<td><p class="text-bold2 text-left">${item.department }
							> ${item.compromise } > ${item.policy }
							<c:if test="${item.departNo ne null }">
				(${item.departNo })
				</c:if>
						</p>
						<p class="text-left">
							<c:if test="${item.information eq ''}">
								<b>추가 내용 없음</b>
							</c:if>
							${item.information}
						</p></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/include/paging2.jsp" />
</div>