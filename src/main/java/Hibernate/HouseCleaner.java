package Hibernate;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.transaction.annotation.Transactional;

public class HouseCleaner {
    private SessionFactory sessionFactory;
    
    public HouseCleaner(final SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }
    
    HouseCleaner() {}
    
    @Transactional
    public void cleanTables() {
        final Set<String> tablesNames = sessionFactory.getAllClassMetadata().values().stream()
                .map(classMetadata -> ((AbstractEntityPersister) classMetadata).getTableName())
                .collect(Collectors.toSet());

        final Session session = sessionFactory.getCurrentSession();
            tablesNames.stream()
                    .forEach(tableName -> session.createSQLQuery("DELETE FROM " + tableName).executeUpdate());
    }
}
