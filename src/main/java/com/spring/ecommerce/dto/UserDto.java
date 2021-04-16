package com.spring.ecommerce.dto;

import java.util.Date;


import lombok.Data;

@Data
public class UserDto {
	private String id;
	private String name;
	private String email;
	private Date birthDate;
	private String gender;
	private boolean active;
	private String imageId;
	private String imageName;
	private String phoneNumber;
	private String role;
	private String type;
	private String tinId;
	private String nidId;
	private boolean verified;
	private String permanentAddress;
	private String presentAddress;
	private String shopAddress;
}
