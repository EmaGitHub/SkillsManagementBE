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
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Getter @Setter
	@Column(name = "description", nullable = true)
	private String description;	
	@Getter @Setter
	@Column(name = "parent_id", nullable = true)
	private int parentId = -1;
}
