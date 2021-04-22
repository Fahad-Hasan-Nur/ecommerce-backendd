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
 * The persistent class for the ecommerce_user_cart_product database table.
 *
 * @author Fahad Hasan
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ecommerce_user_cart_product")
@ToString(callSuper = true)
public class UserCartProduct extends AbstractPersistableEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	private User user;

	@Transient
	private String userId;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@JsonIgnore
	private Product product;

	@Transient
	private String productId;

	@Transient
	private String productName;

	@ManyToOne
	@JoinColumn(name = "variation_id", referencedColumnName = "id")
	@JsonIgnore
	private ProductVariation variation;

	@Transient
	private String variationId;

	@Transient
	private String variationName;

	@Column(nullable = false)
	@NotNull
	private long totalCost;

	@Column(nullable = false)
	@NotNull
	private int quantity;

	private String color;

	@Column(nullable = false)
	@NotNull
	private String status;
	
	private String orderId;


}