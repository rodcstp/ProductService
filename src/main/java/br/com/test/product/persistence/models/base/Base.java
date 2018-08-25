package br.com.test.product.persistence.models.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class Base {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OrderColumn
	private		Long			id;

	@Getter
	@Setter
	@Column(insertable = false, updatable=false, nullable = false, unique = true, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
	@Type(type = "org.hibernate.type.PostgresUUIDType")
	@Generated(GenerationTime.INSERT)
	private		UUID			uuid;

	@Getter
	@Setter
	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private 	boolean			erased		= false;

	@Getter
	@Setter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private 	Date			createdAt	= new Date();

	@Getter
	@Setter
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private 	Date			updatedAt	= new Date();

	@PrePersist
	public void prePersist() {
		setCreatedAt(new Date());
	}

	@PreUpdate
	public void preUpdate() {
		setUpdatedAt(new Date());
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return getUuid().compareTo(((Base) obj).getUuid()) == 0;
		} catch (Exception e) {
			return super.equals(obj);
		}
	}
}
