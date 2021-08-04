package it.plansoft.skills.Controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Security.JWT.JwtTokenUtil;
import it.plansoft.skills.Security.JWT.JwtUserDetailsService;
import it.plansoft.skills.Security.JWT.Model.JwtRequest;
import it.plansoft.skills.Security.JWT.Model.JwtResponse;
import it.plansoft.skills.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("public")
public class AuthenticationController {
	
	protected final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		UserDTO newUser = null;
		try {
			newUser = userDetailsService.save(user); 
			return ResponseEntity.ok(newUser);
		}
		catch (Exception e) {
			log.error("ERROR Registering "+e, "Exception occurs");
			Map<String,Object> response = new HashMap<String, Object>();
			if (e.toString().startsWith("org.springframework.dao.DataIntegrityViolationException")) {
				response.put("error", "DataIntegrityViolationException");
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			response.put("error", "GenericError");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			final UserDTO user = userService.findByUsername(authenticationRequest.getUsername());
			return ResponseEntity.ok(new JwtResponse(user.getUsername(), user.getId(), token, 200));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponse(null, null, null, 401));
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}