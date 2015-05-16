package edu.uw.data.lecture6.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.*;

/**
 * JAVA CONFIG
 */
//@Configuration
//@Profile("dev")
public class EmbeddedTestDataSource {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.DERBY)
                .addScript("classpath:create_book.sql")
                .addScript("classpath:insert_book.sql")
                .build();
    }
}