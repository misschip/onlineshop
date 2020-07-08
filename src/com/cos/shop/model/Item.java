package com.cos.shop.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
	private int id;
	private int orders_id;
	private int product_id;
	private int quantity;
	private int unit_price;
	// private int total;	// 삭제한 필드
}
