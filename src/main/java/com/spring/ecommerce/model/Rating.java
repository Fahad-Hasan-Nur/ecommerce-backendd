package com.spring.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_rating database table.
 *
 * @author Fahad Hasan
 * @since 2021-01-31
 */
@Data
@Entity
@Table(name="ecommerce_rating")
@ToString(callSuper = true)
public class Rating {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Setter(value = AccessLevel.PRIVATE)
	@Column(updatable = false, nullable = false)
	String id;
	
	@NotNull
	private String productId;
	
	@NotNull
	private String userId;
	
	@NotNull
	private int ratingValue;
	
}
