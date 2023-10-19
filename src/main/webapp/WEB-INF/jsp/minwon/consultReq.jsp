<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/include/include-taglib.jspf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- ============================================================== -->
<!-- Start right Content here -->
<!-- ============================================================== -->
<div class="main-content">

    <div class="page-content">
        <div class="container-fluid">
            <br>
            <br>
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">상담 신청</h4>
                            <p>궁금하신 사항은 상담신청으로 문의해 주세요.</p>
                            <form id="consultForm">
                            <input type="hidden" value="${registerNo}" name="registerNo">
                            <input type="hidden" value="${receiver}" name="receiver">
                            <div class="table-responsive border-0">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th class="text-white th-type2" colspan="4">상담 내용</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="4"><textarea placeholder="내용을 입력해주세요." style="height: 100px;" name="consultInfo" ></textarea></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="table-responsive border-0">
                                <br>
                                <table class="table mb-0">
                                    <tbody>
                                        <tr>
                                            <th>상담답변 안내</th>
                                            <td colspan="3">
                                                <div class="">
                                                    <!--<label class="form-label mb-3 d-flex">Gender :</label>-->
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="answerType" class="form-check-input" value="call" checked>
                                                        <label class="form-check-label" for="male">전화</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="answerType" class="form-check-input" value="kakao" >
                                                        <label class="form-check-label" for="female">카카오톡</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="answerType" class="form-check-input" value="SNS" >
                                                        <label class="form-check-label" for="female">문자</label>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <div class="float-end">
                                <a type="button" class="btn btn-primary waves-effect waves-light" href="/minwon/${registerNo}/myProcess" style="margin: 0 auto;">메인이동</a>
                                <button type="button" class="btn btn-info waves-effect waves-light" onclick="consultSave()" style="margin: 0 auto;">확인</button>
                            </div>
                            </form>
                            <br><br></br>
						<div class="table-responsive border-0">
							<ul>
							<c:forEach var="item" items="${consultList}">
								<li>
									<div class="table-responsive border-0">
										<p>신청날짜 : <fmt:formatDate value="${item.consultDate}" pattern="yyyy-MM-dd-HH:mm" /> </p>
										<p>상담답변 안내 : ${item.answerType }  </p>
										<p>상담내용 : ${item.consultInfo }</p>
										<p><b>상태 : ${item.consultStatus }</b></p>
										<c:if test="${item.consultStatus eq '답변완료' }">
										<p><b>답변내용 : ${item.commentInfo }</b></p>
										</c:if> 
									</div>
									<div class="right_btn">
										<c:if test="${item.consultStatus eq '접수' }">
										<button type="button" class="btn btn-primary waves-effect waves-light" onclick="updateConsultForm('${item.consultId}');">상담수정</button>
										</c:if>
										<c:if test="${item.consultStatus eq '접수' }">
										<button type="button" class="btn btn-warning waves-effect waves-light" onclick="deleteConsult('${item.consultId}');">상담취소</button>
										</c:if>
									</div>
								</li>
								<br>
								 <li>--------------------------------------------------------------------------------</li>
							</c:forEach>
							</ul>
						</div>                            
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- container-fluid -->
    </div> <!-- End Page-content -->
</div><!-- end main content-->


<div class="modal fade" id="popup-view" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>
<script>

function consultSave(){
	var registerNo = '${registerNo}';
	var saveData =  $("#consultForm").serialize();
	
	$.ajax({
		url : "/minwon/consultReq/save",
		type : 'post',
		data : saveData,
		dataType : 'json',
		success: function(response){
			alert(response.msg);
			if(!response.result) 
				return false;
			location.reload();
// 			fnRedirect('/minwon/'+registerNo+'/myProcess');
		}
	});
}

function deleteConsult(consultId){
	if(!confirm("상담취소하시겠습니까?")) return;
	
	var params = {
			'consultId' : consultId
	}
	
	$.ajax({
		url : "/minwon/consultReq/delete",
		type : 'post',
		data : params,
		dataType : 'json',
		success: function(response){
// 			alert(response.msg);
			if(!response.result) 
				return false;
			location.reload();
		}
	});
}


function updateConsultForm(consultId){

	var params = { 		
			'consultId' : consultId
			};
	$.ajax({
		url : '/minwon/consultReq/update/form',
		type : 'post',
		data : params,
		dataType : "html",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : params ,
		success : function (response){
			console.log("success");
			$("#popup-view").find(".modal-dialog").html(response);
			$("#popup-view").modal("show");
				
				
		}
	});
}





</script>