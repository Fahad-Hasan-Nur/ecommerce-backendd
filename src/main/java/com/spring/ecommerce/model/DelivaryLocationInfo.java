package com.spring.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_delivary_location_info database table.
 *
 * @author Fahad Hasan
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_delivary_location_info")
@ToString(callSuper = true)
public class DelivaryLocationInfo extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
		
		@NotNull
		private String city;
		
		@NotNull
		private String area;
		
		@NotNull
		private String street;
		
		private String building;
		
		private String house;
		
		private String postalCode;
		
		private String zip;
		
	}
