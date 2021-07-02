package it.plansoft.skills.Repository;

import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.UserDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserDAO extends JpaRepository<UserDTO, Long> {
	UserDTO findByUsername(String username);
}