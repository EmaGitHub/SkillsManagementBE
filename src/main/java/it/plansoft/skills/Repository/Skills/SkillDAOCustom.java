package it.plansoft.skills.Repository.Skills;

import java.sql.SQLException;
import java.util.List;

import it.plansoft.skills.Model.Data.UserSkill;

public interface SkillDAOCustom {

	public List<UserSkill> getUsersSkills(Long userId) throws SQLException;
}
