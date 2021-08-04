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

import it.plansoft.skills.DTO.SkillDTO;

public class SkillDAOCustomImpl implements SkillDAOCustom {

	protected final static Logger log = LoggerFactory.getLogger(SkillDAOCustomImpl.class);

    String URL = "jdbc:mysql://localhost:3306/skills";
    String USER = "root";
    String PASS = "root";
    
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SkillDTO> getUsersSkills(Long userId) throws SQLException {
		
		List<SkillDTO> skillList = new ArrayList<SkillDTO>();
		
		String sqlDeleteSkills = "SELECT * FROM skill \r\n"
								+ "JOIN competence on skill.id = competence.id\r\n"
								+ "WHERE skill.user_id = ?";

		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		
        PreparedStatement stmt = conn.prepareStatement(sqlDeleteSkills);
        stmt.setLong(1, userId);
        ResultSet rs = stmt.executeQuery();        
        
        while (rs.next()) {
        	SkillDTO skill = new SkillDTO();
        	//skill.set
        }
        
		return skillList;
	}

}
