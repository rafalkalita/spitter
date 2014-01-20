package com.rafalkalita.spitter.specification.pages;

import com.rafalkalita.spitter.service.SpitterService;
import org.jbehave.web.selenium.WebDriverProvider;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:17
 */
public class PageFactory {

    private SpitterService spitterService;

    private final WebDriverProvider webDriverProvider;

    public PageFactory(WebDriverProvider webDriverProvider, SpitterService spitterService) {
        this.webDriverProvider = webDriverProvider;
        this.spitterService = spitterService;
    }

    public Home newHome() {
        return new Home(webDriverProvider, spitterService);
    }
}
