package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	List<T> findAll();

	T saveOrUpdate(T o);

	Optional<T> getOne(Long id);

	void delete(Long id);

}