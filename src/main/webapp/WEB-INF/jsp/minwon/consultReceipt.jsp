<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>
    
    
<div class="main-content">
    <div class="page-content">
        <div class="container-fluid">
            <br>
            <br>
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">기본 정보</h4>
                            <div class="table-responsive border-0">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th class="text-white th-type2">접수번호</th>
                                            <th class="text-white th-type2" colspan="3" id="registerNo">${minwon.mw_take_no}</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>민원명</th>
                                            <td colspan="3">${minwon.mw_afr_nm }</td>
                                        </tr>
                                        <tr>
                                            <th>접수일자</th>
                                            <td>${minwon.take_ymd }</td>
                                            <th>처리기한</th>
                                            <td>${minwon.deal_plan_ymd }</td>
                                        </tr>
                                        <tr>
                                            <th>접수부서</th>
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
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                           <div class="d-flex justify-content-between mb-1">
                               <h4 class="card-title mb-3">접수</h4>
                           <div class="float-end">
                           <c:if test="${minwon.deal_nm eq '해결'}">
                           <button type="button" class="btn btn-success waves-effect waves-light"  style="margin: 0 auto;" onclick="goSurvey('${registerNoAES}')">설문조사</button>
                           </c:if>
                           </div>
                           </div>
                            <div class="table-responsive border-0">
                                <table class="table mb-0">
                                    <tbody>
                                        <tr>
                                            <th>처리부서</th>
                                            <td>${minwon.main_deal_dep_nm }</td>
                                            <th>처리담당자</th>
                                            <td>${minwon.dpp_nm }</td>
                                        </tr>
                                        <tr>
                                            <th>전화번호</th>
                                            <td>${minwon.take_sft_nm_tel}</td>
                                            <th>이메일</th>
                                            <td>${minwon.take_stf_nm_email}</td>
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
                            <div class="text-center"><a type="button" class="btn btn-primary waves-effect waves-light" href="/minwon/${registerNoAES}/myProcess" style="margin: 0 auto;">메인이동</a></div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container-fluid -->
    </div>
    </div>
    <div class="modal fade" id="popup-form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>
    <script>
    function goSurvey(registerNoAES){

    	var params = { 		
    			'registerNoAES' : registerNoAES
    			};
    	$.ajax({
    		url : '/minwon/${registerNoAES}/survey',
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

    
    
    
    </script>