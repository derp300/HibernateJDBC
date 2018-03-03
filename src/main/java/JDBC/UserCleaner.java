package JDBC;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserCleaner {
    private final JdbcTemplate jdbcTemplate;

    public UserCleaner(final DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void cleanTables() {
        jdbcTemplate.update("DELETE FROM users");
    }
}
