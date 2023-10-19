<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>

         <div class="modal-content">
             <div class="modal-header">
                 <h5 class="modal-title"></h5>
                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
             </div>
             <div class="modal-body">
			<h4>[민원서비스 첨부파일]</h4>
			<br><br>
			<form id="fileUpload">		
			<input type="hidden" value="${param.registerNo}" name="registerNo">	
			<table id="tech-companies-1" class="table table-type3">
				<thead>
					<tr>
						<th>첨부파일명</th>
						<th class="file-name"><input type="file" name="fileName" ></th>
					</tr>
				</thead>
				<tbody>
					<tr id="file-row" class="hidden" >
						<td>comments</td>
						<td><input class="btn btn-white border-gray height-35" name="comments" style="width: 350px"></td>
					</tr>
				</tbody>
			</table>
			</form>			
			<br>
			<div style="text-align: right;">
				<button class="btn btn-danger" type="button" id="upload" onclick="fileUpload()">upload</button>
				<button class="btn btn-dark close" type="button" data-bs-dismiss="modal" aria-label="Close">닫기</button>
			</div>
<!-- 			<div class="close-btn">
				<i class="mdi mdi-close"></i>
			</div> -->
		</div>
		    <div class="modal-footer">
	</div>
	</div>

<script>
$(function(){
});
function fileUpload() {

	  const fileInput = document.querySelector('input[type="file"]');
	  if (!fileInput.files || !fileInput.files[0]) {
		    alert('파일을 먼저 선택해주세요');
		    return;
		  }
	
	var form = $('#fileUpload')[0];
	 var formData = new FormData(form);
	
	$.ajax({
		url : '/admin/minwon/file/upload',
		type : 'post',
		enctype: 'multipart/form-data', 
		data : formData,
		processData : false,
		 contentType: false,
		dataType : 'json',
		success : function (response){
			if(!response.result) {
				alert(response.msg);
				return false;
				};
				alert(response.msg);
				$("#popup-form").modal("hide");
				location.reload();

		}
	});
}


</script>
