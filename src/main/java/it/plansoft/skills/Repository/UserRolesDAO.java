package it.plansoft.skills.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.RoleDTO;

@Repository
public interface UserRolesDAO extends JpaRepository<RoleDTO, Long> {

}