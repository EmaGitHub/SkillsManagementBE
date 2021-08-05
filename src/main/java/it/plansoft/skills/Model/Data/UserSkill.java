package it.plansoft.skills.Model.Data;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserSkill {

	private int competenceId;
	private String competenceName;
	private int level;
	private int selfLevel;
	private int maxLevel;
	private Long validationUserId;
	private LocalDate validationDate;
}
