package it.plansoft.skills.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	UserService userService;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	public UserController(UserService service) {
		super(service);
	}
	
	@RequestMapping(value = "public/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ResponseEntity<?> get() throws Exception{
//		List<UserDTO> list = super.getAll();
//		return ResponseEntity.ok(list);
//	}
}
