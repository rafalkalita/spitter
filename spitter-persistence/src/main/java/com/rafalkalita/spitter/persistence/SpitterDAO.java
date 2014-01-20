package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;

import java.util.List;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 21:44
 */
public interface SpitterDAO {

    void addSpitter(Spitter spitter);

    void saveSpitter(Spitter spitter);

    Spitter getSpitterById(long id);

    List<Spittle> getRecentSpittles(int count);

    void saveSpittle(Spittle spittle);

    Spitter getSpitterByUsername(String username);
}
