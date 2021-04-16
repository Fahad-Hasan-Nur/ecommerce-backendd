package com.spring.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "ecommerce_otp")
@ToString(callSuper = true)
@AllArgsConstructor
public class Otp implements Serializable {
	private static final long serialVersionUID = 4240005902936474749L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Setter(value = AccessLevel.PRIVATE)
	@Column(updatable = false, nullable = false)
	String id;

	public Otp(String mobileNumber, String otp, long expiryTime) {
		this.otp=otp;
		this.expiryTime=expiryTime;
		this.mobileNumber=mobileNumber;
	}
	public Otp() {}

	@NotNull
	private String mobileNumber;
	
	@NotNull
	private String otp;
	
	@NotNull
	private long expiryTime;
}
