package com.example.demo.Model;

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
	protected int id;
	
	public IdModel() {	}
		
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
