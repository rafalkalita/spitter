package com.rafalkalita.spitter.specification.pages;

import com.rafalkalita.spitter.service.SpitterService;
import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:17
 */
@Component
public class PageFactory {

    @Inject
    private SpitterService spitterService;

    @Inject
    private WebDriverProvider webDriverProvider;


    public Home newHome() {
        return new Home(webDriverProvider, spitterService);
    }
}
