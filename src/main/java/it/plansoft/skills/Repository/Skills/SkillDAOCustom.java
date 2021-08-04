package it.plansoft.skills.Repository.Skills;

import java.sql.SQLException;
import java.util.List;

import it.plansoft.skills.DTO.SkillDTO;

public interface SkillDAOCustom {

	public List<SkillDTO> getUsersSkills(Long userId) throws SQLException;
}
