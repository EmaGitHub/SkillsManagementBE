package it.plansoft.skills.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

public class SkillAreaDAOCustomImpl implements SkillAreaDAOCustom {
	
	@Transactional(rollbackFor=Exception.class)
	public int deleteAreaById(int areaId) throws SQLException {
        
        String URL = "jdbc:mysql://localhost:3306/skills";
        String USER = "root";
        String PASS = "root";
        
        int deletedItems = 0;
        try {
        		String sqlDeleteSkills = "DELETE FROM skill WHERE area_id = ?";

        		Connection conn = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sqlDeleteSkills);
                stmt.setInt(1, areaId);
                deletedItems = stmt.executeUpdate();        
                
                String sqlDeleteArea = "DELETE FROM skill_area WHERE id = ?";

        		int deletedArea = 0;
                PreparedStatement stmt2 = conn.prepareStatement(sqlDeleteArea);
                stmt2.setInt(1, areaId);
                deletedArea = stmt2.executeUpdate(); 
                if (deletedArea != 1) {
                	throw new SQLException();
                }

             } catch (SQLException e) {
                e.printStackTrace();
        } 

		return deletedItems;
	}
}
