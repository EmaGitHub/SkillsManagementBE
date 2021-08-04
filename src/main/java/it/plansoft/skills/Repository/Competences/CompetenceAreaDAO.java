package it.plansoft.skills.Repository.Competences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.CompetenceAreaDTO;

@Repository
public interface CompetenceAreaDAO extends JpaRepository<CompetenceAreaDTO, Integer>, CompetenceAreaDAOCustom {
}	