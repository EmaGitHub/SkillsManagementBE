package it.plansoft.skills.Controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Service.UserService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
class UserControllerTest {

	@Autowired
	UserService userService;
	
	@Autowired
	UserController userController;

	@Test
	void registerNewUser() {
		UserDTO newUser = new UserDTO();
		newUser.setUsername("usernameTest");
		newUser.setPassword("passwordTest");
		UserDTO u = userService.save(newUser);
		
		assertNotNull(u.getId());
		assert(u.getUsername().equals("usernameTest"));		
		assertNotNull(u.getDtInsert());
		assert(u.getDtInsert().getYear() == LocalDate.now().getYear());
		assert(u.getDtInsert().getMonth()  == LocalDate.now().getMonth());
		assert(u.getDtInsert().getDayOfMonth() == LocalDate.now().getDayOfMonth());
	}

	@Test()
	void registerCoupleOfUser() {
		UserDTO newUser1 = new UserDTO();
		newUser1.setUsername("usernameTestC");
		newUser1.setPassword("passwordTestC");
		UserDTO newUser2 = new UserDTO();
		newUser2.setUsername("usernameTestC");
		newUser2.setPassword("passwordTestC");
		ResponseEntity<?> response = userController.save(newUser1);
		
		assertThrows(DataIntegrityViolationException.class, () -> userController.save(newUser2));
		assert(response.getStatusCode().equals(HttpStatus.OK));
		assert(response.getBody().equals(newUser1));
	}

}
