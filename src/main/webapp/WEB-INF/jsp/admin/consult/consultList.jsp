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
						<h6 class="page-title">전체 상담 리스트</h6>
						<ol class="breadcrumb m-0">
							<li class="breadcrumb-item"><a href="#">홈</a></li>
							<li class="breadcrumb-item"><a href="#">전체 상담 리스트</a></li>
						</ol>
					</div>
				</div>
			</div>
			<!-- end page title -->


			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title mb-3">상담 리스트</h4>
							<div class="table-responsive">
								<table class="table mb-0">
									<thead>
										<tr>
											<th>번호</th>
											<th>등록일</th>
											<th>접수번호</th>
											<th>민원명</th>
											<th>접수내역</th>
											<th>진행내역</th>
											<th>내용</th>
											<th>답변안내</th>
											<th>상태</th>
											<th>답변</th>
											<th>삭제</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="rownum" value="${pageVO.rownum }" />
										<c:forEach var="list" items="${list}">
											<tr>
												<td>${rownum}</td>
												<td>
													<fmt:formatDate value="${list.consultDate}" pattern="yyyy-MM-dd" />
												</td>
												<td>${list.registerNo }</td>
												<td>건축 신고</td>
												<td>
													<button type="button" class="btn btn-info waves-effect waves-light" onclick="consultDetail('${list.registerNo}')">보기</button>
												</td>
												<td>
													<button type="button" class="btn btn-info waves-effect waves-light" onclick="consultProgress('${list.registerNo}')">보기</button>
												</td>
												<td>${list.consultInfo}</td>
												<td>${list.answerType }</td>
												<td>${list.consultStatus}</td>
												<td>
													<button type="button" class="btn btn-danger waves-effect waves-light" onclick="consultComment('${list.consultId}','${list.registerNo }')">답변</button>
												</td>
												<td>
													<button type="button" class="btn btn-dark waves-effect waves-light" onclick="deleteConsult('${list.consultId}','${list.registerNo }')">삭제</button>
												</td>
											</tr>
											<c:set var="rownum" value="${rownum-1 }" />
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>
					</div>

					<jsp:include page="/WEB-INF/include/paging.jsp" />

				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Page-content -->

<div class="modal fade" id="popup-form1" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>
<div class="modal fade" id="popup-form2" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>
<div class="modal fade" id="popup-form3" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-center"></div>
</div>
<script>
	$(function() {

	});

	function consultDetail(registerNo) {
		var params = {
			'registerNo' : registerNo
		};

		$.ajax({
			url : "/admin/consult/detail",
			type : "post",
			data : params,
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				$("#popup-form1").find(".modal-dialog").html(response);
				$("#popup-form1").modal("show");
			}

		});
	}

	function consultProgress(registerNo) {
		var params = {
			'registerNo' : registerNo
		};

		$.ajax({
			url : "/admin/consult/progress",
			type : "post",
			data : params,
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				$("#popup-form2").find(".modal-dialog").html(response);
				$("#popup-form2").modal("show");

			}

		});
	}

	function consultComment(consultId, registerNo) {
		var params = {
			'consultId' : consultId,
			'registerNo' : registerNo
		};

		$.ajax({
			url : "/admin/consult/comment",
			type : "post",
			data : params,
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				$("#popup-form3").find(".modal-dialog").html(response);
				$("#popup-form3").modal("show");

			}

		});
	}

	function deleteConsult(consultId, registerNo) {

		if (!confirm("상담삭제하시겠습니까?"))
			return;

		var params = {
			'registerNo' : registerNo,
			'consultId' : consultId,
		};

		console.log(params)

		$.ajax({
			url : "/admin/consult/delete",
			type : "post",
			data : params,
			dataType : "json",
			success : function(e) {
				console.log(e);
				alert(e.msg);
				if (!e.result)
					return false;
				location.reload();
			}

		});
	}
</script>
