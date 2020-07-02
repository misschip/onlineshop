// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";


var isCheckedUsername = false;


// ------------------- juso.go.kr 라이브러리 함수 (시작) ----------------
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/onlineshop/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr, zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		// document.form.roadFullAddr.value = roadFullAddr;
		var tfAddress = document.querySelector("#address");
		tfAddress.value = roadFullAddr;
		
		// input type="hidden" 필드에 우편번호(zipNo)를 넘기면 될 듯
}

//------------------- juso.go.kr 라이브러리 함수 (끝) ----------------


//------------------- 중복체크 함수 (시작) ----------------
function validate() {
	// alert('validate() 실행');
	if (!isCheckedUsername) {
		alert('username 중복체크를 실행해 주세요');
	}
	return isCheckedUsername;
}

// 데이터베이스에 ajax 요청을 해서 중복 username이 있는지 확인함
// 있으면 1, 없으면 0, 오류가 나면 -1을 리턴하고 아래 result 매개값이 이를 받아옴
function usernameCheck() {
	var tfUsername = $('#username').val();
	// alert(tfUsername);	// 모든 걸 String으로만 보여줌
	console.log(tfUsername);	// 객체 타입도 대응 가능한 방법

	$.ajax({
		type: 'get',
		url: `/onlineshop/customer?cmd=usernameCheck&username=${tfUsername}`
		// ajax가 이렇게 호출함으로써 UsersUsernameCheckAction 객체가 생성 및 실행

	}).done(function(result){
		console.log(result);
		if (result == 1) {
			alert('아이디가 중복되었습니다');
		} else if (result == 0) {
			alert('사용가능한 아이디입니다');
			isCheckedUsername = true;
		} else {	// 이 부분은 나중에 배포시에는 삭제할 부분임
			console.log('develop : 서버오류');
		}
	}).fail(function(error){
		console.log(error);
	});
	
}
//------------------- 중복체크 함수 (끝) ----------------

// JSP 파일 내에 <script></script> 안에 들어 있던 자바스크립트 소스코드를 이렇게 .js 파일로 분리시키고 나면
// EL 표현식을 사용한 경우는 값이 바로 넘어오지 않는다.
// 따라서 js 함수 호출시 매개값으로 넘겨주는 방식으로 수정을 해줘야 함