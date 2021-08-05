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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import it.plansoft.skills.Controller.Abstraction.BaseCrudController;
import it.plansoft.skills.DTO.CompetenceAreaDTO;
import it.plansoft.skills.DTO.CompetenceDTO;
import it.plansoft.skills.Model.Data.RestResponse;
import it.plansoft.skills.Service.CompetenceAreaService;
import it.plansoft.skills.Service.CompetenceService;

@RestController
@CrossOrigin
@RequestMapping("/competence")
public class CompetenceController extends BaseCrudController<CompetenceService, CompetenceDTO, Integer> {

	protected final static Logger log = LoggerFactory.getLogger(CompetenceController.class);

	@Autowired
	CompetenceService skillService;
	
	@Autowired
	CompetenceAreaService skillAreaService;
	
	public CompetenceController(CompetenceService service) {
		super(service);
	}
	
	@PostMapping
	@RequestMapping(path = "/area", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveSkillArea(@RequestBody CompetenceAreaDTO skillArea) throws ClassNotFoundException, SQLException  {
		try {
			CompetenceAreaDTO savedArea = skillAreaService.save(skillArea);
			return ResponseEntity.ok(savedArea);
		}
		catch (Exception e) {
			log.error("Skill area not created. Exception throwed "+e);
	        Map<String,Object> response = new HashMap<String, Object>();
			if (e.toString().startsWith("org.springframework.dao.DataIntegrityViolationException")) {
				response.put("error", "DataIntegrityViolationException");
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			response.put("error", "GenericError");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping
	@RequestMapping(path = "/area")
	public ResponseEntity<?> getAllSkillArea() throws ClassNotFoundException, SQLException  {
		try {
			List<CompetenceAreaDTO> savedAreas = skillAreaService.getAll();
			return ResponseEntity.ok(savedAreas);
		}
		catch (Exception e) {
			this.log.error("Skill area not loaded. Exception throwed "+e);
			Map<String,String> response = new HashMap<String, String>();
			response.put("error", "Skill areas not retrieved");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyAuthority('SYSTEM_ADMIN')")
	@RequestMapping(path = "/area/{areaId}")
	public ResponseEntity<?> deleteSkillArea(@PathVariable(value="areaId") int id) throws ClassNotFoundException, SQLException  {
		try {
			int deletedSkills = skillAreaService.deleteSkillArea(id);
			return ResponseEntity.ok(deletedSkills);
		}
		catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@Override
	public ResponseEntity<?> save(@RequestBody CompetenceDTO skill) {
		try {
			CompetenceDTO savedArea = skillService.save(skill);
			return ResponseEntity.ok(savedArea);
		}
		catch (Exception e) {
			log.error("Competence not created. Exception throwed "+e);
	        Map<String,Object> response = new HashMap<String, Object>();
			if (e.toString().startsWith("org.springframework.dao.DataIntegrityViolationException")) {
				response.put("error", "DataIntegrityViolationException");
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			response.put("error", "GenericError");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
}

