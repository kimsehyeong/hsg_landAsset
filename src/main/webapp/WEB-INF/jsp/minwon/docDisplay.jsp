<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>   
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
                           <div class="d-flex justify-content-between mb-1">
                               <h4 class="card-title mb-3">처리사항</h4>
                           </div>
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <colgroup>
                                        <col width="10%">
                                        <col width="45%">
                                        <col width="45%">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>등록일</th>
                                            <th>첨부파일</th>
                                            <th>Comments</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="file" items="${file}">
                                        <tr>
                                            <td style="display: none" ><input id="fileId" value="${file.id}"></td>
                                            <td><fmt:formatDate value="${file.uploadDate}" pattern="yyyy-MM-dd" /></td>
                                            <td><a style="color: blue; cursor: pointer;" onclick="downloadFile('${file.fileName}','${file.filePk}')">${file.fileName}</a></td>
                                            <td>${file.fileComments}</td>
                                        </tr>
									</c:forEach>                                        
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <div class="text-center"><a type="button" class="btn btn-primary waves-effect waves-light" href="/minwon/${registerNo}/myProcess" style="margin: 0 auto;">메인이동</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->

</div>
<form class="downloadForm" method="POST" target='downloadFrame' action="/admin/minwon/file/download">
	<input type="hidden" name="fileName" value="">
	<input type="hidden" name="filePk" value="">
</form>	
<script>
function downloadFile(fileName,filePk) {
	$form = $('.downloadForm');
	$form.find('input[name="fileName"]').val(fileName);
	$form.find('input[name="filePk"]').val(filePk);
	$form.submit();

}


</script>