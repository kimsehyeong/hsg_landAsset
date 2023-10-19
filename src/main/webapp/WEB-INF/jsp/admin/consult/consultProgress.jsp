<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>


<!--진행내역모달 -->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">접수내역</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive">
                                <table class="table mb-0">
                                   <thead>
                                       <tr>
                                           <th>작업종류</th>
                                           <th>담당자</th>
                                           <th>부서</th>
                                           <th>일시</th>
                                           <th>전화번호</th>
                                           <th>이메일</th>
                                       </tr>
                                   </thead>
                                    <tbody>
                                    	<c:forEach var="item" items="${progress }">
                                       <tr>
                                           <td>${item.bpm_step_nm}</td>
                                           <td>${item.user_name }</td>
                                           <td>${item.dept_name }</td>
                                           <td>${item.step_end_td }</td>
                                           <td>${item.tel}</td>
                                           <td>${item.email}</td>
                                       </tr>
                                       </c:forEach>
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
                <!-- /.modal-dialog -->
