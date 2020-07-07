package com.cos.shop.model;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
	private int id;
	private int customer_id;
	private Timestamp orders_date;
	private String recipient_name;
	private String phone;
	private String email;
	private String address;
	private String zipno;
	private String payment;
	private int total;
	private String status;	// 결제완료, 배송중, 배송완료. enum 타입으로?
}
