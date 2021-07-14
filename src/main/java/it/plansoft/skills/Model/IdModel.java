package it.plansoft.skills.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * creazione di base per i modelli di dati
 * @author ecalvisi
 *
 * @param <ID>
 */

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class IdModel<ID> {

	@Id
	@Getter @Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	protected ID id;
}
