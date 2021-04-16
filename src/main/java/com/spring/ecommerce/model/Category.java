package com.spring.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_category database table.
 *
 * @author Fahad Hasan
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_category")
@ToString(callSuper = true)
public class Category extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String name;
	
	private String description;
	
}
