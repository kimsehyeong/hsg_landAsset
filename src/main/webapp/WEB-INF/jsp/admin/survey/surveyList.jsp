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
                        <h6 class="page-title">만족도 조사관리</h6>
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="#">홈</a></li>
                            <li class="breadcrumb-item"><a href="#">만족도 조사관리</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- end page title -->

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">만족도 조사관리</h4>
                            <div class="search-row">
                            <form id="searchForm">
                                <ul class="d-flex flex-md-wrap justify-content-between">
                                    <li class="">등록일<input class="btn btn-white border-gray height-35 datepicker" type="text" name="startDate" value="${surveyVO.startDate}"><span class="wave">~</span>
                                    <input class="btn btn-white border-gray height-35 datepicker" type="text" name="endDate" value="${surveyVO.endDate}">
                                    <button type="submit" class="btn btn-info waves-effect waves-light" >검색</button></li>
                                    <div><button type="button" class="btn btn-warning waves-effect waves-light" onclick="excelDownload()">EXCEL</button></div>
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
                                            <th>등록일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
							<c:set var="rownum" value="${pageVO.rownum }" />
                                    <c:forEach var="list" items="${list }">
                                        <tr>
                                            <td>${list.surveyId }</td>
                                            <td class="cursor-pointer"><a onclick="goView('${list.registerNo}')" style="color: blue; cursor: pointer;">${list.registerNo }</a></td>
                                            <td class="cursor-pointer"><a onclick="goView('${list.registerNo}')" style="color: blue; cursor: pointer;">${list.mwAfrShtnm }</a></td>
                                            <td><fmt:formatDate value="${list.insertDate}" pattern="yyyy-MM-dd-HH:mm" /></td>
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
<div class="modal fade" id="popup-form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

<script>

$(function(){
});

/* function getList() {
    var formData = $("#searchForm").serialize();
    $.ajax({
        url: '/admin/survey/getlist',
        type: 'POST',
        data: formData,
        dataType: 'json',
        success: function(data) {
            var tbody = $(".table tbody");
            tbody.empty(); 
            $.each(data, function(index, survey) {
                var tr = $("<tr>");
                tr.append($("<td>").text(survey.surveyId));
                tr.append($("<td>").append($("<a>").attr("onclick", "goView('" + survey.registerNo + "')").text(survey.registerNo)));
                tr.append($("<td>").append($("<a>").attr("onclick", "goView()").text(survey.surveyTitle)));
               var formattedDate = moment(survey.insertDate).format('YYYY-MM-DD-HH:mm');
				tr.append($("<td>").text(formattedDate));
                tbody.append(tr);
            });
        }
    });
} */



function goView(registerNo){
	var params = {
			'registerNo' : registerNo
	};
	$.ajax({
		url : "/admin/survey/view",
		type : "post",
		dataType : "html",
		data : params,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(response){
			$("#popup-form").find(".modal-dialog").html(response);
			$("#popup-form").modal("show");
		}
	});
}


function excelDownload() {
	 var formData = $("#searchForm").serialize();
	location.href = '/admin/survey/excel?' + formData;
}


</script>
