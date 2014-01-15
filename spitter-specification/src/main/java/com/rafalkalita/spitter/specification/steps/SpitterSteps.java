package com.rafalkalita.spitter.specification.steps;

import com.rafalkalita.spitter.specification.pages.Home;
import com.rafalkalita.spitter.specification.pages.PageFactory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Date;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:49
 */
public class SpitterSteps {

    private Home home;

    public SpitterSteps(PageFactory pageFactory) {
        this.home = pageFactory.newHome();
    }

    @Given("$user is a user with an account")
    public void userHasAnAccount(String user) {

    }

    @Given("user $user posted $numberOfSpittles spittles")
    public void userPostedXSpittles(String user, int numberOfSpittles) {

    }

    @Given("defaultSpittlesPerPage is not defined")
    public void defaultSpittlesPerPageIsNotDefined() {

    }

    @Given("defaultSpittlesPerPage is $number")
    public void defaultSpittlesPerPageIs(int number) {

    }

    @Given("user $user posted a spittle on '$date'")
    public void userPostedASpittleOn(String user, Date date) {

    }

    @When("I navigate to the home page")
    public void navigateToHomePage() {

    }

    @Then("I see $number spittles on the home page")
    public void theNumberOfSpittlesOnTheHomePageIs(int number) {

    }

    @Then("$number of the spittles belong to user $user")
    public void userHasXSpittles(int number, String user) {

    }

    @Then("all spittles are ordered descending")
    public void allSpittlesAreOrderedDescending() {

    }
}
