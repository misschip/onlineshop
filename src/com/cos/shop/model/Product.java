package com.cos.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private int id;
	private int category_id;
	private String name;
	private String description;
	private int price;
	private String image1;
	private String image2;
	private String image3;
}
