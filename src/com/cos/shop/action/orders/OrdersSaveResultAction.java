package com.cos.shop.action.orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.PayResult;
import com.cos.shop.repository.PayResultRepository;
import com.cos.shop.util.Script;

public class OrdersSaveResultAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// "imp_uid=" + rsp.imp_uid + "&merchant_uid=" + rsp.merchant_uid
		// "&paid_amount=" + rsp.paid_amount + "&apply_num=" + rsp.apply_num
		
		String orders_id_str = request.getParameter("orders_id");
		String imp_uid = request.getParameter("imp_uid");
		String merchant_uid = request.getParameter("merchant_uid");
		String paid_amount_str = request.getParameter("paid_amount");
		String apply_num = request.getParameter("apply_num");
		
		if (orders_id_str == null || orders_id_str.equals("") ||
			imp_uid == null || imp_uid.equals("") ||
			merchant_uid == null || merchant_uid.equals("") ||
			paid_amount_str == null || paid_amount_str.equals("") ||
			apply_num == null || apply_num.equals("")
		) {
			Script.getMessage("OrdersSaveResultAction: 결제가 처리되지 못했습니다(필수 4개 파라메터를 받지 못함)", response);
			System.out.println("OrdersSaveResultAction : 결제가 처리되지 못했습니다(필수 4개 파라메터를 받지 못함)");
			System.out.println("OrdersSaveResultAction : orders_id : " + orders_id_str);
			System.out.println("OrdersSaveResultAction : merchant_uid : " + merchant_uid);
		}
		
		int orders_id = Integer.parseInt(orders_id_str);
		int paid_amount = Integer.parseInt(paid_amount_str);
		
		PayResult payResult = PayResult.builder()
					.orders_id(orders_id)		// 주문id - Orders 테이블의 id(primary)
					.imp_uid(imp_uid)			// 고유 id
					.merchant_uid(merchant_uid)	// 구매상점 id
					.paid_amount(paid_amount)	// 결제금액
					.apply_num(apply_num)		// 결제 승인번호
					.build();
					
		PayResultRepository payResultRepository = PayResultRepository.getInstance();
		int result = payResultRepository.save(payResult);
		
		if (result == 1) {
			Script.outText("결제완료", response);
			return;
		}
		
		
		Script.getMessage("에러로 결제가 완료되지 못했습니다", response);

	}

}
