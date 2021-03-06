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
public class Customer {
	private int id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String address;
	private int zipNo;
	private Timestamp registerDate;
}
