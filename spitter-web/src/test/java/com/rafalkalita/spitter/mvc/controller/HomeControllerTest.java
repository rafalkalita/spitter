package com.rafalkalita.spitter.mvc.controller;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: rafalkalita
 * Date: 20/11/2013
 * Time: 17:55
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController instance = new HomeController();

    @Mock
    private SpitterService spitterService;

    private Map<String, Object> model = new HashMap<String, Object>();

    @Mock
    private Spitter anySpitter;

    private Date date;

    @Test
    public void returnsHomeView() {

        date = new Date();

        when(spitterService.getRecentSpittles(25)).thenReturn(twoSpittles());

        String outcome = instance.showHomeView(model);

        verify(spitterService).getRecentSpittles(25);

        assertEquals(twoSpittles(), model.get("spittles"));
        assertEquals("home", outcome);

    }

    private List<Spittle> twoSpittles() {

        List<Spittle> spittles = new ArrayList<Spittle>();

        spittles.add(aSpittle(1L, anySpitter, "It is a beautiful day", date));
        spittles.add(aSpittle(2L, anySpitter, "It is a beautiful day", date));

        return spittles;
    }

    private Spittle aSpittle(Long id, Spitter spitter, String message, Date date) {

        Spittle spittle = new Spittle();

        spittle.setId(id);
        spittle.setSpitter(spitter);
        spittle.setMessage(message);
        spittle.setWhenCreated(date);

        return spittle;
    }
}
