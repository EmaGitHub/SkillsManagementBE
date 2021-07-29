package it.plansoft.skills.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.Controller.Abstraction.BaseCrudController;
import it.plansoft.skills.DTO.UserDTO;
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
