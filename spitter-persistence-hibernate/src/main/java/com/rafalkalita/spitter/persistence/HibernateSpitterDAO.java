package com.rafalkalita.spitter.persistence;

import com.rafalkalita.spitter.domain.Spitter;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * User: rafalkalita
 * Date: 05/01/2014
 * Time: 22:21
 */
@Repository
public class HibernateSpitterDAO implements SpitterDAO {

    @Inject
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addSpitter(Spitter spitter) {
        currentSession().save(spitter);
    }

    @Override
    public Spitter getSpitterById(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }
}
