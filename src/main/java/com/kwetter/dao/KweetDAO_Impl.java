package com.kwetter.dao;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */
@LocalBean
@Stateless
public class KweetDAO_Impl implements KweetDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public void create(Kweet k,User user) {
        em.persist(k);
    }

    @Override
    public void edit(Kweet k) {
        em.merge(k);
    }

    @Override
    public void removeKweet(Kweet k,User user) {
        em.remove(k);
    }

    @Override
    public List<Kweet> findAll() {
        return (List<Kweet>) em.createNamedQuery("Kweet.findAll", Kweet.class).getResultList();
    }


}
