package com.spring.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_user_order database table.
 *
 * @author Fahad Hasan
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_user_order")
@ToString(callSuper = true)
public class UserOrder extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
		
		
		@OneToOne
		@JoinColumn(name = "user_id",referencedColumnName = "id")
		@JsonIgnore
		private User user;
		
		@Transient
		private String userId;
		
		@OneToOne
		@JoinColumn(name = "delivary_location_info_id",referencedColumnName = "id")
		@JsonIgnore
		private DelivaryLocationInfo delivaryLocationInfo;
		
		@Transient
		private String delivaryLocationInfoId;
		
		@OneToOne
		@JoinColumn(name = "delivary_contact_info_id",referencedColumnName = "id")
		@JsonIgnore
		private DelivaryContactInfo delivaryContactInfo;
		
		@Transient
		private String delivaryContactInfoId;
		
		@NotNull
		private String paymentMethod;
		
		@NotNull
		private String paymentInfo;
		
		@NotNull
		private long totalCost;
		
		@NotNull
		private String status;
		
	}
