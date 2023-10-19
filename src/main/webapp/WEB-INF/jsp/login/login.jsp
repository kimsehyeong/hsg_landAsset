<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>
<!DOCTYPE html>
<html lang="en">
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

</head>
<body>

<div class="container login mt-3">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6 col-xl-4">
            <div class="card overflow-hidden">
                <div class="" style="border-bottom: 1px solid #ccc;">
                    <div class="text-primary text-center p-4">
                        <a href="index.html" class="logo logo-admin">
                            <img src="/assets/images/login-logo.png" height="" alt="logo">
                        </a>
                    </div>
                </div>

                <div class="card-body p-4">
                    <div class="p-3">
                       <h5 class="text-center">내 민원처리 바로 확인</h5>
                        <form class="mt-4" id="loginForm">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="id" placeholder="아이디" name="id">
                            </div>

                            <div class="mb-3">
                                <input type="password" class="form-control" id="pwd"  placeholder="비밀번호" name="pwd">
                            </div>

                            <div class="mb-3 row">
                                <div class="col-sm-12">
                                    <button type="button" class="btn btn-info w-100 waves-effect waves-light" onclick="login()">로그인</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>

            </div>


        </div>
    </div>
</div>



<script>
$(function(){
	$("#id").focus();
	
	$("#loginForm").find("input").keyup(function(k){
		if(k.keyCode==13){
			login();
		}
	});
	
});

function login() {
	
	var params = $("#loginForm").serialize();
	$.ajax({
		url : "/admin/login",
		type : 'post',
		data : params,
		dataType : 'json',
		success : function(response) {
			console.log(response);
			if(!response.result) alert(response.msg);
			else fnRedirect(response.redirect);
		}
	});
}

</script>

</body>
</html>
