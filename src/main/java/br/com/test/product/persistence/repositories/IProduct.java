package br.com.test.product.persistence.repositories;

import br.com.test.product.persistence.models.Product;
import br.com.test.product.persistence.repositories.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduct extends IBaseRepository<Product, Long> {
	// Do other methods if needed
}