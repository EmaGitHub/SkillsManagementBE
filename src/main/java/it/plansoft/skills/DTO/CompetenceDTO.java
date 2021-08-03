package it.plansoft.skills.DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.plansoft.skills.Model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Entity
@NoArgsConstructor
@Table(name = "competence")	
public class CompetenceDTO extends BaseModel<Long> {
	
	@Column(name = "skill_id", nullable = false)
	@Getter @Setter
	private int skillId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
	private UserDTO user;
	
	@Column(name = "level", nullable = false)
	@Getter @Setter
	private float level;
	
	@Column(name = "self_level", nullable = true)
	@Getter @Setter
	private float selfLevel;
	
	@Column(name = "max_level", nullable = false, columnDefinition = "float default '5.00'")
	@Getter @Setter
	private float maxLevel;
	
	@Column(name = "validation_user_id", nullable = true)
	@Getter @Setter
	private Long validationUserId;
	
	@Column(name = "validation_date", nullable = true)
	@Getter @Setter
	private LocalDate validationDate;
}
