<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<!-- ============================================================== -->
<!-- Start right Content here -->
<!-- ============================================================== -->
<div class="main-content">

    <div class="page-content">
        <div class="container-fluid">

            <!-- start page title -->
            <div class="page-title-box">
                <div class="row align-items-center">
                    <div class="col-md-12">
                        <h6 class="page-title">메세지 발송 내역</h6>
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="#">홈</a></li>
                            <li class="breadcrumb-item"><a href="#">메세지 발송 내역</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- end page title -->


<form id="searchForm">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">메세지 발송 내역</h4>
                            <div class="search-row">
                                <ul class="d-flex flex-md-wrap justify-content-end">
                                    <div><button type="submit" class="btn btn-info waves-effect waves-light">검색</button></div>
                                </ul>
                            </div>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                  <thead>
				<tr>
					<th width="50">번호</th>
					<th width="100">날짜</th>
					<th width="120">접수번호</th>
					<th width="300">민원명</th>
					<th width="120">전화번호</th>
					<th width="*">내용</th>
					<!-- <th width="80">요청결과</th> -->
					<th width="200">
						발송결과
						<select name="success">
							<option value="">전체</option>
							<option value="Y" ${messageVO.success eq 'Y'? 'selected':''}>성공</option>
							<option value="N" ${messageVO.success eq 'N'? 'selected':''}>실패</option>
						</select>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="rownum" value="${pageVO.rownum }" />
				<c:forEach var="item" items="${messageList }">
					<tr>
						<td>${rownum }</td>
						<td>
							<fmt:formatDate value="${item.insertDate }" pattern="yyyy-MM-dd" />
							<br />
							<fmt:formatDate value="${item.insertDate }" pattern="HH:mm:ss" />
						</td>
						<td>${item.registerNo }</td>
						<td>${item.template }</td>
						<td>${item.receiver }</td>
						<td>${item.content }</td>
						<td>${item.error }</td>
					</tr>
					<c:set var="rownum" value="${rownum-1 }" />
				</c:forEach>
			</tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    
                     <jsp:include page="/WEB-INF/include/paging.jsp" />
                    
                </div>
            </div>
          </form>

        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->


</div>
<!-- end main content-->

<!-- END layout-wrapper -->

<script>

</script>
