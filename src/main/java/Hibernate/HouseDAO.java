package Hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Repository("houseDAO")
@Transactional
public class HouseDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public HouseDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }

    HouseDAO() {}

    public void insert(final House house) {
        if (house.id() != null) {
            throw new IllegalArgumentException("House with " + house.id() + " already exist");
        }
        session().save(house);
    }

    public Optional<House> get(final int houseId) {
        final House house = (House) session().get(House.class, houseId);
        return Optional.ofNullable(house);
    }

    public Set<House> getAll() {
        final Criteria criteria = session().createCriteria(House.class);
        @SuppressWarnings("unchecked")
        final List<House> houses = criteria.list();
        return new HashSet<>(houses);
    }

    public void update(final House house) {
        session().update(house);
    }

    public void delete(final int houseId) {
        session().createQuery("DELETE House WHERE id = :id")
                .setInteger("id", houseId)
                .executeUpdate();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void cleanTables() {
        final Set<String> tablesNames = sessionFactory.getAllClassMetadata().values().stream()
                .map(classMetadata -> ((AbstractEntityPersister) classMetadata).getTableName())
                .collect(Collectors.toSet());

        final Session session = sessionFactory.getCurrentSession();
            tablesNames.stream()
                    .forEach(tableName -> session.createSQLQuery("DELETE FROM " + tableName).executeUpdate());
    }
}
