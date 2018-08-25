package br.com.test.product.rest.services.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IBaseBO<T, DTO> {
	DTO findOne(Long id, UserDetails details);
	DTO findOne(UUID uuid, UserDetails details);

	List<DTO> findAll(UserDetails details);
	Page<DTO> findAll(Pageable pageable, UserDetails details);

	void delete(UUID id);
	void delete(T entity);
	void delete(Long id);

	DTO save(DTO create, UserDetails details);
	List<DTO> save(Collection<DTO> create, UserDetails details);
}