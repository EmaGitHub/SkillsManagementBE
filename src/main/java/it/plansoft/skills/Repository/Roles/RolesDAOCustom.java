package it.plansoft.skills.Repository.Roles;

import java.sql.SQLException;
import java.util.Set;

import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.RoleDTO;

@Repository
public interface RolesDAOCustom {

	public Set<RoleDTO> getUserRoles(Long id) throws SQLException;
}	