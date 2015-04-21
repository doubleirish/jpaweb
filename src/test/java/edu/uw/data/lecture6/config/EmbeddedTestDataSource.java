package edu.uw.data.lecture6.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.*;

/**
 * Created by Conor on 4/12/2015.
 */
@Configuration
@Profile("dev")
public class EmbeddedTestDataSource {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.DERBY)
                .addScript("classpath:book_create.sql")
                .addScript("classpath:book_insert.sql")
                .build();
    }
}