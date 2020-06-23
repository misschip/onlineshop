<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 부트스트랩의 list group 기능 --%>
<%-- https://getbootstrap.com/docs/4.0/components/list-group/ --%>
<%-- 부트스트랩의 list group 기능을 활용하여 지마켓의 전체 카테고리 메뉴 보여주기 기능을 시도해 봄 --%>
<%-- yes24의 카테고리 메뉴 방식이 좀 더 구현하기 쉬울 듯! --%>

<%@ include file="../include/header.jsp" %>


<div class="row">
  <div class="col-sm-3">
  	<div class="card-header rounded-0" data-toggle="collapse" data-target="#list-tab" style="cursor: pointer; background-color: navy; color:white; font-size:large">카테고리</div>
    <div class="list-group collapse rounded-0" id="list-tab" role="tablist">
      <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-home" role="tab" aria-controls="home">테스트</a>
      <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-profile" role="tab" aria-controls="profile">의류</a>
      <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-messages" role="tab" aria-controls="messages">식품/농산물</a>
      <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-settings" role="tab" aria-controls="settings">컴퓨터</a>

    </div>
  </div>
  <div class="col-sm-2">
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list"></div>
      <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list"><a href="#">여성의류</a></div>
      <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list"><a href="#">과일</a><br/><a href="#">통조림</a></div>
      <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list"><a href="#">노트북</a><br/><a href="#">데스크톱</a></div>
    </div>
  </div>
  <div class="col-sm-7">
  </div>
</div>


<%@ include file="../include/footer.jsp" %>