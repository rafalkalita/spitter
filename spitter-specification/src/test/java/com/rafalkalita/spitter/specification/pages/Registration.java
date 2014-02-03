package com.rafalkalita.spitter.specification.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

/**
 * User: rafalkalita
 * Date: 02/02/2014
 * Time: 19:52
 */
public class Registration extends FluentWebDriverPage {

    public Registration(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        String url = "http://localhost:8080/spitter-web/spitters?new";
        get(url);
    }
}
