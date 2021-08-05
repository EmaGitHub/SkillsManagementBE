package it.plansoft.skills.Repository.Skills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import it.plansoft.skills.DTO.SkillDTO;
import it.plansoft.skills.Model.Data.UserSkill;

public class SkillDAOCustomImpl implements SkillDAOCustom {

	protected final static Logger log = LoggerFactory.getLogger(SkillDAOCustomImpl.class);

    String URL = "jdbc:mysql://localhost:3306/skills";
    String USER = "root";
    String PASS = "root";
    
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UserSkill> getUsersSkills(Long userId) throws SQLException {
		
		List<UserSkill> skillList = new ArrayList<UserSkill>();
		
		String sqlDeleteSkills = "SELECT * FROM skill "
								+ "JOIN competence on skill.competence_id = competence.id "
								+ "WHERE skill.user_id = ?";

		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		
        PreparedStatement stmt = conn.prepareStatement(sqlDeleteSkills);
        stmt.setLong(1, userId);
        ResultSet rs = stmt.executeQuery();        
        
        while (rs.next()) {
        	UserSkill skill = new UserSkill();
        	skill.setCompetenceName(rs.getString("name"));
        	skill.setLevel(rs.getInt("level"));
        	skill.setMaxLevel(rs.getInt("max_level"));
        	skill.setValidationUserId(rs.getLong("validation_user_id"));
        	skill.setValidationDate(rs.getDate("validation_date").toLocalDate());
        	skillList.add(skill);
        }
        
		return skillList;
	}

}
