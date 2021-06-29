package it.plansoft.skills.Controllers;

import java.util.List;
import java.util.Optional;

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
	public List<MODEL> getAll();
	
	/**
	 * recupero oggetto specifico
	 */
	public Optional<MODEL> getById(ID id);
	
	/**
	 * aggiornamento dell'entity
	 */
	public MODEL update(MODEL model, ID id);
	
	/**
	 * inserimento dell'entity
	 */
	public MODEL add(MODEL model);
	
	/**
	 * cancellazione tramite id
	 */
	public void deleteById(ID id);
	
	/**
	 * cancellazione
	 */
	public void delete(MODEL model);
}