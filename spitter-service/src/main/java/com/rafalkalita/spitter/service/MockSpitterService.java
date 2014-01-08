package com.rafalkalita.spitter.service;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: rafalkalita
 * Date: 11/12/2013
 * Time: 13:51
 */
@Component
public class MockSpitterService implements SpitterService {

    @Override
    public List<Spittle> getRecentSpittles(int count) {

        List<Spittle> spittles = new ArrayList<Spittle>();

        Spitter spitterOne = mockedSpitterOne();

        spittles.add(aSpittle(1L, spitterOne, "We have a decent weather today", new Date()));

        spittles.add(aSpittle(2L, spitterOne, "What should I do in the next hour?", new Date()));

        Spitter spitterTwo = mockedSpitterTwo();

        spittles.add(aSpittle(3L, spitterTwo, "I am going to be very productive today", new Date()));

        spittles.add(aSpittle(4L, spitterTwo, "Or maybe I will spend some time on the couch", new Date()));

        return spittles;
    }

    private Spitter mockedSpitterOne() {

        return aSpitter(1L, "Rafal", "1234", "Rafal Kalita");
    }

    private Spitter mockedSpitterTwo() {

        return aSpitter(2L, "John", "1234", "John Snow");
    }

    private Spittle aSpittle(Long id, Spitter spitter, String message, Date date) {

        Spittle spittle = new Spittle();

        spittle.setId(id);
        spittle.setSpitter(spitter);
        spittle.setMessage(message);
        spittle.setWhenCreated(date);

        return spittle;
    }

    private Spitter aSpitter(Long id, String username, String password, String fullname) {

        Spitter spitter = new Spitter();

        spitter.setId(id);
        spitter.setUsername(username);
        spitter.setPassword(password);
        spitter.setFullName(fullname);

        return spitter;
    }
}
