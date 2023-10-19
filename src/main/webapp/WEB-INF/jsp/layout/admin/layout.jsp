<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>횡성 민원웹 서비스</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Premium Multipurpose Admin & Dashboard Template" name="description">
<meta content="Themesbrand" name="author">
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

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://kit.fontawesome.com/33c9a6582b.js" crossorigin="anonymous"></script>
<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="/assets/admin/js/custom.js"></script>

<link href="/assets/front/css/jquery-ui.css" rel="stylesheet" type="text/css" media="all">
<script src="/assets/front/js/jquery-ui.js"></script>
<script src="/assets/front/js/jquery-ui-datepicker.js"></script>


<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

</head>
<body data-sidebar="dark">

	<!-- Begin page -->
	<div id="layout-wrapper">
				<tiles:insertAttribute name="header" />

		<!-- Begin page -->

		<div class="vertical-menu">

			<div data-simplebar class="h-100">
				<!--- Sidemenu -->
				<div id="sidebar-menu">
					<!-- Left Menu Start -->
					<ul class="metismenu list-unstyled" id="side-menu">
						<li><a href="/admin/minwon/list" class="waves-effect"> <i class="ti-clipboard"></i> <span>민원 리스트</span>
						</a></li>
						<li><a href="/admin/consult/list" class="waves-effect"> <i class="ti-view-grid"></i> <span>전체 상담 리스트</span>
						</a></li>
						<li><a href="/admin/user/list" class="waves-effect"> <i class="ti-user"></i> <span>담당공무원 관리</span>
						</a></li>
<!-- 						<li><a href="/admin/department/list" class="waves-effect"> <i class="ti-archive"></i> <span>협의 담당부서 관리</span>
						</a></li> -->
						<li><a href="/admin/survey/list" class="waves-effect"> <i class="ti-headphone-alt"></i> <span>만족도 조사 관리</span>
						</a></li>
						<li><a href="/admin/message/list" class="waves-effect"> <i class="ti-email"></i> <span>메세지 발송 내역</span>
						</a></li>
						<li><a href="/admin/status/list" class="waves-effect"> <i class="ti-pie-chart"></i> <span>통계</span>
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
</html>