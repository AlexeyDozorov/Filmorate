package ru.yandex.practicum.JDBC;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class JDBCconnectionService {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/cats";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "123";
    public static final String JDBC_DRIVER = "org.postgresql.Driver";

    public JdbcTemplate getTamplate(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
