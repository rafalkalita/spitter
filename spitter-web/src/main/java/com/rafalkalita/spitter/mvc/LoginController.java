package com.rafalkalita.spitter.mvc;

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

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal ) {

        String username = principal.getName();
        model.addAttribute("username", username);
        model.addAttribute("message", "Successfully loged in as " + username);

        return "home";
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        //String errormessage = resources.getMessage("login.error", null, null);
        model.addAttribute("errormessage", "Invalid username or password.");

        return "home";
    }
}