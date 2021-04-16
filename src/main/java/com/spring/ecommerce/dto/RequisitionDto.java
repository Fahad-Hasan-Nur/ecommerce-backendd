package com.spring.ecommerce.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RequisitionDto {
	
	private String id;
	private long totalCost;
	private String userId;
	private String status;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	
}

