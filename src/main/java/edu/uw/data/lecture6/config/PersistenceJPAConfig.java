package edu.uw.data.lecture6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:dbprod.properties")
@ComponentScan(basePackages = {"edu.uw.data.lecture6.dao","edu.uw.data.lecture6.model"})
@EnableCaching
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


    p.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
    p.setProperty("hibernate.cache.use_second_level_cache", "true");
    p.setProperty("hibernate.cache.use_query_cache",  "true");
    p.setProperty("hibernate.generate_statistics",  "true");


		return p;
	}

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject() );
        return transactionManager;
    }


  @Bean
  	public CacheManager cacheManager() {
  		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
  	}

  	@Bean
  	public EhCacheManagerFactoryBean ehCacheCacheManager() {
  		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
  		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
  		cmfb.setShared(true);
  		return cmfb;
  	}






}
