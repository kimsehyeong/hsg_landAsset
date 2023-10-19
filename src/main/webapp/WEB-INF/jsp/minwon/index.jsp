<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>




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
                                            <th class="text-white th-type2" colspan="3" id="registerNo">${registerNo}</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>민원명</th>
                                            <td>산지전용(변경)허가</td>
                                            <th>토지 소재지</th>
                                            <td>산지전용(변경)허가</td>                                            
                                        </tr>
                                        <tr>
                                            <th>접수일자</th>
                                            <td>2022-03-29</td>
                                            <th>처리기한</th>
                                            <td>2022-05-31</td>
                                        </tr>
                                        <tr>
                                            <th>처리부서</th>
                                            <td>허가민원과</td>
                                            <th>처리담당자</th>
                                            <td>남주희</td>
                                        </tr>
                                        <tr>
                                            <th>전화번호</th>
                                            <td>033-340-2453</td>
                                            <th>이메일</th>
                                            <td>wngml4722@korea.kr</td>
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
                                        <a href="receipt.html">접수</a>
                                    </li>
                                    <li class="active">
                                        <a href="receipt.html">수령처리</a>
                                    </li>
                                    <li class="active">
                                        <a href="receipt.html">접수확인</a>
                                    </li>
                                    <li class="active">
                                        <a href="receipt.html">보완보정</a>
                                    </li>
                                    <li class="active">
                                        <a href="receipt.html">해결</a>
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

