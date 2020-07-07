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
            buyer_postcode: '123-456',
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
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
            }
            alert(msg);
        });
    }
    
    
    
    function shippingAddr() {
    	if ($("#checkAddr").is(":checked") == true) {	// 체크박스 체크된 경우
    		console.log("주소지와 배송지 동일!");
    		console.log($("#checkAddr").is(":checked"));
    		
    		$.ajax({
    			type: "get",
    			url: "/onlineshop/customer?cmd=getPrincipal",
    			data: "",
    			contentType: "application/x-www-form-urlencoded; charset=utf-8",
    			// dataType: "application/json; charset=utf-8"
    			dataType: "text"	// application/json으로 하면 parsererror가 발생함에 주의
    				
    		}).done(function(result){
    			var customer = JSON.parse(result);
    			
    			$("#username").val(customer['username']);
        		$("#address").val(customer['address']);
        		$("#zipno").val(customer['zipNo']);
        		$("#phone").val(customer['phone']);
        		$("#email").val(customer['email']);
    		}).fail(function(error){
    			console.log("ajax 실행 실패");
    			console.log(error);
    		});
    		
    	} else {			// 체크 안된 경우
    		console.log("주소지와 배송지 다름!");
    		console.log($("#checkAddr").is(":checked"));
    		
    		$("#username").val("");
    		$("#address").val("");
    		$("#zipno").val("");
    		$("#phone").val("");
    		$("#email").val("");
    	}
    }
    
    
    function syncWithHiddenField() {
    	var payType = $("#payType").val();
    	$("#payment").val(payType);
    	console.log(payType);
    }