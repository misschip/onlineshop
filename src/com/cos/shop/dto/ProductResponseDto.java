package com.cos.shop.dto;

import com.cos.shop.model.Category;
import com.cos.shop.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
	private Product product;
	private Category category;
}
