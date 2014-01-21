package com.rafalkalita.spitter.specification.pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.Date;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:51
 */
public class Home extends FluentWebDriverPage {

    private static final Log logger = LogFactory.getLog(Home.class);

    private JdbcTemplate jdbcTemplate;

    public Home(WebDriverProvider webDriverProvider, JdbcTemplate jdbcTemplate) {
        super(webDriverProvider);
        this.jdbcTemplate = jdbcTemplate;
    }

    public void go() {
        get("http://localhost:8080/spitter-web/");
    }

    public void createAUser(String username) {

        if(!isUserRegistered(username)) {

            String sql = "INSERT INTO spitter(username, password, fullname) values(?, ?, ?)";

            Object[] params = new Object[] { username, "password", username};
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

            int row = jdbcTemplate.update(sql, params, types);

            logger.info(row + " rows inserted.");
        }
        Long userId = getUserId(username);
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

        logger.info(row + "rows inserted");
        logger.info(row + " spittle posted(" + message + ") ");
    }

    public void setDefaultSpittlesPerPage(int number) {
        // TODO: finish
    }

    public int numberOfSpittles() {
        return divs(By.className("spittle")).size();
    }

    public int numberOfSpittlesForAUser(String user) {
        return 0; // TODO: finish
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
}
