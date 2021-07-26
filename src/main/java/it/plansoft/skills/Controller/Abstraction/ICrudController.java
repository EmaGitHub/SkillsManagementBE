package it.plansoft.skills.Controller.Abstraction;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

/**
 * Controller per operazioni CRUD
 * 
 * @author ecalvisi
 *
 */

public interface ICrudController<MODEL, ID> extends IController {

	/**
	 *  recupero oggetti (non paginato)
	 */
	public ResponseEntity<List<MODEL>> getAll();
	
	/**
	 * recupero oggetto specifico
	 */
	public ResponseEntity<Optional<MODEL>> getById(ID id);
	
	/**
	 * aggiornamento dell'entity
	 */
	public ResponseEntity<MODEL> update(MODEL model, ID id);
	
	/**
	 * inserimento dell'entity
	 */
	public ResponseEntity<?> save(MODEL model);
	
	/**
	 * cancellazione tramite id
	 */
	public void deleteById(ID id);
	
}