package br.com.test.product.persistence.dao.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface IBaseDAO<T, ID extends Serializable>{
	T findOne(ID id);
	T findOne(UUID uuid);

	List<T> findAll();
	Page<T> findAll(Pageable pageable);

	T save(T model);
	List<T> save(Iterable<T> models);

	void delete(UUID id);
	void delete(ID id);
	void delete(T entity);
}