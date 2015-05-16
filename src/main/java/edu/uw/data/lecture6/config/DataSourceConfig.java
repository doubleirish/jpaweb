package edu.uw.data.lecture6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * JAVA CONFIG
 */
//@Configuration
//@PropertySource("classpath:dbprod.properties")
public class DataSourceConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("db.driver"));
        ds.setUrl(environment.getProperty("db.url"));
        ds.setUsername(environment.getProperty("db.username"));
        ds.setPassword(environment.getProperty("db.password"));
        return ds;
    }


// container based datasource
//        @Bean
//        public DataSource dataSource() throws Exception {
//            Context ctx = new InitialContext();
//            return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
//        }
//    }

}
