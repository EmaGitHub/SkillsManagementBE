package it.plansoft.skills.Model;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * creazione di base per i modelli di dati
 * @author ecalvisi
 *
 * @param <ID>
 */

@MappedSuperclass
public class IdModel<ID> {

	@Id
    @GeneratedValue
	protected Long id;
	
	public IdModel() {	}
		
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
