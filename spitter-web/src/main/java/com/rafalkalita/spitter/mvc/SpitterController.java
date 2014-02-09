package com.rafalkalita.spitter.mvc;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Deals with requests altering spitter and listing spittles.
 *
 * User: rafalkalita
 * Date: 30/01/2014
 * Time: 18:24
 */
@Controller
@RequestMapping("/spitters")
public class SpitterController {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    @Inject
    private SpitterService spitterService;

    @Inject
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = {"/{username}", "/{username}/spittles"}, method = RequestMethod.GET)
    public String displaySpittlesForSpitter(@PathVariable String username, Model model) {

        logger.info("SpitterController [/spitters/{username} /spitters/{username}/spittles]");

        Spitter spitter = spitterService.getSpitterByUsername(username);
        List<Spittle> spittles = spitterService.getSpittlesForSpitter(username);

        model.addAttribute(spitter);
        model.addAttribute(spittles);

        return "spittles/list";
    }

    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String showNewSpitterForm(Model model) {

        logger.info("SpitterController [/spitters?new]");

        model.addAttribute(new Spitter());
        return "spitters/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processNewSpitterForm(@Valid Spitter spitter, BindingResult bindingResult, HttpServletRequest request) {

        logger.info("SpitterController [/spitters]");

        if(bindingResult.hasErrors()) {
            return "spitters/edit";
        }

        spitterService.saveSpitter(spitter);

        //After successfully Creating user
        authenticateUserAndSetSession(spitter, request);

        return "redirect:/spitters/" + spitter.getUsername() + "/spittles";
    }

    private void authenticateUserAndSetSession(Spitter user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

}
