package it.plansoft.skills.Service;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.CompetenceAreaDTO;
import it.plansoft.skills.Repository.Competences.CompetenceAreaDAOCustom;
import it.plansoft.skills.Service.Abstraction.BaseCrudService;

@Service
public class CompetenceAreaService extends BaseCrudService<JpaRepository<CompetenceAreaDTO, Integer>, CompetenceAreaDTO, Integer> {

	public CompetenceAreaService(JpaRepository<CompetenceAreaDTO, Integer> repo) {
		super(repo);
	}
	
	public int deleteSkillArea(int skillAreaId) throws SQLException {
		return ((CompetenceAreaDAOCustom)repo).deleteAreaById(skillAreaId);
	}
}
 