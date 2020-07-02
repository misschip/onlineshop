	

	function minusQuantity(idString) {
		var quantity = $(`#${idString}`).val();
		if (quantity > 1) {
			--quantity;
		} else {
			quantity = 1;
		}
		console.log(quantity);
		
		$(`#${idString}`).val(quantity);
		
		subSum(quantity, idString);
		

	}
	
	function plusQuantity(idString) {
		var quantity = $(`#${idString}`).val();
		if (quantity < 1) {
			quantity = 1;
		} else {
			++quantity;
		}
		console.log(quantity);
		
		$(`#${idString}`).val(quantity);
		
		subSum(quantity, idString);
	}
	
	// 장바구니 항목마다의 부분 합계
	function subSum(qty, idStr) {
		var num = idStr.substr("quantity-".length);
		var price = $("#pprice-"+num).text();
		var total = price * qty;
		
		$("#subSum-"+num).text(total);
		
		// 특정 항목 개수 변할 때 부분 합계 뿐 아니라 전체 합계도 바로 계산되어 표출
		totalSum();
	}
	
	function totalSum() {
		var totalSum = 0;
		
		// #itemCount는 장바구니의 상품 가지수 값을 갖고 있음
		var count = $("#itemCount").val();
		
		for (i=1; i<=count; i++) {
			totalSum = totalSum + Number($("#subSum-"+ i).text());
		}

		$("#totalSum").text(totalSum);
		
		// checkCart();
	}
	
	
	// 쇼핑카트에 담긴 상품들을 product id와 상품 개수 값을 key - value 쌍으로 담아서 반환함
	function getCart() {
		var count = $("#itemCount").val();
		var data = {};
		
		for (i=1; i<=count; i++) {
			var pid = $("#pid-" + i).val();	// product의 프라이머리키값을 읽어옴
			data[pid] = $("#quantity-" + i).val();	// 장바구니에서 해당 상품 개수
		}
		
		console.log(data);
		console.log(typeof(data));
		return data;
	}
	
	// 장바구니 보기 페이지 열릴 때 오른쪽에 바로 총합계 금액이 계산되어 보이도록
	window.onload = function() {
		totalSum();
	}
	
	// window.beforeunload = checkCart;
	
	function saveCart() {
		var data = getCart();
		
		console.log("function : savaCart() : data : ");
		console.log(data);
		
		$.ajax({
			type: "post",
			url: "/onlineshop/cart?cmd=save",
			data: JSON.stringify(data),
			// contentType: "application/x-www-form-urlencoded; charset=utf-8",
			contentType: "application/json; charset=utf-8",
			dataType: "text"
					
		}).done(function(result){
			if (result == 1) {
				alert("장바구니 저장 완료!");
			}
		}).fail(function(error){
			console.log(error);
		});
	}