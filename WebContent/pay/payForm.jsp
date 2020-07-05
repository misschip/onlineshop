<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<!--  결제 관련 소스 코드 -->
<!-- https://www.iamport.kr/ 에 회원가입하고
	아래 [1], [2] 부분은 홈페이지(https://www.iamport.kr/getstarted)에 나오는 걸 가져옴
	삼성카드로 결제되는 것 확인 완료!
-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <!-- [1] -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>

<body>
	<!-- [2] -->
  <script>

  	initStage();
 
  	finalPay('${param.totalPrice}');


  	function initStage() {
		// var cartJson = '${cart}';
		// var principalJson = '${principalJson}';

		
  	}
  	
    function finalPay(totalPrice) {
    	console.log("finalPay 함수가 실행됨");
    	
        var IMP = window.IMP; // 생략가능
        IMP.init('imp19191445'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

        IMP.request_pay({
            pg: 'inicis', // version 1.1.0부터 지원.
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: '주문명:결제테스트',
            amount: totalPrice,	// 결제금액. 최소결제금액 제한으로 10원은 허용 안됨. 테스트 결제라 당일 밤 12시쯤에 다시 환불된다고!
            buyer_email: '${principal.email}',
            buyer_name: '${principal.username}',
            buyer_tel: '${principal.phone}',
            buyer_addr: '${principal.address}',
            buyer_postcode: '${principal.zipNo}',
            m_redirect_url: 'http://localhost:8000/onlineshop/cart?cmd=pay'
        }, function (rsp) {
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                alert('결제가 완료되었습니다.');
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                console.log(msg);	// msg에 표출되는 값들을 db에 저장해둬야 나중에 환불 등의 추후 조치가 가능함
                
                $.ajax({
                	type: "post",
    	    		url: "/onlineshop/orders?cmd=saveOrder",
    	    		// data: "id="+id+"&totalPrice="+totalPrice,
    	    		data: "imp_uid=" + rsp.imp_uid + "&merchant_uid=" + rsp.merchant_uid + "&paid_amount=" + rsp.paid_amount + "&apply_num=" + rsp.apply_num,
    	    		contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	    		dataType: "text"
                	
                }).done(function(result){	// result가 성공적인 orderId값
                	location.href="/onlineshop/orders?cmd=display&id="+result;
                }).fail(function(error) {
                	alert("결제가 비정상적으로 처리됨. 관리자에게 문의 하세요.");
                })
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
            }
            alert(msg);
        });
    }
  	
  </script>

</body>

</html>