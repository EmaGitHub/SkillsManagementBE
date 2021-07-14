package it.plansoft.skills.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Security.JWT.JwtUserDetailsService;
import it.plansoft.skills.Service.UserService;

@RestController
@CrossOrigin
public class UserController extends BaseCrudController<UserService, UserDTO, Long> {

	protected final static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	public UserController(UserService service) {
		super(service);
	}
	
	@RequestMapping(value = "/public/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		UserDTO newUser = null;
		try {
			newUser = userDetailsService.save(user);
			return ResponseEntity.ok(newUser);
		}
		catch (Exception e) {
			log.error("ERROR Registering "+e, "Exception occurs");
			Map<String,String> response = new HashMap<String, String>();
			if (e.toString().startsWith("org.springframework.dao.DataIntegrityViolationException")) {
				response.put("error", "DataIntegrityViolationException");
		        return ResponseEntity.ok(response);
			}
			response.put("error", "GenericError");
	        return ResponseEntity.ok(response);
		}
	}

}
