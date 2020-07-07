package com.cos.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayResult {
	private int id;
	private int orders_id;
	private String imp_uid;
	private String merchant_uid;
	private int paid_amount;
	private String apply_num;
}
