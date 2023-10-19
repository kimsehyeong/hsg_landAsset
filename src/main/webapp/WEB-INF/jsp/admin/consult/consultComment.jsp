<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>


<!--             답변모달 -->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">내용</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive">
                                <input type="hidden" value="${receiver}" id="receiver">
                                <table class="table mb-0">
                                    <tbody>
                                       <tr>
                                            <th>내용</th>
                                            <td>${consult.consultInfo }</td>
                                        </tr>
                                        <tr>
                                            <th>답변</th>
                                            <td><textarea id="commentInfo">${consult.commentInfo }</textarea></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="saveComments('${consult.consultId}','${receiver}')">확인</button>
                        </div>
                    </div>

                    
<script>
function saveComments(consultId,receiver){
	var commentInfo = $('#commentInfo').val();
	var registerNo = "${param.registerNo}";
	
	var params = {
			'consultId' : consultId,
			'commentInfo' : commentInfo,
			'registerNo' : registerNo,
			'receiver' : receiver,
	};
	console.log("consol"+registerNo);
	
	if(!confirm("답변등록하시겠습니까?")) return;
	

	$.ajax({
		url : "/admin/minwon/comment/save",
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


</script>