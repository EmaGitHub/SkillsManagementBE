package it.plansoft.skills.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import it.plansoft.skills.Model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competence")
public class CompetenceDTO extends BaseModel<Integer> {

	@Getter @Setter
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Getter @Setter
	@Column(name = "description", nullable = true)
	private String description;
	
	@Getter @Setter
	@Column(name = "area_id", nullable = false)
	private int areaId;
} 
