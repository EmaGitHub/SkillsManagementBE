package it.plansoft.skills.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.SkillAreaDTO;

@Service
public class SkillAreaService extends BaseCrudService<JpaRepository<SkillAreaDTO, Integer>, SkillAreaDTO, Integer> {

	public SkillAreaService(JpaRepository<SkillAreaDTO, Integer> repo) {
		super(repo);
	}
	
}
 