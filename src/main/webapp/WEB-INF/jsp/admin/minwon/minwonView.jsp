<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <h6 class="page-title">민원리스트</h6>
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="#">홈</a></li>
                            <li class="breadcrumb-item"><a href="#">민원리스트</a></li>
                            <li class="breadcrumb-item active" aria-current="page">민원상세보기</li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- end page title -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">접수내역</h4>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <tbody>
                                        <tr>
                                            <th>민원명</th>
                                            <td colspan="3">${minwon.mw_afr_nm }</td>
                                        </tr>
                                        <tr>
                                            <th>접수번호</th>
                                            <td colspan="3">${minwon.mw_take_no}</td>
                                        </tr>
                                        <tr>
                                            <th>토지소재지</th>
                                            <td colspan="3"><input class="btn btn-white border-gray height-35" style="width: 354px" id="location" value="${location.location}">
                                            <button type="button" class="btn btn-dark waves-effect waves-light" onclick="saveArea()">저장</button></td>
                                        </tr>                                        
                                        <tr>
                                            <th>접수일자</th>
                                            <td class="date">${minwon.take_ymd }</td>
                                            <th>처리기한</th>
                                            <td class="date">${minwon.deal_plan_ymd }</td>
                                        </tr>
                                        <tr>
                                            <th>접수부서명</th>
                                            <td>${minwon.take_dep_nm }</td>
                                            <th>접수담당자</th>
                                            <td>${minwon.take_stf_nm }</td>
                                        </tr>
                                        <tr>
                                            <th>전화번호</th>
                                            <td>${minwon.take_sft_nm_tel}</td>
                                            <th>이메일</th>
                                            <td>${minwon.take_stf_nm_emai}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <br>
                            <h4 class="card-title mb-3">진행내역</h4>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th>작업종류</th>
                                            <th>담당자</th>
                                            <th>부서</th>
                                            <th>일시</th>
                                            <th>전화번호</th>
                                            <th>이메일</th>
                                            <th>발송</th>
                                            <th>발송상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="item" items="${progress }">
                                        <tr>
                                            <td>${item.bpm_step_nm}</td>
                                            <td>${item.user_name }</td>
                                            <td>${item.dept_name }</td>
                                            <td>${item.step_end_td }</td>
                                            <td>${item.tel}</td>
                                            <td>${item.email}</td>
                                            <td>
                                            <c:if test="${item.bpm_step_nm ne '수령처리'}">
                                            <button type="button" class="btn btn-warning waves-effect waves-light" onclick="sendMessage('${minwon.mw_afr_nm }','${minwon.mw_take_no}','${item.bpm_step_nm}','${item.user_name }','${item.tel}')">kakaotalk</button>
                                            </c:if>
                                            </td>
                                            <td>
                                            	<c:if test="${item.bpm_step_nm ne '수령처리'}">
	                                            	<c:choose>
														<c:when test="${item.msgCount > 0 }"> ${item.msgCount }회 발송완료</c:when>
														<c:otherwise>발송전</c:otherwise>
													</c:choose>
                                            	</c:if>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <br>
							    <div style="text-align: right;">
							        <button class="btn btn-warning waves-effect waves-light" onclick="sendMessage('민원해결(등기촉탁)','${minwon.mw_take_no}','0','${minwon.dpp_nm }','${minwon.dpp_nm_tel}')">등기촉탁</button>
							        <button class="btn btn-warning waves-effect waves-light" onclick="sendMessage('민원해결(개발행위허가-원스톱건)','${minwon.mw_take_no}','0','${minwon.dpp_nm }','${minwon.dpp_nm_tel}')">원스톱건</button>
							    </div>
                            </div>
                            <br>
                            <br>
                            <h4 class="card-title mb-3">현재상태</h4>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <tbody>
                                        <tr>
                                            <th>처리구분</th>
                                            <td>${minwon.deal_nm }</td>
                                            <th>처리일자</th>
                                            <td>${minwon.real_deal_ymd }</td>
                                        </tr>
                                        <tr>
                                            <th>처리부서</th>
                                            <td>${minwon.main_deal_dep_nm }</td>
                                            <th>처리담당자</th>
                                            <td>${minwon.dpp_nm }</td>
                                        </tr>
                                        <tr>
                                            <th>전화번호</th>
                                            <td>${minwon.dpp_nm_tel}</td>
                                            <th>이메일</th>
                                            <td>${minwon.dpp_nm_email}</td>
                                        </tr>
                                        <tr>
                                            <th>처리근거</th>
                                            <td colspan="3">${minwon.deal_bas}</td>
                                        </tr>
                                        <tr>
                                            <th>처리내용</th>
                                            <td colspan="3">${minwon.deal_dtl}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                           <br>
                            <br>
                            <h4 class="card-title mb-3">민원서비스</h4>
                            <div style="position: relative;"><button type="button" class="btn btn-success waves-effect waves-light posit-right" onclick="attachFile()">업로드</button></div>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <colgroup>
                                        <col width="10%">
                                        <col width="40%">
                                        <col width="40%">
                                        <col width="10%">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>등록일</th>
                                            <th>첨부파일</th>
                                            <th>Comments</th>
                                            <th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="file" items="${file}">
                                        <tr>
                                            <td style="display: none" ><input id="fileId" value="${file.id}"></td>
                                            <td><fmt:formatDate value="${file.uploadDate}" pattern="yyyy-MM-dd" /></td>
                                            <td><a style="color: blue; cursor: pointer;" onclick="downloadFile('${file.fileName}','${file.filePk}')">${file.fileName}</a></td>
                                            <td>${file.fileComments}</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light" onclick="fileDelete()">삭제</button></td>
                                        </tr>
									</c:forEach>                                        
                                    </tbody>
                                </table>
                            </div>                            
                            <br>
                            <br>
                            <h4 class="card-title mb-3">상담신청 내역</h4>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th>신청날짜</th>
                                            <th>상태</th>
                                            <th>답변안내</th>
                                            <th>내용</th>
                                            <th>답변등록일</th>
                                            <th>답변</th>
                                            <th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
							<c:forEach var="item" items="${consultList}">
                                        <tr>
                                            <td><fmt:formatDate value="${item.consultDate}" pattern="yyyy-MM-dd-HH:mm" /></td>
                                            <td>${item.consultStatus }</td>
                                            <td>${item.answerType }</td>
                                            <td>${item.consultInfo }</td>
                                            <td><fmt:formatDate value="${item.commentDate}" pattern="yyyy-MM-dd-HH:mm" /></td>
                                            <td><button type="button" class="btn btn-danger waves-effect waves-light" onclick="answerConsult('${item.consultId}')">답변</button></td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light" onclick="consultDelte('${item.consultId}')">삭제</button></td>
                                        </tr>
                             </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <div class="text-center"><button type="button" class="btn btn-primary waves-effect waves-light" onclick="location.href='/admin/minwon/list'" style="margin: 0 auto;">목록</button></div>

                        </div>
                    </div>
                </div>
            </div>
                <!-- /.modal-dialog -->
            </div>
        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->
