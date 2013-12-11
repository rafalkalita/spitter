package com.rafalkalita.spitter.service;

import com.rafalkalita.spitter.domain.Spitter;
import com.rafalkalita.spitter.domain.Spittle;
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

        spittles.add(new Spittle(1L, spitterOne, "We have a decent weather today", new Date()));
        spitterOne.setSpittles(spittles);

        spittles.add(new Spittle(2L, spitterOne, "What should I do in the next hour?", new Date()));
        spitterOne.setSpittles(spittles);

        Spitter spitterTwo = mockedSpitterTwo();

        spittles.add(new Spittle(3L, spitterTwo, "I am going to be very productive today", new Date()));
        spitterOne.setSpittles(spittles);

        spittles.add(new Spittle(4L, spitterTwo, "Or maybe I will spend some time on the couch", new Date()));
        spitterOne.setSpittles(spittles);

        return spittles;
    }

    public Spitter mockedSpitterOne() {

        return new Spitter(1L, "Rafal", "1234", "Rafal Kalita", null);
    }

    public Spitter mockedSpitterTwo() {

        return new Spitter(2L, "John", "1234", "John Snow", null);
    }
}
