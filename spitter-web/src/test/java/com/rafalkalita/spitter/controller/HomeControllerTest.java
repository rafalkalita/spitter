package com.rafalkalita.spitter.controller;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    private HomeController instance;

    @Mock
    private SpitterService spitterService;

    private Map<String, Object> model = new HashMap<String, Object>();

    @Mock
    private Spitter anySpitter;

    private Date date;

    @Test
    public void returnsHomeView() {

        date = new Date();

        when(spitterService.getRecentSpittles(10)).thenReturn(twoSpittles());

        instance = new HomeController(spitterService);
        String outcome = instance.showHomeView(model);

        verify(spitterService).getRecentSpittles(10);

        assertEquals(twoSpittles(), model.get("spittles"));
        assertEquals("home", outcome);

    }

    private List<Spittle> twoSpittles() {

        List<Spittle> spittles = new ArrayList<Spittle>();

        spittles.add(new Spittle(1L, anySpitter, "It is a beautiful day", date));
        spittles.add(new Spittle(2L, anySpitter, "It is a beautiful day", date));

        return spittles;
    }
}
