package it.plansoft.skills.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.Controller.Abstraction.BaseCrudController;
import it.plansoft.skills.DTO.CompetenceDTO;
import it.plansoft.skills.Service.CompetenceService;

@RestController
@CrossOrigin
@RequestMapping("competence")
public class CompetenceController extends BaseCrudController<CompetenceService, CompetenceDTO, Long> {
	protected final static Logger log = LoggerFactory.getLogger(CompetenceController.class);

	@Autowired
	CompetenceService competenceService;

	public CompetenceController(CompetenceService service) {
		super(service);
	}


}
