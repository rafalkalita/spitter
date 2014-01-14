package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.model.Spitter;

import com.rafalkalita.spitter.model.Spittle;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 22:21
 */
@Repository("spitterDao")
public class HibernateSpitterDAO implements SpitterDAO {

    @Inject
    private SessionFactory sessionFactory;

    public HibernateSpitterDAO() {}

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addSpitter(Spitter spitter) {
        currentSession().save(spitter);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        currentSession().update(spitter);
    }

    public Spitter getSpitterById(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Spittle> getRecentSpittles(int count) {
        return currentSession().createCriteria(Spittle.class).list();
    }

    @Override
    public void saveSpittle(Spittle spittle) {
        currentSession().save(spittle);
    }
}
