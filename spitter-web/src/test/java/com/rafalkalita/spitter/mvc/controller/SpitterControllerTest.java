package com.rafalkalita.spitter.mvc.controller;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * User: rafalkalita
 * Date: 30/01/2014
 * Time: 18:26
 */
@RunWith(MockitoJUnitRunner.class)
public class SpitterControllerTest {

    @InjectMocks
    SpitterController instance = new SpitterController();

    @Mock
    private SpitterService service;


    private ExtendedModelMap model = new ExtendedModelMap();

    @Test
    public void producesSpittlesForSpitter() {

        Spitter spitter = aSpitter();
        List<Spittle> spittles = listOfTwoSpittles();

        when(service.getSpitterByUsername(spitter.getUsername())).thenReturn(spitter);
        when(service.getSpittlesForSpitter(spitter.getUsername())).thenReturn(spittles);

        String view = instance.displaySpittlesForSpitter(spitter.getUsername(), model);

        assertEquals("spittles/list", view);
        assertEquals(spitter, model.get("spitter"));
        assertEquals(spittles, model.get("spittleList"));
    }

    @Test
    public void displaysCreateNewSpitterForm() {

        String view = instance.showNewSpitterForm(model);

        assertEquals("spitters/edit", view);
        assertTrue(model.containsAttribute("spitter"));
        assertNotNull((Spitter)model.get("spitter"));
    }

    private List<Spittle> listOfTwoSpittles() {

        List<Spittle> spittles = new ArrayList<Spittle>();

        Spittle spittle1 = new Spittle();
        spittle1.setMessage("message1");
        Spittle spittle2 = new Spittle();
        spittle2.setMessage("message2");

        spittles.add(spittle1);
        spittles.add(spittle2);

        return spittles;
    }

    private Spitter aSpitter() {

        Spitter spitter = new Spitter();

        spitter.setId(1L);
        spitter.setUsername("John");
        spitter.setFullName("John Downs");
        spitter.setPassword("pass");

        return spitter;
    }
}
