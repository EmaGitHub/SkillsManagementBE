package it.plansoft.skills.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Repository.UserDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {
	
	@Autowired
	UserDAO userDAO;

    @Test
    public void myTest() throws Exception {
        userDAO.save(new UserDTO());
    }
}