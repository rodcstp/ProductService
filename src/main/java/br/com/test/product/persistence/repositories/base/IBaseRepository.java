package br.com.test.product.persistence.repositories.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	Optional<T> findByUuid(UUID uuid);

	@Transactional
	Optional<ID> deleteByUuid(UUID uuid);
}