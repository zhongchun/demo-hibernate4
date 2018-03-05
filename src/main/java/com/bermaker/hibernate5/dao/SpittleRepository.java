/**
 * @Title: SpittleRepository.java
 * @Desc: TODO
 * @Package: com.bermaker.hibernate5.dao
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月5日 下午3:28:55
 * @version 1.0
 */
package com.bermaker.hibernate5.dao;

import java.util.List;

import com.bermaker.hibernate5.domain.Spittle;

/**
 * ClassName: SpittleRepository
 * 
 * @Desc: TODO
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月5日 下午3:29:31
 * @version 1.0
 */
public interface SpittleRepository {

    long count();

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

    Spittle findOne(long id);

    Spittle save(Spittle spittle);

    List<Spittle> findBySpitterId(long spitterId);

    void delete(long id);

}
