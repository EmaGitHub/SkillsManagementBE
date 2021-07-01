package it.plansoft.skills.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public class BaseCrudService<REPO extends JpaRepository<MODEL, ID>, MODEL, ID> implements ICrudService<MODEL, ID> {
	
	private JpaRepository<MODEL, ID> repo;
	
	private final static Logger log = LoggerFactory.getLogger(BaseCrudService.class);

	@Override
	public List<MODEL> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MODEL> getById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MODEL update(MODEL t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MODEL add(MODEL model) {
		return null;
	}

	@Override
	public void delete(MODEL t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub
		
	}

}
