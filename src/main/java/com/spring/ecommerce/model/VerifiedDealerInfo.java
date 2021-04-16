package com.spring.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_verified_dealer_info database table.
 *
 * @author Fahad Hasan
 * @since 2021-03-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_verified_dealer_info")
@ToString(callSuper = true)
public class VerifiedDealerInfo extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
		
		
		@NotNull
		private String nid;
		
		@NotNull
		private String tin;
		
		@OneToOne
		@JoinColumn(name = "user_id",referencedColumnName = "id")
		@JsonIgnore
		private User user;
		
		@Transient
		private String userNmae;
		
		@Transient
		private String userId;
		
	}


