package com.cos.shop.action.orders;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.model.Item;
import com.cos.shop.model.Orders;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ItemRepository;
import com.cos.shop.repository.OrdersRepository;
import com.cos.shop.util.Script;

public class OrdersSaveOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		
		Customer principal = (Customer)session.getAttribute("principal");
		
		if (principal == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return;
		}
		
		// Map<상품,수량>
		Map<Product,Integer> cart = (Map<Product,Integer>) session.getAttribute("cart");
		
		if (cart == null) {
			Script.getMessage("장바구니 정보가 초기화되었습니다.", response);	// 세션 만료로 정보가 날아가는 경우
			return;
		}
		
		/*
		 cart의 상품 가격과 수량을 곱한 총합계 금액과 바로 앞 화면에서 넘겨받은 totalPrice 값을 비교해서 일치하는지 확인하는 절차가
		 이 위치에 들어오면 좀더 확실한 체크가 될 듯!
		 
		 */
		
		
		String recipient_name = request.getParameter("recipient_name");
		String address = request.getParameter("address");
		String zipno = request.getParameter("zipno");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String payment = request.getParameter("payment");
		
		String totalPriceStr = request.getParameter("totalPrice");
		
		if (recipient_name == null || recipient_name.equals("") ||
			address == null || address.equals("") ||
			zipno == null || zipno.equals("") ||
			phone == null || phone.equals("") ||
			email == null || email.equals("") ||
			payment == null || payment.equals("") ||
			totalPriceStr == null || totalPriceStr.equals("")
		) {
			System.out.println("OrdersSaveOrderAction : 매개값을 받아오지 못했습니다.");
			return;
		}
		
		System.out.println("OrdersSaveOrderAction : payment : " + payment);
		
		int totalPrice = Integer.parseInt(totalPriceStr);
		
		Orders order = Orders.builder()
				.customer_id(principal.getId())
				.phone(phone)
				.email(email)
				.address(address)
				.zipno(zipno)
				.recipient_name(recipient_name)
				.payment(payment)
				.total(totalPrice)
				.status("결제완료")
				.build();
		
		
		/// Orders, Item 테이블에 저장하는 절차
		
		// Orders 테이블에 저장
		OrdersRepository ordersRepository = OrdersRepository.getInstance();
		int orders_id = ordersRepository.save(order);	// orders_id는 Orders 테이블의 id(primary key)값이면서
														// Item 테이블의 orders_id로 외래키로 들어감
		
		if (orders_id == 0 || orders_id == -1) {
			System.out.println("OrdersSaveOrderAction : save(order)에 문제 발생 : orders_id : " + orders_id);
			return;
		}
		
		// Item 테이블에 저장
		ItemRepository itemRepository = ItemRepository.getInstance();
		
		// 장바구니에 든 상품과 상품별 수량 값을 가져와 Item 테이블에 저장
		Iterator<Map.Entry<Product,Integer>> it = cart.entrySet().iterator();
		while (it.hasNext()) {
			Product p = it.next().getKey();	// 상품
			int qty = it.next().getValue();	// 수량
			
			Item item = Item.builder()
					.orders_id(orders_id)
					.product_id(p.getId())
					.quantity(qty)
					.unit_price(p.getPrice())
					.build();
			
			int result = itemRepository.save(item);
			if (result != 1) {
				System.out.println("OrdersSaveOrderAction : save(item)에 문제 발생");
				return;
			}
		}
		
		
		
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("orders_id", orders_id);
		
		// 외부 결제 모듈로 리다이렉트
		RequestDispatcher dis = request.getRequestDispatcher("payForm.jsp");
		dis.forward(request, response);
		
	}

}
