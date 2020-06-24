package com.cos.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {
	private int id;
	private String username;
	private String password;
	private String phone;
	private String email;
}
