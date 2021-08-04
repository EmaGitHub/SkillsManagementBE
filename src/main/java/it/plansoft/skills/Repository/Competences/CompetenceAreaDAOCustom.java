package it.plansoft.skills.Repository.Competences;

import java.sql.SQLException;

public interface CompetenceAreaDAOCustom {
	
	public int deleteAreaById(int areaId) throws SQLException;
}
