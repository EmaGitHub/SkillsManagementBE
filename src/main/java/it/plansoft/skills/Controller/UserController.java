package it.plansoft.skills.Controller;

import java.util.HashMap;
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

import it.plansoft.skills.Controller.Abstraction.BaseCrudController;
import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Model.Data.User;
import it.plansoft.skills.Security.JWT.JwtUserDetailsService;
import it.plansoft.skills.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController extends BaseCrudController<UserService, UserDTO, Long> {

	protected final static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	public UserController(UserService service) {
		super(service);
	}
	
	

}
