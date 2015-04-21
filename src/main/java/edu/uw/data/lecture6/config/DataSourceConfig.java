package edu.uw.data.lecture6.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.*;
import org.springframework.jdbc.datasource.*;

import javax.sql.*;

/**
 * Created by Conor on 4/12/2015.
 */
@Configuration
@PropertySource("classpath:dbprod.properties")
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
