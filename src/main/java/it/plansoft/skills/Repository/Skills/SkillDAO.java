package it.plansoft.skills.Repository.Skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.SkillDTO;

@Repository
public interface SkillDAO extends JpaRepository<SkillDTO, Long>, SkillDAOCustom {
}	