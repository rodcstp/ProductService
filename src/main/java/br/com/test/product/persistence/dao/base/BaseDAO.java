package br.com.test.product.persistence.dao.base;

import br.com.test.product.exception.ServiceException;
import br.com.test.product.persistence.repositories.base.IBaseRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SuppressWarnings("unchecked")
public abstract class BaseDAO<T, ID extends Serializable, TR extends IBaseRepository<T, ID>> implements IBaseDAO<T, ID> {
	@Getter(AccessLevel.PROTECTED)
	private		final	TR			repository;

	@Getter
	private 	final	String		className;

	@Autowired
	public BaseDAO(TR repository) {
		this.repository = repository;
		this.className	= ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName().toLowerCase();
	}

	@Override
	public T findOne(ID id) {
		return getRepository().findById(Optional.ofNullable(id).orElseThrow(() -> new ServiceException(className + ".id.missing"))).orElseThrow(() -> new ServiceException(className + ".not.found"));
	}

	@Override
	public T findOne(UUID uuid) {
		return getRepository().findByUuid(Optional.ofNullable(uuid).orElseThrow(() -> new ServiceException(className + ".id.missing"))).orElseThrow(() -> new ServiceException(className + ".not.found"));
	}

	@Override
	public List<T> findAll() {
		return StreamSupport.stream(getRepository().findAll(new Sort(Sort.Direction.ASC, "id")).spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Override
	public T save(T model) {
		return getRepository().save(model);
	}

	@Override
	public List<T> save(Iterable<T> models) {
		return StreamSupport.stream(getRepository().saveAll(models).spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public void delete(ID id) {
		getRepository().deleteById(id);
	}

	@Override
	public void delete(UUID id) {
		getRepository().deleteByUuid(id).orElseThrow(() -> new ServiceException("cannot.delete"));
	}

	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}
}