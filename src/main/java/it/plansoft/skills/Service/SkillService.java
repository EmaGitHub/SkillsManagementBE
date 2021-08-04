package it.plansoft.skills.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.SkillDTO;
import it.plansoft.skills.Repository.Skills.SkillDAOCustom;
import it.plansoft.skills.Service.Abstraction.BaseCrudService;

@Service
public class SkillService extends BaseCrudService<JpaRepository<SkillDTO, Long>, SkillDTO, Long> {

	public SkillService(JpaRepository<SkillDTO, Long> repo) {
		super(repo);
	}

	public List<SkillDTO> getUserSkills(Long userId) throws SQLException {
		return ((SkillDAOCustom)repo).getUsersSkills(userId);
	}

}