<!-- end main content-->
<form class="downloadForm" method="POST" target='downloadFrame' action="/admin/minwon/file/download">
	<input type="hidden" name="fileName" value="">
	<input type="hidden" name="filePk" value="">
</form>	


<div class="modal fade" id="popup-form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center" style="width: 600px;height: 50%"></div>
</div>
<div class="modal fade" id="popup-view" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>

<!-- END layout-wrapper -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>


<script>
$(function(){
	
    $('.date').each(function() {
        var dateString = $(this).text();
        var date = moment(dateString, "YYYYMMDD");
        $(this).text(date.format("YYYY-MM-DD"));
    });
    
});
  
function sendMessage(minwonTitle,registerNo,status,manager,managerNumber){
	if(!confirm("메세지를 전송하시겠습니까?")) return ;
	
	
	
	var refer = '0';
// 	minwonTitle = '민원해결(등기촉탁)';
// 	minwonTitle = '민원해결(토지이동)';
// 	minwonTitle = '민원해결(개발행위허가)';
// 	minwonTitle = '민원해결(개발행위허가-원스톱건';
// 	minwonTitle = 'test';
// 	status = '해결';
// 	status = '접수';
	
	var receiverName = "${in_mw_aplr_nm}";
	var receiverNumber = "${mw_aplct_hpno}";
// 	var receiverNumber = "05223412342";
	
	var params = {
			'registerNo' : registerNo,
			'receiverName' : receiverName,
			'receiverNumber': receiverNumber,
			'template' : minwonTitle,
			'status' : status ,
			'refer' : refer,
			'manager': manager,
			'managerNumber' : managerNumber
	}
	
	$.ajax({
		url : "/admin/minwon/sendMessage",
		data : params,
		type : "post",
		dataType : "json",
		success : function(response){
			alert(response.msg);
			if(response.result)
				fnReload();
		}
	});
	
}

