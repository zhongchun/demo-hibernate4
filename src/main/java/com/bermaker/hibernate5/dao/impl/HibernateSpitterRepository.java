/**
 * @Title: HibernateSpitterRepository.java
 * @Desc: TODO
 * @Package: com.bermaker.hibernate5.db.impl
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午5:55:04
 * @version 1.0
 */
package com.bermaker.hibernate5.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bermaker.hibernate5.dao.SpitterRepository;
import com.bermaker.hibernate5.domain.Spitter;

/**
 * ClassName: HibernateSpitterRepository
 * 
 * @Desc: TODO
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午5:55:04
 * @version 1.0
 */
@Repository
public class HibernateSpitterRepository implements SpitterRepository {

    private SessionFactory sessionFactory;

    /**
     * @Title: HibernateSpitterRepository
     * @Desc: TODO
     */
    @Inject
    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * @Title: count
     * @Desc:
     * @return
     * @see com.bermaker.hibernate5.dao.SpitterRepository#count()
     */
    @Override
    public long count() {
        return findAll().size();
    }

    /**
     * @Title: save
     * @Desc:
     * @param spitter
     * @return
     * @see com.bermaker.hibernate5.dao.SpitterRepository#save(com.bermaker.hibernate5.domain.Spitter)
     */
    @Override
    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter((Long) id, spitter.getUsername(), spitter.getPassword(), spitter.getFullName(),
                spitter.getEmail(), spitter.isUpdateByEmail());
    }

    /**
     * @Title: findOne
     * @Desc:
     * @param id
     * @return
     * @see com.bermaker.hibernate5.dao.SpitterRepository#findOne(long)
     */
    @Override
    public Spitter findOne(long id) {
        return currentSession().get(Spitter.class, id);
    }

    /**
     * @Title: findByUsername
     * @Desc:
     * @param username
     * @return
     * @see com.bermaker.hibernate5.dao.SpitterRepository#findByUsername(java.lang.String)
     */
    @Override
    public Spitter findByUsername(String username) {
        return (Spitter) currentSession().createCriteria(Spitter.class).add(Restrictions.eq("username", username))
                .list().get(0);
    }

    /**
     * @Title: findAll
     * @Desc:
     * @return
     * @see com.bermaker.hibernate5.dao.SpitterRepository#findAll()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Spitter> findAll() {
        return currentSession().createCriteria(Spitter.class).list();
    }

}
