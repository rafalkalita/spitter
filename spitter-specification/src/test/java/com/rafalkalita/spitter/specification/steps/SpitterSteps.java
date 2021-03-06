package com.rafalkalita.spitter.specification.steps;

import com.rafalkalita.spitter.specification.pages.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.annotations.*;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

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

    private static final Log logger = LogFactory.getLog(SpitterSteps.class);

    private Home home;
    private Registration registration;
    private Forms forms;
    private UserPage userPage;

    @Inject
    JdbcTemplate jdbcTemplate;

    @BeforeScenario
    public void setUp() {
        try {
            deleteFromTables(jdbcTemplate, "spittle");
            deleteFromTables(jdbcTemplate, "spitter");
        } catch(CannotGetJdbcConnectionException e) {
            logger.error("Cannot connect to the database.\n" + e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @AfterScenario
    public void cleanUp() {
        deleteFromTables(jdbcTemplate, "spittle");
        deleteFromTables(jdbcTemplate, "spitter");
    }

    @Inject
    public SpitterSteps(PageFactory pageFactory) {
        this.home = pageFactory.newHome();
        this.registration = pageFactory.newRegistrationPage();
        this.forms = pageFactory.newForms();
        this.userPage = pageFactory.newUserPage();
    }

    @Given("$user is a user with an account")
    @Alias("And $user is a user with an account")
    public void userHasAnAccount(String user) {
        logger.info("creating a user: " + user);
        home.createAUser(user);
    }

    @Given("user $user posted $numberOfSpittles spittles")
    public void userPostedXSpittles(String user, int numberOfSpittles) {
        for(int i=0; i< numberOfSpittles;i++) {
            home.postASpittle(user, "message" + i);
        }
    }

    @Given("user $user posted a spittle on '$date'")
    public void userPostedASpittleOn(String user, String date) {
        home.postASpittle(user, "message", date);
    }

    @When("I navigate to the home page")
    public void navigateToHomePage() {
        home.go();
    }

    @When("click on username $username")
    public void clickOnAUsername(String username) {
        home.clickLinkWithUsername(username);
    }

    @Then("I see $number spittles")
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

    @When("I navigate to the registration page")
    public void navigateToRegistrationPage() {
        registration.go();
    }

    @When("I put value $value in field $fieldId")
    public void formFieldContainsValue(String value, String fieldId) {
        forms.fillTextField(value, fieldId);
    }

    @When("I submit $formId form")
    public void submitTheForm(String formId) {
        forms.submitForm(formId);
    }

    @Then("I will be navigated to user page")
    public void navigatedToUserPage() {
        userPage.checkIfNavigatedTo();
    }
}
