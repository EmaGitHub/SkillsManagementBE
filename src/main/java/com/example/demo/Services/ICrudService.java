package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T, ID> extends IService {
	public List<T> findAll();
	public Optional<T> getById(ID id);
	public T update(T t);
	public void delete(T t);
}
