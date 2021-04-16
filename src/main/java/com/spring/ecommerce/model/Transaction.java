package com.spring.ecommerce.model;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_transaction database table.
 *
 * @author Fahad Hasan
 * @since 2021-03-11
 */
@Data
@Entity
@Table(name="ecommerce_dealer_transaction")
@ToString(callSuper = true)
public class Transaction extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;

	
	@NotNull
	private String transactionId;
	
	@NotNull
	private String accountNumber;
	
	@NotNull
	private String paymentMethod;
	
	@NotNull
	private long paid;
	
	@NotNull
	private long due;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	@JsonIgnore
	private User user;
	
	@Transient
	private String userId;
	
	@NotNull
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "requisition_id",referencedColumnName = "id")
	@JsonIgnore
	private Requisition requisition;
	
	@Transient
	private String requisitionId;
	
}


