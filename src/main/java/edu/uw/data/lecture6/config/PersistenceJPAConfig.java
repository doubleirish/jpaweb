package edu.uw.data.lecture6.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.*;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.*;

import javax.sql.*;
import java.util.*;


@Configuration
@PropertySource("classpath:dbprod.properties")
@ComponentScan(basePackages = {"edu.uw.data.lecture6.dao","edu.uw.data.lecture6.model"})
public class PersistenceJPAConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = 
				new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("edu.uw.data.lecture6.model");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	private Properties additionalProperties() {
		Properties p = new Properties();
		p.setProperty("hibernate.show_sql", "true");
		//p.setProperty("hibernate.hbm2ddl.auto", "validate");
		p.setProperty("hibernate.dialect", environment.getProperty("db.dialect"));
		return p;
	}

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject() );
        return transactionManager;
    }






}
