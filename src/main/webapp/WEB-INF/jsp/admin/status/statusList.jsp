<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>



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
                        <h6 class="page-title">통계</h6>
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="#">홈</a></li>
                            <li class="breadcrumb-item"><a href="#">통계</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- end page title -->






            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">통계</h4>
                            <div class="search-row">
                            <form id="searchForm">
                                <ul class="d-flex flex-md-wrap justify-content-between">
                                    <li class="">등록일<input class="btn btn-white border-gray height-35 datepicker" name="startDate" type="text" value="${statusVO.startDate }"><span class="wave">~</span>
                                    <input class="btn btn-white border-gray height-35 datepicker" type="text" name="endDate" value="${statusVO.endDate }">
                                    <button type="submit" class="btn btn-info waves-effect waves-light" >검색</button></li>
                                    <div><button type="button" class="btn btn-warning waves-effect waves-light" onclick="excelStatusDownload()">EXCEL</button></div>
                                </ul>
							</form>
                            </div>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>접수번호</th>
                                            <th>민원명</th>
                                            <th>민원인</th>
                                            <th>전화번호</th>
                                            <th>담당자</th>
                                            <th>접수일</th>
                                            <th>처리기한</th>
                                            <th>접수확인</th>
                                        </tr>
                                    </thead>
                                    <tbody>
							<c:set var="rownum" value="${pageVO.rownum }" />
                                    <c:forEach var="list" items="${statsList}" varStatus="status">
                                        <tr>
                                            <td>${rownum}</td>
                                            <td>${list.registerNo }</td>
                                            <td>${list.mwAfrShtnm}</td>
                                            <td>${list.mwAplctNm}</td>
                                            <td>${list.mwAplctHpno}</td>
                                            <td>${list.dppUsrNm}</td>
                                            <td>${list.takeYmd}</td>
                                            <td>${list.dealPlanYmd}</td>
                                            <td>1</td>
                                        </tr>
                                    </c:forEach>
							<c:set var="rownum" value="${rownum-1 }" />
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    
                    <jsp:include page="/WEB-INF/include/paging.jsp" />
                    
                </div>
            </div>
        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->


</div>
<!-- end main content-->

<!-- END layout-wrapper -->
<script>

function excelStatusDownload(){
	 var formData = $("#searchForm").serialize();
		location.href = '/admin/status/excel?' + formData;
}

/* function getList() {
    var formData = $("#searchForm").serialize();
    $.ajax({
        url: '/admin/status/getList',
        type: 'POST',
        data: formData,
        dataType: 'json',
        success: function(data) {
            // 기존의 테이블 행 삭제
            $("table tbody").empty();

            // 새로운 행 추가
            $.each(data, function(index, item) {
                var row = $("<tr>");
                row.append($("<td>").text(index + 1)); // 번호
                row.append($("<td>").text(item.registerNo)); // 접수번호
                row.append($("<td>").text(item.mwAfrShtnm)); // 민원명
                row.append($("<td>").text(item.mwAplctNm)); // 민원인
                row.append($("<td>").text(item.mwAplctHpno)); // 전화번호
                row.append($("<td>").text(item.dppUsrNm)); // 담당자
                row.append($("<td>").text(item.takeYmd)); // 접수일
                row.append($("<td>").text(item.dealPlanYmd)); // 처리기한
                row.append($("<td>").text(1)); // 접수확인
                $("table tbody").append(row);
            });
        },
        error: function(request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
} */
</script>
