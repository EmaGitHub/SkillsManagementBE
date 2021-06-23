package com.example.demo.Model;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 *  classe di base per i modelli di dati: id + tracciamento inserimento / modifica
 */
@MappedSuperclass
public abstract class BaseModel<ID> extends IdModel<ID> {
	
	protected Date dtInsert;

	public Date getDtInsert() {
		return this.dtInsert;
	}
}
