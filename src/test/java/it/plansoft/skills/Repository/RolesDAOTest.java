package it.plansoft.skills.Repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import it.plansoft.skills.DTO.RoleDTO;
import it.plansoft.skills.Repository.Roles.RolesDAO;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RolesDAOTest {
	
	@Autowired RolesDAO repo;
	
	@Test
	public void testCreateRoles() {
		RoleDTO user = new RoleDTO("USER");
		RoleDTO admin = new RoleDTO("SYSTEM_ADMIN");
		
		repo.saveAll(List.of(user, admin));
		
		List<RoleDTO> roles = repo.findAll();
		
		assertEquals(roles.size(), 2);
	}

}
