package com.rafalkalita.spitter.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Rafal Kalita
 */
public class LoginControllerTest {

    private LoginController loginController = new LoginController();

    @Test
    public void showsHomeViewAfterSuccessfulLogin() {

        ModelMap model = new ModelMap();

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("John");

        String view = loginController.printWelcome(model, principal);

        assertEquals("John", model.get("username"));
        assertNotNull(model.get("message"));
        assertEquals("home", view);
    }

    @Test
    public void showsHomePageWithErrorMessageAfterInvalidLogin() {

        ModelMap model = new ModelMap();

        String view = loginController.loginerror(model);

        assertNotNull(model.get("errormessage"));
        assertEquals("home", view);
    }
}
