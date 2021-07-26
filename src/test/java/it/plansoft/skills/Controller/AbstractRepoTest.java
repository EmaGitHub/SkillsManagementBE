package it.plansoft.skills.Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.AbstractController;

import it.plansoft.skills.config.TestPasswordConfig;

/**
 * Test repository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(TestPasswordConfig.class)
abstract class AbstractRepoTest {

    protected final static Logger log = LoggerFactory.getLogger(AbstractController.class);

    protected abstract void loadDataBase();

    @Before
    public void init() {
        log.info("startup");
        loadDataBase();
    }

    @After
    public void teardown() {
        log.info("teardown");
    }


}