package com.rafalkalita.spitter.specification.pages;

import com.rafalkalita.spitter.specification.exceptions.URLNotLoadingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:51
 */
public class Home extends FluentWebDriverPage {

    private static final Log logger = LogFactory.getLog(Home.class);
    private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter fmt1 = DateTimeFormat.forPattern("hh:mmaa MMM dd, yyyy");

    @Resource(name = "spitterProperties")
    private Properties spitterProperties;

    private JdbcTemplate jdbcTemplate;

    public Home(WebDriverProvider webDriverProvider, JdbcTemplate jdbcTemplate) {
        super(webDriverProvider);
        this.jdbcTemplate = jdbcTemplate;
    }

    public void go() {
        String url = "http://localhost:8080/spitter-web/";

        get(url);
        checkPageIsLoaded(url);
    }

    public void createAUser(String username) {

        if(!isUserRegistered(username)) {

            String sql = "INSERT INTO spitter(username, password, fullname) values(?, ?, ?)";

            Object[] params = new Object[] { username, "password", username};
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

            int row = jdbcTemplate.update(sql, params, types);

            logger.debug(row + " rows inserted.");
        }
    }

    public void postASpittle(String username, String message) {

        String date = new DateTime().toString("dd/MM/yyyy HH:mm:ss");

        postASpittle(username, message, date);
    }

    public void postASpittle(String username, String message, String date) {

        Date parsedDate = fmt.parseDateTime(date).toDate();
        Long userId = getUserId(username);
        String sql = "INSERT INTO spittle(spitter_id, message, whencreated) VALUES(?, ?, ?)";
        Object[] params = new Object[] { userId, message, parsedDate};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP };

        int row = jdbcTemplate.update(sql, params, types);

        logger.debug(row + " spittle posted(" + message + ") ");
    }

    public int numberOfSpittles() {
        return findElements(By.xpath("//div[@class='spittle']")).size();
    }

    public int numberOfSpittlesForAUser(String username) {
        return divs(By.xpath("@name='"+username+"'")).size();
    }

    public boolean allSpittlesAreOrderedDescending() {


        int numberOfElements = findElements(By.xpath("//small[@class='date']")).size();
        List<DateTime> dateTimeList = new ArrayList<DateTime>(numberOfElements);

        for(int i=0; i<numberOfElements;i++) {

            String date = findElements(By.xpath("//small[@class='date']")).get(i).getText();

            dateTimeList.add(fmt1.parseDateTime(date));
        }

        return isListOrdered(dateTimeList);
    }

    public void clickLinkWithUsername(String username) {
        findElements(By.xpath("//a[@class='spitterlink']")).get(0).click();
    }

    private boolean isListOrdered(List<DateTime> dateTimeList) {

        DateTime firstElement = dateTimeList.get(0);

        for(DateTime element : dateTimeList) {

            if(element.compareTo(firstElement) < 0) {
                logger.info("Dates are not in order: " + dateTimeList);
                return false;
            }
            firstElement = element;
        }
        return true;
    }

    private boolean isUserRegistered(String username) {
        return 0 != countRowsInTableWhere(jdbcTemplate, "spitter", "username='" + username + "'");
    }

    private Long getUserId(String username) {

        String sql = "SELECT id FROM spitter WHERE username='" + username + "'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private void checkPageIsLoaded(String url) {
        if(findElements(By.xpath("//body[@id='spitter']")).size() == 0) {
            logger.error("Cannot load url: " + url + " Check if server is started.");
            throw new URLNotLoadingException();
        }
    }
}
