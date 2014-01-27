package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 22:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:dataSource-test-context.xml",
        "classpath:spring/database/hibernate.xml",
        "classpath:spring/transaction/transaction.xml"
})

@TransactionConfiguration(transactionManager = "txMgr", defaultRollback = true)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public class SpitterDAOTest {

    @Inject
    private SpitterDAO dao;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @After
    public void cleanup() {
        deleteFromTables(jdbcTemplate, "spittle");
        deleteFromTables(jdbcTemplate, "spitter");
    }

    @Test
    public void createsRowsAndSetIds() {

        assertEquals(0, countRowsInTable(jdbcTemplate, "spitter"));
        insertASpitter("username", "password", "fullname");

        assertEquals(1, countRowsInTable(jdbcTemplate, "spitter"));

        insertASpitter("username2", "password2", "fullname2");
        assertEquals(2, countRowsInTable(jdbcTemplate, "spitter"));
    }

    @Test
    public void savesModifiedSpitter() {

        String newPassword = "newPassword";

        Spitter spitterIn = insertASpitter("username", "password", "fullname");

        spitterIn.setPassword(newPassword);
        dao.saveSpitter(spitterIn);

        Spitter spitterOut = dao.getSpitterById(spitterIn.getId());
        assertEquals(newPassword, spitterOut.getPassword());
    }

    @Test
    public void addsSpittle() {

        Spitter spitter = insertASpitter("username", "password", "fullname");

        Spittle spittle = aSpittle(spitter, "message");

        assertNull(spittle.getId());
        dao.addSpittle(spittle);
        assertNotNull(spittle.getId());

    }

    @Test
    public void findsInsertedSpitter() {

        Spitter spitterIn = insertASpitter("username", "password", "fullname");

        Spitter spitterOut = dao.getSpitterById(spitterIn.getId());

        assertEquals(spitterIn, spitterOut);
    }

    @Test
    public void findInsertedSpitterByName() {

        Spitter spitterIn = insertASpitter("username", "password", "fullname");

        Spitter spitterOut = dao.getSpitterByUsername(spitterIn.getUsername());

        assertEquals(spitterIn, spitterOut);
    }

    @Test
    public void retrievesAListOfSpittles() {

        Spitter spitter = insertASpitter("username", "password", "fullname");

        Spittle spittle1 = aSpittle(spitter, "message1");
        Spittle spittle2 = aSpittle(spitter, "message2");
        Spittle spittle3 = aSpittle(spitter, "message3");

        dao.addSpittle(spittle1);
        dao.addSpittle(spittle2);
        dao.addSpittle(spittle3);

        List<Spittle> spittles = dao.getRecentSpittles(3);

        List<Spittle> expectedSpittles = Arrays.asList(spittle1, spittle2, spittle3);

        assertEquals(expectedSpittles, spittles);
    }

    private Spitter insertASpitter(String username, String password, String fullname) {

        Spitter spitter = new Spitter();

        spitter.setUsername(username);
        spitter.setPassword(password);
        spitter.setFullName(fullname);

        assertNull(spitter.getId());
        dao.addSpitter(spitter);
        assertNotNull(spitter.getId());

        return spitter;
    }

    private Spittle aSpittle(Spitter spitter, String message) {

        Spittle spittle = new Spittle();

        spittle.setWhenCreated(new Date());
        spittle.setMessage(message);
        spittle.setSpitter(spitter);
        return spittle;
    }
}
