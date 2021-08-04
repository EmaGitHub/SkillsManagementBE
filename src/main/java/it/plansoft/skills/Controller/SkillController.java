package it.plansoft.skills.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.Controller.Abstraction.BaseCrudController;
import it.plansoft.skills.DTO.SkillDTO;
import it.plansoft.skills.Service.SkillService;

@RestController
@CrossOrigin
@RequestMapping("skill")
public class SkillController extends BaseCrudController<SkillService, SkillDTO, Long> {
	protected final static Logger log = LoggerFactory.getLogger(SkillController.class);

	@Autowired
	SkillService skillService;

	public SkillController(SkillService service) {
		super(service);
	}

	@GetMapping
	public List<SkillDTO> getUserSkills(Long userId) {
		return skillService.getUserSkills(userId);
	}

}
