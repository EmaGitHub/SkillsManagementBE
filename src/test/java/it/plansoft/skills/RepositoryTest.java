package it.plansoft.skills;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Repository.UserDAO;


/**
 * test repository standalone
 */
public class RepositoryTest extends AbstractRepoTest {


    @Autowired
    private UserDAO repository;

    @Test
    public void testStandAloneRepository() throws Exception {
        UserDTO user = this.repository.findByUsername("user");
        assertEquals(user.getUsername(), "user");
    }

    @Override
    protected void loadDataBase() {
//        this.repository.save(new UserAccount("Grosso", "Giuseppe",
//                "giuseppe.ing.grosso@gmail.com", "Microsoft", "giuseppe", passwordEncoder.encode("giuseppe"), "ADMIN|READ|WRITE"));
//
    }
}
