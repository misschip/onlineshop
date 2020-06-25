package com.cos.shop.dto;

import java.util.List;

import com.cos.shop.model.Customer;
import com.cos.shop.model.Orders;

import lombok.Data;

@Data
public class OrdersResponseDto {

	private Orders orders;
	private Customer customer;
	private List<ItemResponseDto> itemDtos;
}
