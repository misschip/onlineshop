package com.cos.shop.model;


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
	private String orders_date;
	private String shipping_address;
	private String recipient_name;
	private String recipient_phone;
	private String payment;
	private int total;
	private String status;
}
