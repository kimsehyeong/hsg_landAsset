<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>
    
		         <div class="modal-content">
		             <div class="modal-header">
		                 <h5 class="modal-title"></h5>
		                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		             </div>
		             <div class="modal-body">
                            <h4 class="card-title mb-3">상담 수정</h4>
                            <form id="updateForm">
                            <input type="hidden" value="${consultUpdateOne.consultId}" name="consultId">
                            <div class="table-responsive border-0">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th class="text-white th-type2" colspan="4">상담 내용</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="4"><textarea placeholder="내용을 입력해주세요." style="height: 100px;" name="consultInfo" >${consultUpdateOne.consultInfo }</textarea></td>
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
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="answerType" class="form-check-input" value="call" ${consultUpdateOne.answerType == 'call' ? 'checked' : '' }>
                                                        <label class="form-check-label" for="male">전화</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="answerType" class="form-check-input" value="kakao" ${consultUpdateOne.answerType == 'kakao' ? 'checked' : '' }>
                                                        <label class="form-check-label" for="female">카카오톡</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="answerType" class="form-check-input" value="SNS" ${consultUpdateOne.answerType == 'SNS' ? 'checked' : '' } >
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
                            <button type="button" class="btn btn-secondary waves-effect modal5" data-bs-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="updateComments()">수정</button>
                            </div>
                            </form>
                        </div>
                        <div class="modal-footer">

                        </div>
                    </div>
 <script>
 
 $(function(){
 });
 
 function updateComments(consultId){
		if(!confirm("수정하시겠습니까?")) return;
		
		var updateData =  $("#updateForm").serialize();

		$.ajax({
			url : "/minwon/consultReq/update",
			type : 'post',
			data : updateData,
			dataType : 'json',
			success: function(response){
	 			alert(response.msg);
				if(!response.result) 
					return false;
				location.reload();
			}
		});
	}
 
 
 </script>
 
            
            
            
            
            
         