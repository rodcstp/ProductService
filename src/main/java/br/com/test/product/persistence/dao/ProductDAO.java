package br.com.test.product.persistence.dao;

import br.com.test.product.persistence.dao.base.BaseDAO;
import br.com.test.product.persistence.models.Product;
import br.com.test.product.persistence.repositories.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAO extends BaseDAO<Product, Long, IProduct> {
	@Autowired
	public ProductDAO(IProduct repository) {
		super(repository);
	}
}