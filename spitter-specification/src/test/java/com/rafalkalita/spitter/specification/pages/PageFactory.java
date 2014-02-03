package com.rafalkalita.spitter.specification.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private WebDriverProvider webDriverProvider;

    @Inject
    private JdbcTemplate jdbcTemplate;

    public Home newHome() {
        return new Home(webDriverProvider, jdbcTemplate);
    }

    public UserPage newUserPage() {
        return new UserPage(webDriverProvider);
    }

    public Registration newRegistrationPage() {
        return new Registration(webDriverProvider);
    }

    public Forms newForms() {
        return new Forms(webDriverProvider);
    }
}