function attachFile() {
	var registerNo = "${param.registerNo}";

	var params = { 		
			'registerNo' : registerNo
			};
	$.ajax({
		url : '/admin/minwon/fileupload/form',
		type : 'post',
		data : params,
		dataType : "html",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : params ,
		success : function (response){
			console.log("success");
			$("#popup-form").find(".modal-dialog").html(response);
			$("#popup-form").modal("show");
				
				
		}
	});
}

function fileDelete(){
	var fileId = $('#fileId').val();
	
	var params = { 		
			'id' : fileId
	};
	if(!confirm("삭제하시겠습니까?")) return ;
	
	$.ajax({
		url : '/admin/minwon/file/delete',
		type : 'post',
		data : params,
		dataType : "json",
		data : params ,
		success : function (response){
			console.log(response);
			if(!response.result) {
				alert(response.msg);
				return false;
				};
				
				alert(response.msg);
				location.reload();
				
		}
	});
}

function saveArea(){
	var registerNo = "${param.registerNo}";
	var locationInput = $('#location').val();
	
	var params = { 		
			'location' : locationInput,
			'registerNo' : registerNo
	};
	if(!confirm("저장하시겠습니까?")) return ;
	
	$.ajax({
		url : '/admin/minwon/landLocation',
		type : 'post',
		data : params,
		dataType : "json",
		data : params ,
		success : function (response){
			console.log(response);
			if(!response.result) {
				alert(response.msg);
				return false;
				};
				alert(response.msg);
// 				$('#location').val('${location.location}');			
		}
	});
}

function answerConsult(consultId){
	var registerNo = "${param.registerNo}";

	var params = { 		
			'consultId' : consultId,
			'registerNo' : registerNo
			
			};
	$.ajax({
		url : '/admin/minwon/consultComment/form',
		type : 'post',
		data : params,
		dataType : "html",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function (response){
			console.log("success");
			$("#popup-view").find(".modal-dialog").html(response);
			$("#popup-view").modal("show");
				
				
		}
	});
}

function consultDelte(consultId){
	if(!confirm("상담삭제하시겠습니까?")) return;
	
	var params = {
			'consultId' : consultId
	}
	
	$.ajax({
		url : "/minwon/consultReq/delete",
		type : 'post',
		data : params,
		dataType : 'json',
		success: function(response){
			alert(response.msg);
			if(!response.result) 
				return false;
			location.reload();
		}
	});
}


	function downloadFile(fileName,filePk) {
		$form = $('.downloadForm');
		$form.find('input[name="fileName"]').val(fileName);
		$form.find('input[name="filePk"]').val(filePk);
		$form.submit();

	}


</script>
