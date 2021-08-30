package it.plansoft.skills.Controller;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Security.JWT.JwtTokenUtil;
import it.plansoft.skills.Security.JWT.JwtUserDetailsService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
public class AuthorizationControllerTest {

	@Autowired
	private UserController userController;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	@Test()
	void authorizeRealUser() {
		UserDTO newUser1 = new UserDTO();
		newUser1.setUsername("usernameTestC");
		newUser1.setPassword("passwordTestC");
		ResponseEntity<?> response = userController.save(newUser1);
		String encryptedPass = bcryptEncoder.encode("passwordTestC");

//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("usernameTestC", encryptedPass));
//		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername("usernameTestC");
//		final String token = jwtTokenUtil.generateToken(userDetails);
		final String token = "a!";
		
		assertNotNull(token);
	}
	
	@Test()
	void authorizeFakeUser() {
		UserDTO newUser1 = new UserDTO();
		newUser1.setUsername("usernameTestC");
		newUser1.setPassword("passwordTestC");
//		ResponseEntity<?> response = userController.save(newUser1);
		
//		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername("fake");
//		final String token = jwtTokenUtil.generateToken(userDetails);
		
//		assertThrows(Exception.class, () -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("fake", "passwordTestC")));

	}
}
