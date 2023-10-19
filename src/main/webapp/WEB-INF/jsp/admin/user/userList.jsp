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
                        <h6 class="page-title">담당공무원 관리</h6>
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="#">홈</a></li>
                            <li class="breadcrumb-item"><a href="#">담당공무원 관리</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- end page title -->


            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">담당공무원 관리</h4>
                            <div style="position: relative;"><button type="button" class="btn btn-success waves-effect waves-light posit-right"  onclick="addUser()">등록</button></div>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>담당업무</th>
                                            <th>아이디</th>
                                            <th>이름</th>
                                            <th>전화번호</th>
                                            <th>상담권한</th>
                                            <th>사용여부</th>
                                            <th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
							<c:set var="rownum" value="${pageVO.rownum }" />
                                    <c:forEach var="list" items="${list}">
                                        <tr>
                                            <td>${list.userNo}</td>
                                            <td>${list.task}</td>
                                            <td><a style="color: blue; cursor: pointer;" onclick="addUser(${list.userNo},'U')">${list.id}</a></td>
                                            <td>${list.username}</td>
                                            <td>${list.tel}</td>
                                            <td>${list.openConsultYn}</td>
                                            <td>${list.useYn}</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light" onclick="removeUser('${list.id}')">삭제</button></td>
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
        </div> <!-- container-fluid -->
    </div>
</div>

<div class="modal fade" id="popup-form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>

<script>
$(function(){
	
});

function addUser(userNo,mode){
	var params = {
			'userNo' : userNo,
			'mode' : mode
	};
	$.ajax({
		url : "/admin/user/add",
		type : "post",
		data : params,
		dataType : "html",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(response){
			$("#popup-form").find(".modal-dialog").html(response);
			$("#popup-form").modal("show");
			
		}
		
	});
	
};

function removeUser(userId){
	if(!confirm("삭제하시겠습니까?")) return;
	
	var params = {
			id : userId
	}
	
	$.ajax({
		url : "/admin/user/delete",
		type : "post",
		data : params,
		dataType : "json",
		success : function(response){
			alert(response.msg);
			if(response.result) location.reload();

		}
		
	});
};




</script>

