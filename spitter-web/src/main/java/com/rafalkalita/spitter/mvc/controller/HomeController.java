package com.rafalkalita.spitter.mvc.controller;

import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Displays a home page with recent spittles.
 */
@Controller
public class HomeController {

    @Inject
    private SpitterService spitterService;

    @Resource(name = "spitterProperties")
    private Properties spitterProperties;

    public HomeController()  { }

	@RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
	public String showHomeView(Map<String, Object> model) {

        model.put("spittles", recentSpittles());

        return "home";
	}

    private List<Spittle> recentSpittles() {
        return spitterService.getRecentSpittles(Integer.parseInt(spitterProperties.getProperty("defaultSpittlesPerPage")));
    }
}
