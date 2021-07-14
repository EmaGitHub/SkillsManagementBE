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
@Table(name = "skill_area")
public class SkillAreaDTO extends BaseModel<Integer> {
	
	@Getter @Setter
	@Column(name = "area", nullable = false)
	private String area;
}
