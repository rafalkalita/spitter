package com.rafalkalita.spitter.mvc.controller;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
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

    @Inject
    private SpitterService spitterService;

    @RequestMapping(value = "/{username}/spittles", method = RequestMethod.GET)
    public String displaySpittlesForSpitter(@PathVariable String username, Model model) {

        Spitter spitter = spitterService.getSpitterByUsername(username);
        List<Spittle> spittles = spitterService.getSpittlesForSpitter(username);

        model.addAttribute(spitter);
        model.addAttribute(spittles);

        return "spittles/list";
    }

    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String showNewSpitterForm(Model model) {

        model.addAttribute(new Spitter());
        return "spitters/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processNewSpitterForm(@Valid Spitter spitter, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "spitters/edit";
        }

        spitterService.saveSpitter(spitter);

        return "redirect:/spitters/" + spitter.getUsername() + "/spittles";
    }
}
