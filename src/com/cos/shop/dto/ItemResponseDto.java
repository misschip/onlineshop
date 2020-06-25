package com.cos.shop.dto;

import com.cos.shop.model.Item;
import com.cos.shop.model.Product;

import lombok.Data;

@Data
public class ItemResponseDto {
	private Item item;
	private Product product;
}
