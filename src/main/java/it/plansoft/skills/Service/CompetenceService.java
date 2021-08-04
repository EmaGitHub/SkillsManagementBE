package it.plansoft.skills.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.CompetenceDTO;
import it.plansoft.skills.Service.Abstraction.BaseCrudService;

@Service
public class CompetenceService extends BaseCrudService<JpaRepository<CompetenceDTO, Integer>, CompetenceDTO, Integer> {

	public CompetenceService(JpaRepository<CompetenceDTO, Integer> repo) {
		super(repo);
	}
}
 