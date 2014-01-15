package com.rafalkalita.spitter.specification.pages;

import org.jbehave.web.selenium.WebDriverProvider;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:17
 */
public class PageFactory {

    private final WebDriverProvider webDriverProvider;

    public PageFactory(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    public Home newHome() {
        return new Home(webDriverProvider);
    }
}
