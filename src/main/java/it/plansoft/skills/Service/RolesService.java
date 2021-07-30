package it.plansoft.skills.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Service.Abstraction.BaseCrudService;

@Service
public class RolesService extends BaseCrudService<JpaRepository<UserDTO, Long>, UserDTO, Long> {

	public RolesService(JpaRepository<UserDTO, Long> repo) {
		super(repo);
	}
}