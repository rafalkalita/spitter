package com.rafalkalita.spitter.specification.steps;

import com.rafalkalita.spitter.specification.pages.Home;
import com.rafalkalita.spitter.specification.pages.PageFactory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:49
 */
@Component
public class SpitterSteps {

    private Home home;

    @Inject
    JdbcTemplate jdbcTemplate;

    @BeforeScenario
    public void setUp() {

        deleteFromTables(jdbcTemplate, "spittle");
        deleteFromTables(jdbcTemplate, "spitter");
    }

    @Inject
    public SpitterSteps(PageFactory pageFactory) {
        this.home = pageFactory.newHome();
    }

    @Given("$user is a user with an account")
    public void userHasAnAccount(String user) {
        home.createAUser(user);
    }

    @Given("user $user posted $numberOfSpittles spittles")
    public void userPostedXSpittles(String user, int numberOfSpittles) {
        for(int i=0; i< numberOfSpittles;i++) {
            home.postASpittle(user, "message" + i);
        }
    }

    @Given("defaultSpittlesPerPage is $number")
    public void defaultSpittlesPerPageIs(int number) {
        home.setDefaultSpittlesPerPage(number);
    }

    @Given("user $user posted a spittle on '$date'")
    public void userPostedASpittleOn(String user, Date date) {
        home.postASpittle(user, "message", date);
    }

    @When("I navigate to the home page")
    public void navigateToHomePage() {
        home.go();
    }

    @Then("I see $number spittles on the home page")
    public void theNumberOfSpittlesOnTheHomePageIs(int number) {
        assertEquals(number, home.numberOfSpittles());
    }

    @Then("$number of the spittles belong to user $user")
    public void userHasXSpittles(int number, String user) {
        assertEquals(number, home.numberOfSpittlesForAUser(user));
    }

    @Then("all spittles are ordered descending")
    public void allSpittlesAreOrderedDescending() {
        assertTrue(home.allSpittlesAreOrderedDescending());
    }
}
