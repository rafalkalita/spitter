package com.rafalkalita.spitter.service;

import com.rafalkalita.spitter.model.Spitter;

import com.rafalkalita.spitter.persistence.SpitterDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpitterServiceImplTest {

    @InjectMocks
    private SpitterService instance = new SpitterServiceImpl();

    @Mock
    private SpitterDAO spitterDao;

    @Test
    public void shouldAddASpitter() {

        Spitter spitter = aNewSpitter();

        instance.saveSpitter(spitter);

        Mockito.verify(spitterDao).addSpitter(spitter);
    }

    @Test
    public void shouldSaveASpitter() {

        Spitter spitter = anOldSpitter();

        instance.saveSpitter(spitter);

        Mockito.verify(spitterDao).saveSpitter(spitter);
    }

    private Spitter anOldSpitter() {

        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setFullName("John Black");
        spitter.setUsername("John");

        return spitter;
    }

    private Spitter aNewSpitter() {

        Spitter spitter = new Spitter();
        spitter.setFullName("John Black");
        spitter.setUsername("John");

        return spitter;
    }
}