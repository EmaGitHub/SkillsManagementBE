package it.plansoft.skills.DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.plansoft.skills.Model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Entity
@NoArgsConstructor
@Table(name = "skill")	
public class SkillDTO extends BaseModel<Long> {
	
	@Column(name = "competence_id", nullable = false)
	@Getter @Setter
	private int competenceId;

	@Column(name = "level", nullable = true)
	@Getter @Setter
	private int level;
	
	@Column(name = "self_level", nullable = true)
	@Getter @Setter
	private int selfLevel;
	
	@Column(name = "max_level", nullable = false, columnDefinition = "int default 5")
	@Getter @Setter
	private int maxLevel;
	
	@Column(name = "validation_user_id", nullable = true)
	@Getter @Setter
	private Long validationUserId;
	
	@Column(name = "validation_date", nullable = true)
	@Getter @Setter
	private LocalDate validationDate;
	
	@Getter @Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private UserDTO user;
}
