package it.plansoft.skills.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.plansoft.skills.Model.BaseModel;
import it.plansoft.skills.Service.BaseCrudService;

public class BaseCrudController<SERVICE extends BaseCrudService, MODEL extends BaseModel, ID> implements ICrudController<MODEL, ID> {
	
	protected final static Logger log = LoggerFactory.getLogger(BaseCrudController.class);
	
	/**
	 * oggetto di business che effettua la logica
	 */
	protected SERVICE service;
	
	public BaseCrudController(SERVICE service) {
		this.service = service;
	}

	@RequestMapping(path = "")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<MODEL>> getAll() {
		log.info("Get All");
		return ResponseEntity.ok(service.getAll());
	}

	@Override
	public ResponseEntity<Optional<MODEL>> getById(ID id) {
		log.info("Get by ID");
		return ResponseEntity.ok(service.getById(id));
	}

	@Override
	public ResponseEntity<MODEL> update(MODEL model, ID id) {
		log.info("Update model ", model, id);
		return ResponseEntity.ok((MODEL)service.update(model));
	}

	@Override
	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> save(@RequestBody MODEL model) {
		log.info("Add model ", model);
		return ResponseEntity.ok((MODEL)service.save(model));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void deleteById(ID id) {
		log.info("Delete model with ID ", id);
		service.deleteById(id);
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void delete(MODEL model) {
		log.info("Delete model ", model);
		service.delete(model);
		
	}


}
