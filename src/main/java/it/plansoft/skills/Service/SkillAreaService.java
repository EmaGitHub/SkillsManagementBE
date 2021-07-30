package it.plansoft.skills.Service;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.SkillAreaDTO;
import it.plansoft.skills.Repository.Skills.SkillAreaDAOCustom;

@Service
public class SkillAreaService extends BaseCrudService<JpaRepository<SkillAreaDTO, Integer>, SkillAreaDTO, Integer> {

	public SkillAreaService(JpaRepository<SkillAreaDTO, Integer> repo) {
		super(repo);
	}
	
	public int deleteSkillArea(int skillAreaId) throws SQLException {
		return ((SkillAreaDAOCustom)repo).deleteAreaById(skillAreaId);
	}
}
 