package br.com.test.product.rest.dto;

import br.com.test.product.persistence.models.Product;
import br.com.test.product.rest.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO extends BaseDTO<Product> {
	@Getter
	@Setter
	@Size(max = 255, message = "name.too.long")
	@JsonProperty("name")
	private			String			name;

	@Getter
	@Setter
	@JsonProperty("description")
	private			String			description;

	@Getter
	@Setter
	@JsonProperty("price")
	private			Float			price;

	public ProductDTO() {
		super();
	}

	public ProductDTO(Product product, boolean detailed) {
		super(product);
	}
}