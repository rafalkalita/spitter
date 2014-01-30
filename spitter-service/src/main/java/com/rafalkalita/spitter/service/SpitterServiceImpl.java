package com.rafalkalita.spitter.service;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.persistence.SpitterDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;

/**
 * User: rafalkalita
 * Date: 10/01/2014
 * Time: 15:45
 */
@Service("spitterService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class SpitterServiceImpl implements SpitterService {

    @Inject
    private SpitterDAO spitterDao;

    public SpitterServiceImpl() {}

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Spittle> getRecentSpittles(int count) {

        return spitterDao.getRecentSpittles(count);
    }

    @Override
    public void saveSpittle(Spittle spittle) {

        spittle.setWhenCreated(new Date());
        spitterDao.addSpittle(spittle);
    }

    @Override
    public void saveSpitter(Spitter spitter) {

        if(spitter.getId() == null) {
            spitterDao.addSpitter(spitter);
        } else {
            spitterDao.saveSpitter(spitter);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Spitter getSpitterByUsername(String username) {
        return spitterDao.getSpitterByUsername(username);
    }

    @Override
    public List<Spittle> getSpittlesForSpitter(String username) {
        return spitterDao.getSpittlesForSpitter(username);
    }
}
