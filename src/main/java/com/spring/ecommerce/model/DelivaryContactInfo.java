package com.spring.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_delivary_contact_info database table.
 *
 * @author Fahad Hasan
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_delivary_contact_info")
@ToString(callSuper = true)
public class DelivaryContactInfo extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
		
		@NotNull
		private String firstName;
		
		@NotNull
		private String lastName;
		
		@NotNull
		private String email;
		
		@NotNull
		private long phoneNumber;
		
	}
