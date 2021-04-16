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
@Table(name="ecommerce_product")
@ToString(callSuper = true)
public class Product extends AbstractPersistableEntity{

		private static final long serialVersionUID = 1L;
			
			@Column(nullable = false)
			@NotNull
			private String name;
			
			@NotNull
			private String code;
			
			@ManyToOne
			@JoinColumn(name = "brand_id",referencedColumnName = "id")
			@JsonIgnore
			private Brand brand;
			
			@Transient
			private String brandId;
			
			@Transient
			private String brandName;
			
			@ManyToOne
			@JoinColumn(name = "category_id",referencedColumnName = "id")
			@JsonIgnore
			private Category category;
			
			@Transient
			private String categoryId;
			
			@Transient
			private String categoryName;
			
			@ManyToOne
			@JoinColumn(name = "sub_category_id",referencedColumnName = "id")
			@JsonIgnore
			private SubCategory subCategory;
			
			@Transient
			private String subCategoryId;
			
			@Transient
			private String subCategoryName;
			
			@ManyToOne
			@JoinColumn(name = "image_id",referencedColumnName = "id")
			@JsonIgnore
			private Image image;
			
			@Transient
			private String imageName;
			
			@Transient
			private String imageId;
			
			@NotNull
			private String[] color;
			
		}

