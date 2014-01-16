package com.rafalkalita.spitter.specification.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import java.util.Date;

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

    public void createAUser(String user) {
        // TODO: finish
    }

    public void postASpittle(String user, String s) {
        // TODO: finish
    }

    public void postASpittle(String user, String message, Date date) {
        // TODO: finish
    }

    public void setDefaultSpittlesPerPage(int number) {
        // TODO: finish
    }

    public int numberOfSpittles() {
        return 0;// TODO: finish
    }

    public int numberOfSpittlesForAUser(String user) {
        return 0; // TODO: finish
    }

    public boolean allSpittlesAreOrderedDescending() {
        return false; // TODO: finish
    }


}
