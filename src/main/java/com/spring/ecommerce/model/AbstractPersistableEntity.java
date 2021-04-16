package com.spring.ecommerce.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * The Abstract Persistable entity for all other db schema entity
 *
 * @author Fahad Hasan
 * @since 2021-1-29
 */
@Data
@MappedSuperclass
public class AbstractPersistableEntity implements Serializable {

	private static final long serialVersionUID = 4240005902936474749L;

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Setter(value = AccessLevel.PRIVATE)
	@Column(updatable = false, nullable = false)
	String id;


	@Version
	private Long version;

	@Column(updatable = false, nullable = false)
	@Setter(value = AccessLevel.PRIVATE)
	private LocalDateTime createdAt;


	@Column(updatable = false, nullable = false)
	private String createdBy;

	@Setter(value = AccessLevel.PRIVATE)
	private LocalDateTime updatedAt;

	private String updatedBy;
	
	@Column(nullable = false)
	private boolean isActive = true;
	
	@PrePersist
	void onInsert() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}

