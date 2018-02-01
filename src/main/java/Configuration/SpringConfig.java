package Configuration;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import JDBC.UserCleaner;
import JDBC.UserDAO;
import JDBC.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "Hibernate" })
public class SpringConfig {
    @Bean
    @Autowired
    UserDAO userDAO(DataSource dataSource) {
        return new UserDAO(dataSource);
    }

    @Bean
    @Autowired
    UserCleaner userCleaner(DataSource dataSource) {
        return new UserCleaner(dataSource);
    }

    @Bean
    @Autowired
    UserService userService(UserDAO userDAO) {
        return new UserService(userDAO);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "Hibernate" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Inject
    private DataSource dataSource;

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", false);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create");
        return properties;
    }
}


