package it.plansoft.skills.Repository.Roles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.RoleDTO;
import it.plansoft.skills.Repository.Competences.CompetenceAreaDAOCustomImpl;

@Repository
public class RolesDAOCustomImpl implements RolesDAOCustom {
	
	protected final static Logger log = LoggerFactory.getLogger(CompetenceAreaDAOCustomImpl.class);

    String URL = "jdbc:mysql://localhost:3306/skills";
    String USER = "root";
    String PASS = "root";

	@Override
	public Set<RoleDTO> getUserRoles(Long userId) throws SQLException {

		Set<RoleDTO> roles = new HashSet<>();
		String sqlSelectRoles = "SELECT * FROM user_roles WHERE user_id = ?";

		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		
        PreparedStatement stmt = conn.prepareStatement(sqlSelectRoles);
        stmt.setLong(1, userId);
        ResultSet rs = stmt.executeQuery();        
        
        try {
        	while (rs.next()) {
        		String roleName = getRoleName(rs.getInt("role_id"));
        		roles.add(new RoleDTO(roleName));
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	stmt.close();
        }
		
		return roles;
	}
	
	private String getRoleName(int id) throws SQLException {
		
		String roleName = null;
		String sqlGetRoleName = "SELECT name FROM role WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(sqlGetRoleName);
        stmt.setInt(1, id);
        final ResultSet rs = stmt.executeQuery();    
        try {
	        while (rs.next()) 
	        		roleName = rs.getString(1);
        } 	
        finally {
        	rs.close();
        }    
		return roleName;
	}

}	