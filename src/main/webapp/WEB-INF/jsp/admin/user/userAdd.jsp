<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>



           <div class="modal-dialog modal1" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" >
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">내용</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive">
                           <form id="saveForm">
                           <input type="hidden" name="mode" value="${param.mode}">
                                <table class="table mb-0 table-type2">
                                    <tbody>
                                        <tr>
                                            <th>담당업무</th>
                                            <td>
												<div class="dropdown">
													<select class="btn btn-secondary dropdown-toggle height-35" name="task" id="task">
															<option value="" >선택</option>
															<option value="종합민원" ${item.task == '종합민원' ? 'selected' : '' } >종합민원</option>
															<option value="건축허가" ${item.task == '건축허가' ? 'selected' : '' }>건축허가</option>
															<option value="건축신고" ${item.task == '건축신고' ? 'selected' : '' }>건축신고</option>
															<option value="개발허가" ${item.task == '개발허가' ? 'selected' : '' }>개발허가</option>
															<option value="농지허가" ${item.task == '농지허가' ? 'selected' : '' }>농지허가</option>
															<option value="산지허가" ${item.task == '산지허가' ? 'selected' : '' }>산지허가</option>
													</select>
												</div>                                            
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>아이디</th>
                                            <td><input class="btn btn-white border-gray height-35" type="text" name="id" value="${item.id}"></td>
                                        </tr>
                                        <tr>
                                            <th>패스워드</th>
                                            <td><input class="btn btn-white border-gray height-35" type="password" name="pwd">
                                                <div class="text-danger">
                                                <c:if test="${param.mode == 'U'}">
                                                ※ 비밀번호 변경시에만 입력해주세요. 미입력시 기존 비밀번호 유지
                                                </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>이름</th>
                                            <td><input class="btn btn-white border-gray height-35" type="text" name="username" value="${item.username}"></td>
                                        </tr>
                                        <tr>
                                            <th>전화번호</th>
                                            <td><input class="btn btn-white border-gray height-35" type="text" name="tel" value="${item.tel}"></td>
                                        </tr>
                                        <tr>
                                            <th>상담신청 작성권한</th>
                                            <td>
                                                <div class="">
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="openConsultYn" class="form-check-input" value="Y" ${item.openConsultYn == 'Y' ? 'checked' : ' ' }>
                                                        <label class="form-check-label" for="customRadioInline1">Y</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="openConsultYn" class="form-check-input" value="N" ${item.openConsultYn == 'N' ? 'checked' : ' ' }>
                                                        <label class="form-check-label" for="customRadioInline2">N</label>
                                                    </div>
                                                </div>
                                                <div>(* 민원인의 상담신청을 공무원에게 배분 가능)</div>
                                            </td>
                                        </tr>
                                       <c:if test="${param.mode == 'U'}">                                        
                                        <tr>
                                            <th>사용여부</th>
                                            <td>
                                                <div class="">
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="useYn" class="form-check-input" value="Y" ${item.useYn == 'Y' ? 'checked' : '' }>
                                                        <label class="form-check-label" for="customRadioInline1">Y</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="useYn" class="form-check-input" value="N" ${item.useYn == 'N' ? 'checked' : '' }>
                                                        <label class="form-check-label" for="customRadioInline2">N</label>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                       </c:if>
                                    </tbody>
                                </table>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary waves-effect modal1" data-bs-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="save()">확인</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            
<script>
$(function(){
});


function save(){
	var saveData = $("#saveForm").serialize();
	
	$.ajax({
		url : "/admin/user/insert",
		type : "post",
		data : saveData,
		dataType : "json",
		success : function(response){
			alert(response.msg);
			if(!response.result) return false;
			
			$("#popup-form").modal("hide");
			location.reload();
		}
			
		
	})
	
}


</script>            