package com.rafalkalita.spitter.specification.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:51
 */
public class Home extends FluentWebDriverPage {

    public Home(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://localhost:8080/spitter-web/");
    }
}
