<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>



<!-- form wizard -->
<script src="/assets/front/js/jquery.steps.min.js"></script>

    <script src="https://kit.fontawesome.com/33c9a6582b.js" crossorigin="anonymous"></script>
<!-- form wizard init -->
<!-- <script src="/assets/front/js/form-wizard.init.js"></script> -->

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
                                            <td>${minwon.mw_afr_nm }</td>
                                            <th>토지 소재지</th>
                                            <td>${location.location}</td>                                            
                                        </tr>
                                        <tr>
                                            <th>접수일자</th>
                                            <td>${minwon.take_ymd }</td>
                                            <th>처리기한</th>
                                            <td>${minwon.deal_plan_ymd }</td>
                                        </tr>
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
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">처리 진행사항1</h4>
                            <div class="step">
                                <ul>
                                    <li class="active">
                                        <a href="/minwon/${registerNo}/myProcess">접수</a>
                                    </li>
                                    <li class="active">
                                        <a href="/minwon/${registerNo}/myProcess">수령처리</a>
                                    </li>
                                    <li class="${minwon.deal_nm eq '처리중' || minwon.deal_nm eq '해결' ? 'active' : ''}">
                                        <a href="/minwon/${registerNo}/myProcess">처리중</a>
                                    </li>
                                    <li class="active" style="display: none;">
                                        <a href="receipt.html">보완보정</a>
                                    </li>
                                    <li class="${minwon.deal_nm eq '해결' ? 'active' : ''}">
                                        <a href="/minwon/${registerNo}/receipt">
                                        해결<br>
                                        <c:if test="${minwon.deal_nm eq '해결'}">
                                        ${result.dpp_nm} 주무관이 처리기간을<br> 
                                        ${result.step_end_td}일 단축하였습니다.</a>
                                    	</c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->
</div>
<!-- end main content-->


<!-- END layout-wrapper -->
<script>
$(function(){
});

</script>

