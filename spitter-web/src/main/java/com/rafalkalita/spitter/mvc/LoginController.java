package com.rafalkalita.spitter.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * @author Rafal Kalita
 */
@Controller
public class LoginController {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal ) {

        logger.info("LoginController [/welcome]");

        if(principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
            model.addAttribute("message", "Successfully loged in as " + username);
        }

        return "home";
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        logger.info("LoginController [/loginfailed]");

        //String errormessage = resources.getMessage("login.error", null, null);
        model.addAttribute("errormessage", "Invalid username or password.");

        return "home";
    }
}