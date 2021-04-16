package com.spring.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_product database table.
 *
 * @author Fahad Hasan
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_dealer_requisition")
@ToString(callSuper = true)
public class Requisition extends AbstractPersistableEntity {
	private static final long serialVersionUID = 1L;


	@Column(nullable = false)
	@NotNull
	private long totalCost;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	@JsonIgnore
	private User user;
	
	@Transient
	private String userId;
	
	@NotNull
	private String status;
	
	
}

