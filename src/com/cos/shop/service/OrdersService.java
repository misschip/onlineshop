package com.cos.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.cos.shop.dto.ItemResponseDto;
import com.cos.shop.dto.OrdersResponseDto;
import com.cos.shop.model.Item;
import com.cos.shop.model.Orders;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ItemRepository;
import com.cos.shop.repository.OrdersRepository;
import com.cos.shop.repository.ProductRepository;

public class OrdersService {
	private static final String TAG = "OrdersService : ";

	private static OrdersService instance = new OrdersService();
	private OrdersService() {}
	public static OrdersService getInstance() {
		return instance;
	}
	
	
	public List<OrdersResponseDto> ordersDtosByCustomer(int customer_id) {
		List<OrdersResponseDto> ordersDtos = new ArrayList<>();
		
		OrdersRepository ordersRepository = OrdersRepository.getInstance();
		ItemRepository itemRepository = ItemRepository.getInstance();
		ProductRepository productRepository = ProductRepository.getInstance();
		
		
		List<Orders> ordersList = ordersRepository.findByCustomer(customer_id);
		
		for (Orders order : ordersList) {
			OrdersResponseDto ordersDto = new OrdersResponseDto();
			ordersDto.setOrders(order);
			
			List<Item> items = itemRepository.findByOrdersId(order.getId());
			List<ItemResponseDto> itemDtos = new ArrayList<>();
			
			for (Item item : items) {
				ItemResponseDto itemDto = new ItemResponseDto();
				itemDto.setItem(item);
				
				Product product = productRepository.findById(item.getProduct_id());
				
				itemDto.setProduct(product);
				
				itemDtos.add(itemDto);
			}
			
			ordersDto.setItemDtos(itemDtos);
			ordersDtos.add(ordersDto);
		}
		
		return ordersDtos;
	}
			
}
