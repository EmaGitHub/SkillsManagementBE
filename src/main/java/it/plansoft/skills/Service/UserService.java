package it.plansoft.skills.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.plansoft.skills.DTO.UserDTO;

public class UserService extends BaseCrudService<JpaRepository<UserDTO, Long>, UserDTO, Long> {

}