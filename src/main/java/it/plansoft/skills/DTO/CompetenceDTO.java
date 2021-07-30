package it.plansoft.skills.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import it.plansoft.skills.Model.IdModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competence")	
public class CompetenceDTO extends IdModel<Long> {
	
	@Column(name = "user_id", nullable = false)
	@Getter @Setter
	private Long userId;
	@Column(name = "skill_id", nullable = false)
	@Getter @Setter
	private int skillId;
//	@Column(name = "level", nullable = true)
//	@Getter @Setter
//	private float level;
//	@Column(name = "self_level", nullable = true)
//	@Getter @Setter
//	private float selfLevel;
//	@Getter @Setter
//	@Column(name = "max_level", nullable = true)
//	private float maxLevel;
//	
//	
//	@Column(name = "validation_user_id", nullable = true)
//	@Getter @Setter
//	private Long validationUserId;
//	@Getter @Setter
//	@Column(name = "validation_date", nullable = true)
//	protected LocalDate validationDate;

//	PRIMARY KEY (id),
//	FOREIGN KEY (user_id) REFERENCES user(id),
//	FOREIGN KEY (skill_id) REFERENCES skill(id),
//	FOREIGN KEY (validation_user_id) REFERENCES user(id)

}
