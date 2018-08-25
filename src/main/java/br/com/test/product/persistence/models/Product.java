package br.com.test.product.persistence.models;

import br.com.test.product.persistence.models.base.Base;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "visits")
@DynamicUpdate
@DynamicInsert
public class Product extends Base {
	@Getter
	@Setter
	@Column
	private			String			name;

	@Getter
	@Setter
	@Column(columnDefinition = "TEXT")
	private			String			description;

	@Getter
	@Setter
	@Column
	private			Float			price;
}
