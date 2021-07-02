package it.plansoft.skills.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * base CRUD implementation
 * @author ecalvisi
 *
 * @param <REPO>
 * @param <MODEL>
 * @param <ID>
 */

public class BaseCrudService<REPO extends JpaRepository<MODEL, ID>, MODEL, ID> implements ICrudService<MODEL, ID> {
	
	protected REPO repo;
	
	protected final static Logger log = LoggerFactory.getLogger(BaseCrudService.class);
	
	public BaseCrudService(REPO repo) {
		this.repo = repo;
	}

	@Override
	public List<MODEL> getAll() {
		List<MODEL> l = repo.findAll();
		log.info("Data retrieved: "+l);
		return l;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Optional<MODEL> getById(ID id) {
		Optional<MODEL> o = (Optional<MODEL>) repo.getById(id);
		log.info("Object retrieved: "+o);
		return null;
	}

	@Override
	public MODEL update(MODEL t) {
		return repo.save(t);
	}
	
	@Override
	public MODEL save(MODEL model) {
		return repo.save(model);
	}

	@Override
	public void delete(MODEL t) {
		repo.delete(t);
	}
	
	@Override
	public void deleteById(ID id) {
		repo.deleteById(id);
	}

}
