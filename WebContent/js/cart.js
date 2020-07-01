

	function minusQuantity() {
		var quantity = $("#quantity").val();
		if (quantity > 1) {
			--quantity;
		} else {
			quantity = 1;
		}
		console.log(quantity);
		
		$("#quantity").val(quantity);
		
		calcTotal(quantity);
	}
	
	function plusQuantity() {
		var quantity = $("#quantity").val();
		if (quantity < 1) {
			quantity = 1;
		} else {
			++quantity;
		}
		console.log(quantity);
		
		$("#quantity").val(quantity);
		
		calcTotal(quantity);
	}
	
	function calcTotal(qty) {
		var price = $("#pprice").text();
		var total = price * qty;
		$("#totalPrice").text(total);
	}
	