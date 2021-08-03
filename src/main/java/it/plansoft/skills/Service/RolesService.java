package it.plansoft.skills.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.RoleDTO;
import it.plansoft.skills.Service.Abstraction.BaseCrudService;

@Service
public class RolesService extends BaseCrudService<JpaRepository<RoleDTO, Integer>, RoleDTO, Integer> {

	public RolesService(JpaRepository<RoleDTO, Integer> repo) {
		super(repo);
	}
}