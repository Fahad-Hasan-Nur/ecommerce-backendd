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

/**
 * The persistent class for the ecommerce_variation database table.
 *
 * @author Fahad Hasan
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_product_variation")
public class ProductVariation extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@NotNull
	private String name;
	
	@Column(nullable = false)
	@NotNull
	private double price;
	
	@Column(nullable = false)
	@NotNull
	private int quantity;
	
	@Column(nullable = false)
	@NotNull
	private int discount;
		
	@ManyToOne
	@JoinColumn(name = "product_id",referencedColumnName = "id")
	@JsonIgnore
	private Product product;
	
	@Transient
	private String productId;
	
	@Transient
	private String productName;
	
	
}
