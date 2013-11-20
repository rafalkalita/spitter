package com.rafalkalita.spitter.service;

import com.rafalkalita.spitter.domain.Spittle;

import java.util.List;

/**
 * User: rafalkalita
 * Date: 20/11/2013
 * Time: 18:54
 */
public interface SpitterService {

    List<Spittle> getRecentSpittles(int count);
}
