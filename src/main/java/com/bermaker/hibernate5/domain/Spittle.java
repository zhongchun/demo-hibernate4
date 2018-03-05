/**
 * @Title: Spittle.java
 * @Desc: TODO
 * @Package: com.bermaker.hibernate5.domain
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午5:37:20
 * @version 1.0
 */
package com.bermaker.hibernate5.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * ClassName: Spittle
 * 
 * @Desc: TODO
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午5:37:20
 * @version 1.0
 */
@Entity
public class Spittle {

    @SuppressWarnings("unused")
    private Spittle() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spitter")
    private Spitter spitter;

    @Column
    private String message;

    @Column
    private Date postedTime;

    public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.postedTime = postedTime;
    }

    public Long getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getPostedTime() {
        return this.postedTime;
    }

    public Spitter getSpitter() {
        return this.spitter;
    }

}
