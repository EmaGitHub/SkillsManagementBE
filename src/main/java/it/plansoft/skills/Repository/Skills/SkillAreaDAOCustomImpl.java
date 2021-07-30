package it.plansoft.skills.Repository.Skills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class SkillAreaDAOCustomImpl implements SkillAreaDAOCustom {
	
	protected final static Logger log = LoggerFactory.getLogger(SkillAreaDAOCustomImpl.class);

    String URL = "jdbc:mysql://localhost:3306/skills";
    String USER = "root";
    String PASS = "root";
    
    @Override
	public int deleteAreaById(int areaId) throws SQLException {  
        int deletedItems = 0;
        List<Integer> areaChildren = getAreaChildren(areaId);
        // add root area to hierarchy for delete
        if (areaChildren == null)
        	areaChildren = new ArrayList<Integer>();
        areaChildren.add(areaId);
    	log.info("Deletion Hierarchy "+areaChildren);

        for(Integer area: areaChildren) {
        	deleteSkillsAndArea(area);
        }

		return deletedItems;
	}
	
	private List<Integer> getAreaChildren(int areaId) throws SQLException {
		List<Integer> deletionHierarchy = new ArrayList<Integer>();
		try {
    		String findChildren = "SELECT * FROM skill_area WHERE parent_id = ?";
    		
    		Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(findChildren);
            stmt.setInt(1, areaId);
    		
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	List<Integer> nestedList = getAreaChildren(rs.getInt("id"));
            	if (nestedList != null) {
            		deletionHierarchy.addAll(nestedList);
            	}
            	deletionHierarchy.add(rs.getInt("id"));
            }
    		
         } catch (SQLException e) {
            e.printStackTrace();
         } 
		
		if (deletionHierarchy.size() == 0)
			return null;
		else
			return deletionHierarchy;
	}
	
	@Transactional(rollbackFor=Exception.class)
	private int deleteSkillsAndArea(int areaId) throws SQLException {
		int deletedItems = 0;
		
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
        
		return deletedItems;
	}
}
