package br.com.test.product.rest.controllers;

import br.com.test.product.rest.controllers.base.BController;
import br.com.test.product.rest.dto.ProductDTO;
import br.com.test.product.rest.services.ProductBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/rest/product", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ProductController extends BController<ProductBO, ProductDTO> {

	@Autowired
	public ProductController(ProductBO service) {
		super(service);
	}

	// Implement other methods if desired
}
