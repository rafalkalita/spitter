package com.rafalkalita.spitter.controller;

import com.rafalkalita.spitter.service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class HomeController {

    private SpitterService spitterService;

    private static final int DEFAULT_SPITTLES_PER_PAGE = 10;

    @Inject
    public HomeController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }

	@RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
	public String showHomeView(Map<String, Object> model) {

        model.put("spittles", spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE));

        return "home";
	}
}
