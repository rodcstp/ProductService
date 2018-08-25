package br.com.test.product.rest.services;

import br.com.test.product.persistence.dao.ProductDAO;
import br.com.test.product.persistence.models.Product;
import br.com.test.product.rest.dto.ProductDTO;
import br.com.test.product.rest.services.base.BaseBO;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductBO extends BaseBO<Product, ProductDAO, ProductDTO> {
	@Getter
	@Setter
	private 	ModelMapper 	mapper;

	@Autowired
	protected ProductBO(ProductDAO dao, ModelMapper mapper) {
		super(dao);
		setMapper(mapper);
	}

	@Override
	public ProductDTO save(ProductDTO create, UserDetails details) {
		Product			model			= create.getModel();
		Product			product			= model.getUuid() != null? getDao().findOne(model.getUuid()) : null;
		if (product != null) {
			getMapper().map(model, product);
		}
		return product == null? new ProductDTO(getDao().save(model), true) : new ProductDTO(getDao().save(product), true);
	}

	@Override
	public List<ProductDTO> save(Collection<ProductDTO> create, UserDetails details) {
		return null;
	}
}
