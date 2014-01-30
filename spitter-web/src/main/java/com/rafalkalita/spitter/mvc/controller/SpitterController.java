package com.rafalkalita.spitter.mvc.controller;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

/**
 * User: rafalkalita
 * Date: 30/01/2014
 * Time: 18:24
 */
@Controller
@RequestMapping("/spitters")
public class SpitterController {

    @Inject
    private SpitterService spitterService;

    /**
     * Maps the url:
     * http://localhost:8080/spitter/spitters/spittles?spitter=john
     */
    @RequestMapping(value = "/{username}/spittles", method = RequestMethod.GET)
    public String displaySpittlesForSpitter(@PathVariable String username, Model model) {

        Spitter spitter = spitterService.getSpitterByUsername(username);
        List<Spittle> spittles = spitterService.getSpittlesForSpitter(username);

        model.addAttribute(spitter);
        model.addAttribute(spittles);

        return "spittles/list";
    }
}
