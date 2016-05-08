package de.alexandria.cms.business.impl.service;

import de.alexandria.cms.business.api.service.UserService;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import de.alexandria.cms.backend.api.repository.UserRepository;
import de.alexandria.cms.config.SpringConfigBackendForTest;
import de.alexandria.cms.config.SpringConfigBusiness;
import de.alexandria.cms.model.api.security.User;
import de.alexandria.cms.model.impl.security.UserImpl;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfigBusiness.class, SpringConfigBackendForTest.class})
public class UserServiceTest {

    private User user;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService service;

    @Before
    public void setup() {
        user = new UserImpl();
        user.setEmail("foo@spar.org");
        user.setPasswordHash("foobar");
        Mockito.when(userRepository.findByEmail("foo@spar.org")).thenReturn(user);
    }

    @After
    public void tearDown() {
        Mockito.reset(userRepository);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        UserDetails retrieved = service.loadUserByUsername("foo@spar.org");
        assertEquals(user.getEmail(), retrieved.getUsername());
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findByEmail("foo@spar.org");
    }

    @Test
    public void testGetPasswordHash() throws Exception {
        PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        assertTrue(pwEncoder.matches("foobar", user.getPasswordHash()));
    }
}
