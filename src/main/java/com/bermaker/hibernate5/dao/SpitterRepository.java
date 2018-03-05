/**
 * @Title: SpitterRepository.java
 * @Desc: TODO
 * @Package: com.bermaker.hibernate5.db
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午5:53:28
 * @version 1.0
 */
package com.bermaker.hibernate5.dao;

import java.util.List;

import com.bermaker.hibernate5.domain.Spitter;

/**
 * ClassName: SpitterRepository
 * 
 * @Desc: TODO
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午5:53:28
 * @version 1.0
 */
public interface SpitterRepository {

    long count();

    Spitter save(Spitter spitter);

    Spitter findOne(long id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();

}
