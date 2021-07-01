package it.plansoft.skills.Repository;

import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.UserDTO;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserDAO extends CrudRepository<UserDTO, Long> {
	UserDTO findByUsername(String username);
}