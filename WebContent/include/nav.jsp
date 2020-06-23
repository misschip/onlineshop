<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    

<div class="row">
  <div class="col-sm-3">
  	<div class="card-header rounded-0" data-toggle="collapse" data-target="#list-tab" style="cursor: pointer; background-color: navy; color:white; font-size:large">카테고리</div>
    <div class="list-group collapse rounded-0 show" id="list-tab" role="tablist">
    	<%-- #list-home, #list-profile 등이 가리키는 부분의 코드들은 아래 대분류 카테고리 클릭시 ajax로 동적으로 생성해야 --%>
      <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-home" role="tab">생활용품</a>
      <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-profile" role="tab">의류</a>
      <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-messages" role="tab">식품/농산물</a>
      <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-settings" role="tab">컴퓨터</a>

    </div>
  </div>
  <%-- 첫번째 요소가 기본적으로 표출되도록 할 건지 어떨지 결정 후 기술적인 부분 처리는 아래 링크 참조
  		https://getbootstrap.com/docs/4.0/components/collapse/#accessibility
   --%>
  <div class="col-sm-2">
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="list-home" role="tabpanel">
      			<ul class="list-group">
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">가구</a>
      				<span class="badge badge-primary badge-pill">4</span>
      				</li>
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">문구</a>
      				<span class="badge badge-primary badge-pill">2</span>
      				</li>
      			</ul>
      </div>
      <div class="tab-pane fade" id="list-profile" role="tabpanel">
      			<ul class="list-group">
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">여성의류</a>
      				<span class="badge badge-primary badge-pill">15</span>
      				</li>
      			</ul>
      </div>
      <div class="tab-pane fade" id="list-messages" role="tabpanel">
      			<ul class="list-group">
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">과일</a>
      				<span class="badge badge-primary badge-pill">12</span>
      				</li>
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">통조림</a>
      				<span class="badge badge-primary badge-pill">5</span>
      				</li>
      			</ul>
      	</div>
      <div class="tab-pane fade" id="list-settings" role="tabpanel">
      			<ul class="list-group">
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">노트북</a>
      				<span class="badge badge-primary badge-pill">3</span>
      				</li>
      				<li class="list-group-item d-flex justify-content-between align-items-center">
      				<a href="#">데스크톱</a>
      				<span class="badge badge-primary badge-pill">2</span>
      				</li>
      			</ul>
      </div>
    </div>
  </div>
  <div class="col-sm-7">
  </div>
</div>
    