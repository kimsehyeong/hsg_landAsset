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
                        <h6 class="page-title">협의담당부서 관리</h6>
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="#">홈</a></li>
                            <li class="breadcrumb-item"><a href="#">협의담당부서 관리</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- end page title -->






            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title mb-3">협의내용 관리</h4>
                            <div class="mb-2" style="position: relative;text-align: right;">
                                <button type="button" class="btn btn-success waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".modal1">등록</button>
                                <button type="button" class="btn btn-primary waves-effect waves-light" onclick="location.href='/admin/department/list'">목록</button>
                            </div>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <colgroup>
                                        <col width="5%">
                                        <col width="35%">
                                        <col width="10%">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>협의내용</th>
                                            <th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>가축분뇨배출시설설치허가신고</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light">삭제</button></td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>가축분뇨배출시설변경허가신고</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light">삭제</button></td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>개인하수처리시설설치신고</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light">삭제</button></td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>배수설비설치</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light">삭제</button></td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>오수처리시설및단독정화조</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light">삭제</button></td>
                                        </tr>
                                        <tr>
                                            <td>6</td>
                                            <td>유해위험방지계획서</td>
                                            <td><button type="button" class="btn btn-dark waves-effect waves-light">삭제</button></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">«</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">»</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="modal modal1" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-modal="true" style="display: none;">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">등록</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive">
                                <table class="table mb-0 table-type2">
                                    <tbody>
                                        <tr>
                                            <th>협의내용</th>
                                            <td>
                                                <input class="btn btn-white border-gray w-100 height-35" type="text">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary waves-effect waves-light">확인</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->




</div>
<!-- end main content-->

<!-- END layout-wrapper -->