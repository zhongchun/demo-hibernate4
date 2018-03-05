/**
 * @Title: RepositoryTestConfig.java
 * @Desc: TODO
 * @Package: com.bermaker.hibernate5
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午6:09:00
 * @version 1.0
 */
package com.bermaker.hibernate5;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: RepositoryTestConfig
 * 
 * @Desc: TODO
 * @author: yuzhongchun(yuzhongchun@baidu.com)
 * @date: 2018年3月2日 下午6:09:00
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
public class RepositoryTestConfig {

    @Inject
    private SessionFactory sessionFactory;

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        edb.addScript("classpath:db/schema.sql");
        edb.addScript("classpath:db/test-data.sql");
        EmbeddedDatabase embeddedDatabase = edb.build();
        return embeddedDatabase;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        System.out.println("==== platform transaction manager ====");
        System.out.println(sessionFactory);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public SessionFactory sessionFactoryBean() {
        try {
            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
            lsfb.setDataSource(dataSource());
            lsfb.setPackagesToScan("com.bermaker.hibernate5.domain");
            Properties props = new Properties();
            props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
            lsfb.setHibernateProperties(props);

            lsfb.afterPropertiesSet();

            SessionFactory object = lsfb.getObject();
            return object;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
