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
 * The persistent class for the ecommerce_sub_category database table.
 *
 * @author Fahad Hasan
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_sub_category")
public class SubCategory extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String name;
	
	@NotNull
	private String code;
	
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "id")
	@JsonIgnore
	private Category category;
	
	@Transient
	private String categoryId;
	
	@Transient
	private String categoryName;
}
