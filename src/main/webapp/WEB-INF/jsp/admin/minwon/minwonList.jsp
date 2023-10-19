<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<div class="main-content">

	<div class="page-content">
		<div class="container-fluid">

			<!-- start page title -->
			<div class="page-title-box">
				<div class="row align-items-center">
					<div class="col-md-12">
						<h6 class="page-title">민원리스트</h6>
						<ol class="breadcrumb m-0">
							<li class="breadcrumb-item">
								<a href="#">홈</a>
							</li>
							<li class="breadcrumb-item">
								<a href="#">민원리스트</a>
							</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- end page title -->



			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<form id="search-form">
								<h4 class="card-title mb-3">접수내역</h4>
								<div class="search-row">
									<ul class="d-flex flex-md-wrap">
										<li class="mb-2">
											접수일
											<input class="btn btn-white border-gray height-35 datepicker" type="text" name="startDate" value="${minwonVO.startDate }">
											<span class="wave"> ~</span>
											<input class="btn btn-white border-gray height-35 datepicker" type="text" name="endDate" value="${minwonVO.endDate }">
										</li>
										<li>
											접수번호
											<input class="btn btn-white border-gray height-35" type="text" name="in_mw_take_no" value="${minwonVO.in_mw_take_no }">
										</li>
										<li>
											민원명
											<input class="btn btn-white border-gray height-35" type="text" name="mw_afr_shtnm" value="${minwonVO.mw_afr_shtnm }">
										</li>
										<li>
											민원인
											<input class="btn btn-white border-gray height-35" type="text" name="in_mw_aplr_nm" value="${minwonVO.in_mw_aplr_nm }">
										</li>
										<li>
											전화번호
											<input class="btn btn-white border-gray height-35" type="text" name="mw_aplct_hpno" value="${minwonVO.mw_aplct_hpno }">
										</li>
										<li>
											담당자
											<input class="btn btn-white border-gray height-35" type="text" name=user_name value="${minwonVO.user_name }">
										</li>
										<button type="submit" class="btn btn-info waves-effect waves-light">검색</button>
									</ul>
								</div>
								<div class="table-responsive">
							    <div style="text-align: right;">
							        <button class="btn btn-warning waves-effect waves-light" onclick="sendMessage2('민원해결(등기촉탁)')">등기촉탁</button>
							        <button class="btn btn-warning waves-effect waves-light" onclick="sendMessage2('민원해결(개발행위허가-원스톱건)')">원스톱건</button>
							    </div>
									<table class="table mb-0" style="white-space: nowrap;">
										<thead>
											<tr>
         								        <th></th>
												<th>일시</th>
												<th>접수번호</th>
												<th>구분</th>
												<th>민원명</th>
												<th>민원인</th>
												<th>전화번호</th>
												<th>담당자</th>
												<th>
													<span>진행사항</span>
													<br />
													<select name="deal_se">
														<option value="">전체</option>
														<option value="00" ${minwonVO.deal_se eq '00'? 'selected':''}>처리중</option>
														<option value="01" ${minwonVO.deal_se eq '01'? 'selected':''}>해결</option>
													</select>
												</th>
												<th>처리기한</th>
												<th>
													접수확인<br />
													<select name="progress">
														<option value="">전체</option>
														<option value="N" ${minwonVO.progress eq 'N'? 'selected':''}>발송전</option>
														<option value="Y" ${minwonVO.progress eq 'Y'? 'selected':''}>발송</option>
													</select>
												</th>
												<th>
													해결<br />
													<select name="progress2">
														<option value="">전체</option>
														<option value="N" ${minwonVO.progress2 eq 'N'? 'selected':''}>발송전</option>
														<option value="Y" ${minwonVO.progress2 eq 'Y'? 'selected':''}>발송</option>
													</select>
												</th>
												<th>
													상담신청사항<br />
													<select name="answer">
														<option value="">전체</option>
														<option value="N" ${minwonVO.answer eq 'N'? 'selected':''}>답변대기</option>
														<option value="Y" ${minwonVO.answer eq 'Y'? 'selected':''}>답변완료</option>
													</select>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${list }">
												<tr>
							  <!-- 체크박스 추가 --><td><input type="checkbox" class="itemCheckbox" data-mw-take-no="${item.mw_take_no}" data-mw-aplct-nm="${item.mw_aplct_nm}" data-mw-aplct-hpno="${item.mw_aplct_hpno}" /></td>
													<td class="date">${item.take_ymd }</td>
													<td>${item.mw_take_no }</td>
													<td>${item.mw_code_cn }</td>
													<td>
														<a onclick="goView('${item.mw_take_no }','${item.mw_aplct_nm}','${item.mw_aplct_hpno}')" style="color: blue; cursor: pointer;">${item.mw_afr_shtnm }</a>
													</td>
													<td>${item.mw_aplct_nm}</td>
													<td>${item.mw_aplct_hpno}</td>
													<td>${item.dpp_usr_nm}</td>
													<td>${item.deal_code_cn}</td>
													<td>${item.real_deal_ymd}</td>
													<td>
														<c:choose>
															<c:when test="${item.progress>0 }">${item.progress }회발송</c:when>
															<c:otherwise>발송전</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${item.progress2>0 }">${item.progress2 }회발송</c:when>
															<c:otherwise>발송전</c:otherwise>
														</c:choose>
													</td>
													<td>${item.commentStatus }
														<c:if test="${item.consult>0 }">
															<c:choose>
																<c:when test="${item.answered == item.consult }">답변 완료</c:when>
																<c:otherwise>답변 미완료</c:otherwise>
															</c:choose>
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</form>
						</div>
					</div>

					<jsp:include page="/WEB-INF/include/paging.jsp" />

				</div>
			</div>

		</div>
		<!-- container-fluid -->
	</div>
	<!-- End Page-content -->

