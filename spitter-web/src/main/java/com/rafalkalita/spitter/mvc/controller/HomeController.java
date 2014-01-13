package com.rafalkalita.spitter.mvc.controller;

import com.rafalkalita.spitter.service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class HomeController {

    @Inject
    private SpitterService spitterService;

    private static final int DEFAULT_SPITTLES_PER_PAGE = 10;

    public HomeController()  { }

	@RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
	public String showHomeView(Map<String, Object> model) {

        model.put("spittles", spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE));

        return "home";
	}
}
