package br.com.test.product.rest.services.base;

import br.com.test.product.persistence.dao.base.IBaseDAO;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class BaseBO<T, DAO extends IBaseDAO<T, Long>, DTO> implements IBaseBO<T, DTO> {
	@Getter
	private 	final 	DAO			dao;
	private 	final 	Class<T>	entity;
	private 	final 	Class<DTO>	dto;

	@SuppressWarnings("unchecked")
	@Inject
	protected BaseBO(DAO dao) {
		entity		= (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		dto			= (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
		this.dao	= dao;
	}

	@Override
	public DTO findOne(Long id, UserDetails details) {
		return toDTO(getDao().findOne(id), true);
	}

	@Override
	public DTO findOne(UUID uuid, UserDetails details) {
		return toDTO(getDao().findOne(uuid), true);
	}

	@Override
	public List<DTO> findAll(UserDetails details) {
		return getDao().findAll().stream().map(item -> toDTO(item, false)).collect(Collectors.toList());
	}

	@Override
	public Page<DTO> findAll(Pageable pageable, UserDetails details) {
		return getDao().findAll(pageable).map(item -> toDTO(item, false));
	}

	@Override
	public void delete(UUID id) {
		getDao().delete(id);
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Override
	public void delete(Long id) {
		getDao().delete(id);
	}

	private DTO toDTO(T item, boolean detailed) {
		try {
			return dto.getConstructor(entity, boolean.class).newInstance(item, detailed);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(dto.getName() + ": No constructor matches (T, Boolean)");
		}
	}
}