</div>




<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

<script>
	$(function() {
		$('.date').each(function() {
			var dateString = $(this).text();
			var date = moment(dateString, "YYYYMMDD");
			$(this).text(date.format("YYYY-MM-DD"));
		});
	});

	
	function sendMessage2(minwonTitle){
	    if(!confirm("메세지를 전송하시겠습니까?")) return ;

	    var checkboxes = document.getElementsByClassName('itemCheckbox');
	    var checkedRows = [];
	    for (var i = 0; i < checkboxes.length; i++) {
	        if (checkboxes[i].checked) {
	            var mw_take_no = checkboxes[i].getAttribute('data-mw-take-no');  // get mw_take_no
	            var mw_aplct_nm = checkboxes[i].getAttribute('data-mw-aplct-nm');  // get mw_aplct_nm
	            var mw_aplct_hpno = checkboxes[i].getAttribute('data-mw-aplct-hpno');  // get mw_aplct_hpno
	            
	            checkedRows.push({
	                'registerNo': mw_take_no,
	                'receiverName': mw_aplct_nm,
	                'receiverPhone': mw_aplct_hpno,
	    	        'template' : minwonTitle
	            });
	        }
	    }

	    if(checkedRows.length === 0) {
	        alert("선택된 값이 없습니다.");
	        return;
	    }

	    var params = {
	        'checkedRows' : checkedRows  
	    }
	    

	    $.ajax({
	        url : "/admin/minwon/sendMessageList",
	        data : JSON.stringify(checkedRows), 
	        type : "post",
	        contentType: 'application/json',  
	        dataType : "json",
	        success : function(response){
	            alert(response.msg);
	            if(response.result)
	                fnReload();
	        }
	    });
	}
	
	
	
	function goView(registerNo, in_mw_aplr_nm, mw_aplct_hpno) {
		var formattedPhoneNo = mw_aplct_hpno.replace(/-/g, '');

		var encodedRegisterNo = encodeURIComponent(registerNo);
		var encodedMinwonName = encodeURIComponent(in_mw_aplr_nm);
		var encodedPhoneNo = encodeURIComponent(mw_aplct_hpno);

		fnRedirect('/admin/minwon/view?registerNo=' + encodedRegisterNo + '&in_mw_aplr_nm=' + encodedMinwonName + '&mw_aplct_hpno=' + encodedPhoneNo);

		// 		var params = {
		// 			'registerNo' : registerNo,
		// 			'minwonName' : minwonName,
		// 			'minwonPhoneNo' : minwonPhoneNo,
		// 		};

		// 		$.ajax({
		// 			url : "/admin/minwon/view",
		// 			data : params,
		// 			type : "post",
		// 			dataType : "html",
		// 			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		// 			success : function(response){
		// 				console.log("success")
		// 			}
		// 		});
	};
</script>
