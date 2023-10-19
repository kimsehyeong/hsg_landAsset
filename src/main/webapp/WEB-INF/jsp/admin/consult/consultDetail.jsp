<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-taglib.jspf"%>



<!--접수내역 모달 -->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">내용</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <tbody>
                                       <tr>
                                            <th>민원명</th>
                                            <td colspan="3">${minwon.mw_afr_nm }</td>
                                        </tr>
                                        <tr>
                                            <th>접수번호</th>
                                            <td colspan="3">${minwon.mw_take_no}</td>
                                        </tr>
                                        <tr>
                                            <th>접수일자</th>
                                            <td>${minwon.take_ymd }</td>
                                            <th>처리기한</th>
                                            <td>${minwon.deal_plan_ymd }</td>
                                        </tr>
                                        <tr>
                                            <th>접수부서명</th>
                                            <td>${minwon.take_dep_nm }</td>
                                            <th>접수담당자</th>
                                            <td>${minwon.take_stf_nm }1</td>
                                        </tr>
                                        <tr>
                                            <th>전화번호</th>
                                            <td>${minwon.take_sft_nm_tel}</td>
                                            <th>이메일</th>
                                            <td>${minwon.take_stf_nm_emai}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary waves-effect waves-light">확인</button>
                        </div>
                    </div>
