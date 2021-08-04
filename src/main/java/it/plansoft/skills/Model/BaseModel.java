package it.plansoft.skills.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import it.plansoft.skills.DTO.CompetenceDTO;
import it.plansoft.skills.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  classe di base per i modelli di dati: id + tracciamento inserimento / modifica
 */

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@JsonSubTypes(
{
    @Type(value = UserDTO.class, name = "user"),
    @Type(value = CompetenceDTO.class, name = "skill")
})
public abstract class BaseModel<ID> extends IdModel<ID> {
	
	@Getter @Setter
	@Column(name = "dt_insert", nullable = true)
	protected LocalDate dtInsert;
}
