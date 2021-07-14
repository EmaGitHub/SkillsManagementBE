package it.plansoft.skills.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.SkillAreaDTO;

@Repository
public interface SkillAreaDAO extends JpaRepository<SkillAreaDTO, Integer> {
	
}	