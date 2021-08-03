package it.plansoft.skills.Repository.Competences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.CompetenceDTO;

@Repository
public interface CompetenceDAO extends JpaRepository<CompetenceDTO, Long> {
}	