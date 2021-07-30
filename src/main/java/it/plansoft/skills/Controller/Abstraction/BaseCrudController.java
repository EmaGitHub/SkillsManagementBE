package it.plansoft.skills.Controller.Abstraction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.plansoft.skills.Model.BaseModel;
import it.plansoft.skills.Service.Abstraction.BaseCrudService;

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

	@GetMapping
	@Override
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) final ID id) {
		log.info("Get by ID");
		try {
			Optional<MODEL> item = service.getById(id);
			return ResponseEntity.ok(item);
		} catch (Exception e) {
			Map<String,Object> response = new HashMap<String, Object>();
			response.put("error", "GenericError");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
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
	@DeleteMapping
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('SYSTEM_ADMIN')")
	public void deleteById(@PathVariable(value="id") ID id) {
		log.info("Delete model with ID ", id);
		service.deleteById(id);
	}


}
