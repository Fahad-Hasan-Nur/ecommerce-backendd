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
@Table(name = "ecommerce_image")
@ToString(callSuper = true)
@AllArgsConstructor
public class Image implements Serializable {
	private static final long serialVersionUID = 4240005902936474749L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Setter(value = AccessLevel.PRIVATE)
	@Column(updatable = false, nullable = false)
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String type;
	
	@NotNull
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;


	public Image( String name, String type, byte[] picByte) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	
	Image(){}
	
	
}
