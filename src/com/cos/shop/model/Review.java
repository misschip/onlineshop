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
public class Review {
	private int id;
	private int product_id;
	private int customer_id;
	private double rating;
	private String title;
	private String commentary;
	private Timestamp write_time;
}
