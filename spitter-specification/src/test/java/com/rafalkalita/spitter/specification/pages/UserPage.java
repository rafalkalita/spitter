package com.rafalkalita.spitter.specification.pages;

import com.rafalkalita.spitter.specification.exceptions.URLNotLoadingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

/**
 * User: rafalkalita
 * Date: 02/02/2014
 * Time: 19:53
 */
public class UserPage extends FluentWebDriverPage {

    private static final Log logger = LogFactory.getLog(UserPage.class);

    public UserPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void checkIfNavigatedTo() {
        if(findElements(By.xpath("//div[@id='spittlesList']")).size() == 0) {
            logger.error("User page is not loaded.");
            throw new URLNotLoadingException();
        }
    }
}
