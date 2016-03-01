package com.grinyov.bulletinboard.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Configuration
@ComponentScan("com.grinyov.bulletinboard")
@EnableTransactionManagement
public class DataConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/bulletin_board");
    	dataSource.setUsername("root");
    	dataSource.setPassword("weber123");

    	return dataSource;
    }


    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }

    @Autowired
    @Bean(name = "sessionFactory")
	@SuppressWarnings("deprecation")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	//sessionBuilder.addAnnotatedClasses(Advert.class, Account.class, Category.class);
		sessionBuilder.scanPackages("com.grinyov.bulletinboard");
    	return sessionBuilder.buildSessionFactory();
    }

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}

    /*@Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
    	return new UserDAOImpl(sessionFactory);
    }*/
}



