package com.rafalkalita.spitter.specification.steps;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JournaledStoriesSteps {

    private static final String JOURNAL_FIREFOX_COMMANDS = System.getProperty("JOURNAL_FIREFOX_COMMANDS", "false");

    @Inject
    private WebDriverProvider webDriverProvider;

    @AfterStories
    public void afterStories() throws Exception {

        if (!JOURNAL_FIREFOX_COMMANDS.equals("false") && webDriverProvider instanceof FirefoxWebDriverProvider) {
            FirefoxWebDriverProvider.WebDriverJournal journal = ((FirefoxWebDriverProvider) webDriverProvider).getJournal();
            System.out.println("Journal of WebDriver Commands:");
            for (Object entry : journal) {
                System.out.println(entry);
            }
            ((FirefoxWebDriverProvider) webDriverProvider).clearJournal();
        }

    }

}
