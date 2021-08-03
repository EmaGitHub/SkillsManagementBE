package it.plansoft.skills.Repository.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.RoleDTO;

@Repository
public interface RolesDAO extends JpaRepository<RoleDTO, Integer>, RolesDAOCustom {

}	