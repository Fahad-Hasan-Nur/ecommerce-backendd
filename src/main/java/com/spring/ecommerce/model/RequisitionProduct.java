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
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_requisition_product")
@ToString(callSuper = true)
public class RequisitionProduct extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
		

		
		@ManyToOne
		@JoinColumn(name = "product_id",referencedColumnName = "id")
		@JsonIgnore
		private Product product;
		
		@Transient
		private String productId;
		
		@Transient
		private String productName;
		
		@ManyToOne
		@JoinColumn(name = "variation_id",referencedColumnName = "id")
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
		private int cartoonSize;
		
		@Column(nullable = false)
		@NotNull
		private int cartoonPerLot;
		
		@ManyToOne
		@JoinColumn(name = "requisition_id",referencedColumnName = "id")
		@JsonIgnore
		private Requisition requisition;
		
		@Transient
		private String requisitionId;
		
	}
