package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

public interface ICrudService<MODEL, ID> extends IService {
	public List<MODEL> getAll();
	public Optional<MODEL> getById(ID id);
	public MODEL update(MODEL t);
	public MODEL add(MODEL model);
	public void delete(MODEL t);
	public void deleteById(ID id);
}
