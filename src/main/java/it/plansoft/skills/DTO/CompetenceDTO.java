package it.plansoft.skills.DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import it.plansoft.skills.Model.BaseModel;
import it.plansoft.skills.Model.IdModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competence")	
public class CompetenceDTO extends BaseModel<Long> {
	
	@Column(name = "skill_id", nullable = false)
	@Getter @Setter
	private int skillId;
	@Column(name = "level", nullable = true)
	@Getter @Setter
	private float level;
	@Column(name = "self_level", nullable = true)
	@Getter @Setter
	private float selfLevel;
	@Column(name = "max_level", nullable = true, columnDefinition = "default 5")
	@Getter @Setter
	private float maxLevel;
	@Column(name = "validation_user_id", nullable = true)
	@Getter @Setter
	private float validationUserId;
	@Column(name = "validation_date", nullable = true)
	@Getter @Setter
	private LocalDate validationDate;
}
