package com.rafalkalita.spitter.specification.pages;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import java.util.Date;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:51
 */
public class Home extends FluentWebDriverPage {

    private SpitterService spitterService;

    public Home(WebDriverProvider webDriverProvider, SpitterService spitterService) {
        super(webDriverProvider);
        this.spitterService = spitterService;
    }

    public void go() {
        get("http://localhost:8080/spitter-web/");
    }

    public void createAUser(String username) {
        spitterService.saveSpitter(aNewSpitter(username));
    }

    public void postASpittle(String username, String message) {

        Spitter spitter = spitterService.getSpitterByUsername(username);
        Spittle spittle = aSpittle(message, spitter);

        spitterService.saveSpittle(spittle);
    }

    public void postASpittle(String username, String message, Date date) {

        Spitter spitter = spitterService.getSpitterByUsername(username);
        Spittle spittle = aSpittle(message, spitter, date);

        spitterService.saveSpittle(spittle);
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

    private Spitter aNewSpitter(String username) {

        Spitter spitter = new Spitter();
        spitter.setFullName(username);
        spitter.setUsername(username);
        spitter.setPassword("");

        return spitter;
    }

    private Spittle aSpittle(String message, Spitter spitter, Date date) {

        Spittle spittle = aSpittle(message, spitter);
        spittle.setWhenCreated(date);

        return spittle;
    }

    private Spittle aSpittle(String message, Spitter spitter) {

        Spittle spittle = new Spittle();
        spittle.setSpitter(spitter);
        spittle.setMessage(message);
        spittle.setWhenCreated(new Date());

        return spittle;
    }
}
