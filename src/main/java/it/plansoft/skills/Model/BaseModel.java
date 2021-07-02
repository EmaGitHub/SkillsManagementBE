package it.plansoft.skills.Model;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 *  classe di base per i modelli di dati: id + tracciamento inserimento / modifica
 */
@MappedSuperclass
public abstract class BaseModel<ID> extends IdModel<ID> {
	
	protected java.util.Date dtInsert;

	public java.util.Date getDtInsert() {
		return this.dtInsert;
	}
}
