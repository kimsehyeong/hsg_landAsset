<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>횡성 민원웹 서비스</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Premium Multipurpose Admin & Dashboard Template" name="description">

<!-- App favicon -->
<link rel="shortcut icon" href="/assets/images/favicon.ico">

<!-- Bootstrap Css -->
<link href="/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css">
<!-- Icons Css -->
<link href="/assets/css/icons.min.css" rel="stylesheet" type="text/css">
<!-- App Css-->
<link href="/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css">

<!-- customizing-->
<link href="/assets/css/style.css" rel="stylesheet" type="text/css">
<!-- User Css-->
<link href="/assets/css/user.css" rel="stylesheet" type="text/css">

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://kit.fontawesome.com/33c9a6582b.js" crossorigin="anonymous"></script>
<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="/assets/admin/js/custom.js"></script>

<link href="/assets/front/css/jquery-ui.css" rel="stylesheet" type="text/css" media="all">
<script src="/assets/front/js/jquery-ui.js"></script>
<!-- <script src="/assets/front/js/jquery-ui-datepicker.js"></script> -->
</head>
<body data-sidebar="dark">

	<div id="layout-wrapper">
		<tiles:insertAttribute name="header" />

		<div class="vertical-menu">
			<div data-simplebar class="h-100">
				<input type="hidden" id="registerNo" value="${registerNoAES}" />
				<!--- Sidemenu -->
				<div id="sidebar-menu">
					<!-- Left Menu Start -->
					<ul class="metismenu list-unstyled" id="side-menu">
						<li><a href="/minwon/{???}/myProcess" class="waves-effect">
								<i class="ti-clipboard"></i>
								<span>나의 토지민원 현황</span>
							</a></li>
						<li><a href="/minwon/{???}/docDisplay" class="waves-effect">
								<i class="ti-view-grid"></i>
								<span>처리문서보기</span>
							</a></li>
						<li><a href="/minwon/{???}/consultReq" class="waves-effect">
								<i class="ti-user"></i>
								<span>상담 신청</span>
							</a></li>
						<li><a href="https://www.hsg.go.kr/www/contents.do?key=743&" class="waves-effect">
								<i class="ti-user"></i>
								<span>토지재산과 블로그</span>
							</a></li>
						<li><a href="https://www.hsg.go.kr/common/intro/covidintro/main.html" class="waves-effect">
								<i class="ti-user"></i>
								<span>횡성군청홈페이지</span>
							</a></li>
					</ul>
				</div>
				<!-- Sidebar -->
			</div>
		</div>
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>

<script>
	$(function() {
		var registerNo = $('#registerNo').val();

		$('a[href^="/minwon/"]').each(function() {
			var href = $(this).attr('href');
			href = href.replace('{???}', registerNo);
			$(this).attr('href', href);
		});
	});
</script>
</html>