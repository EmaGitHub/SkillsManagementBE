package it.plansoft.skills.Repository.Skills;

import java.sql.SQLException;

public interface SkillAreaDAOCustom {
	
	public int deleteAreaById(int areaId) throws SQLException;
}
