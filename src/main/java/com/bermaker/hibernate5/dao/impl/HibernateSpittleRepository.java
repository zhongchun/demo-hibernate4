/**
 * @Title: HibernateSpittleRepository.java
 * @Desc: TODO
 * @Package: com.bermaker.hibernate5.dao.impl
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月5日 下午3:31:09
 * @version 1.0
 */
package com.bermaker.hibernate5.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bermaker.hibernate5.dao.SpittleRepository;
import com.bermaker.hibernate5.domain.Spittle;

/**
 * ClassName: HibernateSpittleRepository
 * 
 * @Desc: TODO
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月5日 下午3:31:09
 * @version 1.0
 */
@Repository
public class HibernateSpittleRepository implements SpittleRepository {

    private SessionFactory sessionFactory;

    /**
     * @Title: HibernateSpittleRepository
     * @Desc: TODO
     */
    @Inject
    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria spittleCriteria() {
        return currentSession().createCriteria(Spittle.class).addOrder(Order.desc("postedTime"));
    }

    @SuppressWarnings("unchecked")
    public List<Spittle> findAll() {
        return spittleCriteria().list();
    }

    /**
     * @Title: count
     * @Desc:
     * @return
     * @see com.bermaker.hibernate5.dao.SpittleRepository#count()
     */
    @Override
    public long count() {
        return findAll().size();
    }

    /**
     * @Title: findRecent
     * @Desc:
     * @return
     * @see com.bermaker.hibernate5.dao.SpittleRepository#findRecent()
     */
    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    /**
     * @Title: findRecent
     * @Desc:
     * @param count
     * @return
     * @see com.bermaker.hibernate5.dao.SpittleRepository#findRecent(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Spittle> findRecent(int count) {
        return spittleCriteria().setMaxResults(count).list();
    }

    /**
     * @Title: findOne
     * @Desc:
     * @param id
     * @return
     * @see com.bermaker.hibernate5.dao.SpittleRepository#findOne(long)
     */
    @Override
    public Spittle findOne(long id) {
        return currentSession().get(Spittle.class, id);
    }

    /**
     * @Title: save
     * @Desc:
     * @param spittle
     * @return
     * @see com.bermaker.hibernate5.dao.SpittleRepository#save(com.bermaker.hibernate5.domain.Spittle)
     */
    @Override
    public Spittle save(Spittle spittle) {
        Serializable id = currentSession().save(spittle);
        return new Spittle((Long) id, spittle.getSpitter(), spittle.getMessage(), spittle.getPostedTime());
    }

    /**
     * @Title: findBySpitterId
     * @Desc:
     * @param spitterId
     * @return
     * @see com.bermaker.hibernate5.dao.SpittleRepository#findBySpitterId(long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Spittle> findBySpitterId(long spitterId) {
        return spittleCriteria().add(Restrictions.eq("spitter.id", spitterId)).list();
    }

    /**
     * @Title: delete
     * @Desc:
     * @param id
     * @see com.bermaker.hibernate5.dao.SpittleRepository#delete(long)
     */
    @Override
    public void delete(long id) {
        currentSession().delete(findOne(id));
    }

}
