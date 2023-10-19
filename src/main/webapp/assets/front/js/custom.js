$(document).ready(function(){

	/*$('.menu_bar').click(function(){  
		$('.nav-list').toggle();
	});

	$('.menu li').click(function(){
		var href=$(this).find('a').attr('href');  
		location.href=href;
	});*/

		var url = location.href;
		var getAr0 = url.indexOf("main");
	    var getAr1 = url.indexOf("ing");
	    var getAr2 = url.indexOf("advice");
	    if(getAr0 != -1) {
	        $("#main").addClass("active");
	    }
	    if(getAr1 != -1) {
	        $("#ing").addClass("active");
	    }
	    if(getAr2 != -1) {
	        $("#advice").addClass("active");
	    }

	$('.survey_pop').click(function(){  
		$('.survey_layer_pop').css('display', 'block');
	});
	$('.close').click(function(){  
		$('.layer_pop').css('display', 'none');
	});

});

//새로고침
function fnReload(){
	location.reload();
}

// 리다이렉트
function fnRedirect(str){
	location.href = str;
}