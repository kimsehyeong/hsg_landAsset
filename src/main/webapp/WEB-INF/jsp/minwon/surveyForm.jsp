<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/include/include-taglib.jspf"%>


                    <div class="modal-content" style="width: 900px">
                        <div class="modal-header">
                            <h5 class="modal-title">만족도 조사</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="surveyForm">
                            <input type="hidden" value="${registerNo}" name="registerNo">
                            <p>안녕하십니까?<br>횡성군에서는 귀하께서 신청하신 민원처리와 관련하여 설문조사를 진행하고 있습니다.<br>민원행정 서비스 개선을 위한 자료로 활용하고자 하오니 설문에 참여해 주시면 대단히 감사하겠습니다.</p>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th colspan="2">응답자특성</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>연령</th>
                                            <td>
                                                <div class="">
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="age" class="form-check-input center" value="20" ${survey.age == 20 ? 'checked':'' }>
                                                        <label class="form-check-label" for="twenty">20대</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="age" class="form-check-input" value="30" ${survey.age == 30 ? 'checked':'' }>
                                                        <label class="form-check-label" for="thirty">30대</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="age" class="form-check-input" value="40" ${survey.age == 40 ? 'checked':'' }>
                                                        <label class="form-check-label" for="forty">40대</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="age" class="form-check-input" value="50" ${survey.age == 50 ? 'checked':'' }>
                                                        <label class="form-check-label" for="fifty">50대</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="age" class="form-check-input" value="60" ${survey.age == 60 ? 'checked':'' }>
                                                        <label class="form-check-label" for="sixty">60대 이상</label>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>성별</th>
                                            <td>
                                                <div class="">
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio" name="gender" class="form-check-input" value="M" ${survey.gender == 'M' ? 'checked':'' }>
                                                        <label class="form-check-label" for="male">남성</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input type="radio"  name="gender" class="form-check-input" value="W" ${survey.gender == 'W' ? 'checked':'' }>
                                                        <label class="form-check-label" for="female">여성</label>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <div class="table-responsive">
                                <table class="table mb-0 table-type2">
                                    <thead>
                                        <tr>
                                            <th>설문항목</th>
                                            <th>매우만족</th>
                                            <th>만족</th>
                                            <th>보통</th>
                                            <th>불만</th>
                                            <th>매우불만</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <tr> 
                                            <td class="text-left">[만족도] 전반적인 민원서비스 만족도는 어떻습니까?</td>
                                            <td><input type="radio" class="form-check-input" name="qsatisfy" checked value="A" ${survey.qsatisfy == 'A' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qsatisfy" value="B" ${survey.qsatisfy == 'B' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qsatisfy" value="C" ${survey.qsatisfy == 'C' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qsatisfy" value="D" ${survey.qsatisfy == 'D' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qsatisfy" value="E" ${survey.qsatisfy == 'E' ? 'checked':'' }></td>
                                        </tr>
                                        <tr>
                                            <td class="text-left">[친절성] 담당 공무원이 친절하게 업무를 처리하였습니까?</td>
                                            <td><input type="radio" class="form-check-input" name="qkindness"  checked value="A" ${survey.qkindness == 'A' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qkindness" value="B"  ${survey.qkindness == 'B' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qkindness" value="C"  ${survey.qkindness == 'C' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qkindness" value="D"  ${survey.qkindness == 'D' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qkindness" value="E"  ${survey.qkindness == 'E' ? 'checked':'' }></td>
                                        </tr>
                                        <tr>
                                            <td class="text-left">[신속성] 담당 공무원이 신속하게 업무를 처리하였습니까?</td>
                                            <td><input type="radio" class="form-check-input" name="qquickness" checked value="A"  ${survey.qquickness == 'A' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qquickness" value="B" ${survey.qquickness == 'B' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qquickness" value="C" ${survey.qquickness == 'C' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qquickness" value="D" ${survey.qquickness == 'D' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qquickness" value="E" ${survey.qquickness == 'E' ? 'checked':'' }></td>
                                        </tr>
                                        <tr>
                                            <td class="text-left">[전문성] 담당 공무원이 업무와 관련하여 전문적인 지식을 갖고 있습니까?</td>
                                            <td><input type="radio" class="form-check-input" name="qprofessional" checked value="A" ${survey.qprofessional == 'A' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qprofessional" value="B" ${survey.qprofessional == 'B' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qprofessional" value="C" ${survey.qprofessional == 'C' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qprofessional" value="D" ${survey.qprofessional == 'D' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qprofessional" value="E" ${survey.qprofessional == 'E' ? 'checked':'' }></td>
                                        </tr>
                                        <tr>
                                            <td class="text-left">[안내 서비스 만족도] 실시간 민원진행상황 안내서비스에 대한 만족도는 어떻습니까?</td>
                                            <td><input type="radio" class="form-check-input" name="qservice" checked value="A" ${survey.qservice == 'A' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qservice" value="B" ${survey.qservice == 'B' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qservice" value="C" ${survey.qservice == 'C' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qservice" value="D" ${survey.qservice == 'D' ? 'checked':'' }></td>
                                            <td><input type="radio" class="form-check-input" name="qservice" value="E" ${survey.qservice == 'E' ? 'checked':'' }></td>
                                        </tr> 
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th>개선사항</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><textarea name="suggestion">${survey.suggestion}</textarea></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                    </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">닫기</button>
                            <c:if test="${survey.registerNo eq null}">
                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="surveySave()">저장</button>
                        	</c:if>
                        </div>
                    </div>
                    
                    
                    
<script>
$(function(){
});

function surveySave(){
	var registerNo = '${registerNo}';
	var saveData =  $("#surveyForm").serialize();
	
	$.ajax({
		url : "/minwon/survey/save",
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



</script>                    
