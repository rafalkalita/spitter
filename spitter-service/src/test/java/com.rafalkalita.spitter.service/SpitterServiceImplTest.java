package com.rafalkalita.spitter.service;

import com.rafalkalita.spitter.model.Spitter;

import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.persistence.SpitterDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpitterServiceImplTest {

    public static final String USERNAME = "John";
    public static final String FULLNAME = "John Black";

    public static final int ANY_NUMBER = 31;

    @InjectMocks
    private SpitterService instance = new SpitterServiceImpl();

    @Mock
    private SpitterDAO spitterDao;

    @Test
    public void shouldAddASpitter() {

        Spitter spitter = aNewSpitter(FULLNAME, USERNAME);

        instance.saveSpitter(spitter);

        verify(spitterDao).addSpitter(spitter);
    }

    @Test
    public void shouldSaveASpitter() {

        Spitter spitter = anOldSpitter(1L, FULLNAME, USERNAME);

        instance.saveSpitter(spitter);

        verify(spitterDao).saveSpitter(spitter);
    }

    @Test
    public void retrievesRecentSpittles() {

        when(spitterDao.getRecentSpittles(ANY_NUMBER)).thenReturn(aListOfSpittles());

        List<Spittle> spittles = instance.getRecentSpittles(ANY_NUMBER);

        assertEquals(aListOfSpittles(), spittles);
    }

    @Test
    public void retrievesASpitterByUsername() {

        when(spitterDao.getSpitterByUsername(USERNAME)).thenReturn(aNewSpitter(FULLNAME, USERNAME));

        Spitter outcome = instance.getSpitterByUsername(USERNAME);

        assertEquals(USERNAME, outcome.getUsername());
    }

    private Spitter anOldSpitter(Long id, String fullname, String username) {

        Spitter spitter = new Spitter();
        spitter.setId(id);
        spitter.setFullName(fullname);
        spitter.setUsername(username);

        return spitter;
    }

    private Spitter aNewSpitter(String fullname, String username) {

        Spitter spitter = new Spitter();
        spitter.setFullName(fullname);
        spitter.setUsername(username);

        return spitter;
    }

    private List<Spittle> aListOfSpittles() {
        List<Spittle> spittles = new ArrayList<Spittle>();
        spittles.add(new Spittle());
        return spittles;
    }
}