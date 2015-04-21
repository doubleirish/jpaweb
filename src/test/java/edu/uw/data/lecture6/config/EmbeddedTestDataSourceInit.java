package edu.uw.data.lecture6.config;

import org.apache.derby.jdbc.*;
import org.springframework.context.annotation.*;
import org.springframework.core.io.*;
import org.springframework.jdbc.datasource.init.*;

import javax.sql.*;
@Profile("dev")
//@PropertySource("classpath:dbtest.properties")
@Configuration
public class EmbeddedTestDataSourceInit {

//    @Autowired
//    private Environment environment;

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
      //  databasePopulator.addScript(new ClassPathResource("book_drop.sql"));
        databasePopulator.addScript(new ClassPathResource("book_create.sql"));
        databasePopulator.addScript(new ClassPathResource("book_insert.sql"));
        return databasePopulator;
    }

    private DataSource createDataSource() {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("tempDb");
        ds.setCreateDatabase("create");
        ds.setUser("app");
        ds.setPassword("app");

        return ds;
    }
}