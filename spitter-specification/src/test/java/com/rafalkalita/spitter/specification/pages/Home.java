package com.rafalkalita.spitter.specification.pages;

import com.rafalkalita.spitter.specification.exceptions.URLNotLoadingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Date;
import java.util.Properties;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:51
 */
public class Home extends FluentWebDriverPage {

    private static final Log logger = LogFactory.getLog(Home.class);

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

        postASpittle(username, message, new Date());
    }

    public void postASpittle(String username, String message, Date date) {

        Long userId = getUserId(username);
        String sql = "INSERT INTO spittle(spitter_id, message, whencreated) VALUES(?, ?, ?)";
        Object[] params = new Object[] { userId, message, date};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP };

        int row = jdbcTemplate.update(sql, params, types);

        logger.debug(row + " spittle posted(" + message + ") ");
    }

    public int numberOfSpittles() {
        return divs(By.xpath("//div[@class='spittle']")).size();
    }

    public int numberOfSpittlesForAUser(String username) {
        return divs(By.xpath("@name='"+username+"'")).size();
    }

    public boolean allSpittlesAreOrderedDescending() {
        return false; // TODO: finish
    }

    private boolean isUserRegistered(String username) {
        return 0 != countRowsInTableWhere(jdbcTemplate, "spitter", "username='" + username + "'");
    }

    private Long getUserId(String username) {

        String sql = "SELECT id FROM spitter WHERE username='" + username + "'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private void checkPageIsLoaded(String url) {
        if(divs(By.xpath("//body[@id='spitter']")).size() == 0) {
            logger.error("Cannot load url: " + url + " Check if server is started.");
            throw new URLNotLoadingException();
        }
    }
}
