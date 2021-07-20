package it.plansoft.skills.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.SkillDTO;

@Service
public class SkillService extends BaseCrudService<JpaRepository<SkillDTO, Integer>, SkillDTO, Integer> {

	public SkillService(JpaRepository<SkillDTO, Integer> repo) {
		super(repo);
	}
}
 