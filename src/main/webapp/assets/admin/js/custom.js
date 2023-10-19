$(document).ready(function(){

	$('.enrollment').click(function(){  
		$('.enrollment_layer_pop').css('display', 'block');
	});
	$('.id_pop').click(function(){  
		$('.id_layer_pop').css('display', 'block');
	});
	$('.survey_pop').click(function(){  
		$('.survey_layer_pop').css('display', 'block');
	});
//	$('.list_pop').click(function(){  
//		$('.list_layer_pop').css('display', 'block');
//	});
//	$('.list2_pop').click(function(){  
//		$('.list2_layer_pop').css('display', 'block');
//	});
	$('.charge_pop').click(function(){  
		$('.charge_layer_pop').css('display', 'block');
	});
	$('.consult_pop').click(function(){  
		$('.consult_layer_pop').css('display', 'block');
		$('.hide_con').css('display', 'block');
		$('.write_con').css('display', 'none');
	});
	$('.close').click(function(){  
		$('.layer_pop').css('display', 'none');
	});

	$('.write_btn').click(function(){  
		$('.consult_layer_pop').css('display', 'block');
		//$('.hide_con').css('display', 'none');
		$('.write_con').css('display', 'block');
	});

});

//ajax 에러 처리
var ajaxErrorFunc = function(req, status, error){
	console.log("code = "+ req.status); 
}
//정규식
var userIdCheck = RegExp(/^[a-zA-Z0-9_]{4,12}$/);
//var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
//var nameCheck = RegExp(/^[가-힣]{2,6}$/);
//var nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
//var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
//v/ar birthdayCheck = RegExp(/^(19|20)[0-9]{2}(0[1-9]|1[1-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
//var phonNumberCheck = RegExp(/^01[0179][0-9]{7,8}$/);

function activeMenu(str){
	$a = $(".menu").find("a[href='"+str+"']");
	$a.closest("li").addClass("active");
}

//새로고침
function fnReload(){
	location.reload();
}

//리다이렉트
function fnRedirect(str){
	location.href = str;
}

//폼 입력 확인
function fnValidate($form){
	var valid = true;
	$form.find("input[required]").each(function(){
		if(!$(this).val()){
			var text = $(this).closest("div").find("label").text();
			alert(text+"을(를) 입력해주세요");
			valid = false;
			$(this).focus();
			return false;
		}
	});
	
	$form.find("select[required]").each(function(){
		if(!$(this).val()){
			var text = $(this).closest("div").find("label").text();
			alert(text+"을(를) 선택해주세요");
			valid = false;
			$(this).focus();
			return false;
		}
	});
	
	return valid;
}

//페이지 이동
function fnGoPage(page){
	
	var uri = window.location.pathname;
	var nParams = "";
	var params = window.location.search.substring(1);
	var paramArray = params.split("&");
	for(var i=0; i<paramArray.length; i++){
		if(paramArray[i].split("=")[0] != "page"){
			if(i != 0){
				nParams += "&";
			}
			nParams += paramArray[i];
		}
	}
	
	if(nParams != "") nParams += "&";
	nParams+="page="+page;
	uri = uri+"?"+nParams;
	location.href=uri;
}

//이전페이지 파라미터
function fnGetPrevParmas(ex){
	var arr = ex.split(",");
	var nParams = "";
	var params = window.location.search.substring(1);
	var paramArray = params.split("&");
	for(var i=0; i<paramArray.length; i++){
		var flag = true;
		for(j=0; j<arr.length; j++){
			if(paramArray[i].split("=")[0] == arr[j]){
				flag=false;
				break;
			}
		}
		if(flag){
			if(i != 0 && nParams != ""){
				nParams += "&";
			}
			nParams += paramArray[i];
		}
	}
	return nParams;
}

function fnFileDown(f){
	var downFrm = document.downloadForm;
	downFrm.fileId.value = f;
	downFrm.target = "downloadFrame";
	downFrm.submit();
}
