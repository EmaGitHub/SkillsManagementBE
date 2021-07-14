package it.plansoft.skills.Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.DTO.SkillAreaDTO;
import it.plansoft.skills.DTO.SkillDTO;
import it.plansoft.skills.Service.SkillAreaService;
import it.plansoft.skills.Service.SkillService;

@RestController
@CrossOrigin
@RequestMapping("/skill")
public class SkillController extends BaseCrudController<SkillService, SkillDTO, Integer> {

	protected final static Logger log = LoggerFactory.getLogger(SkillController.class);

	@Autowired
	SkillService skillService;
	
	@Autowired
	SkillAreaService skillAreaService;
	
	public SkillController(SkillService service) {
		super(service);
	}
	
	@PostMapping
	@RequestMapping(path = "/area", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveSkillArea(@RequestBody SkillAreaDTO skillArea) throws ClassNotFoundException, SQLException  {
		try {
			SkillAreaDTO savedArea = skillAreaService.save(skillArea);
			return ResponseEntity.ok(savedArea);
		}
		catch (Exception e) {
			this.log.error("Skill area not created. Exception throwed "+e);
			Map<String,String> response = new HashMap<String, String>();
			response.put("error", "Skill area not saved");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping
	@RequestMapping(path = "/area")
	public ResponseEntity<?> getAllSkillArea() throws ClassNotFoundException, SQLException  {
		try {
			List<SkillAreaDTO> savedAreas = skillAreaService.getAll();
			return ResponseEntity.ok(savedAreas);
		}
		catch (Exception e) {
			this.log.error("Skill area not loaded. Exception throwed "+e);
			Map<String,String> response = new HashMap<String, String>();
			response.put("error", "Skill areas not retrieved");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}

