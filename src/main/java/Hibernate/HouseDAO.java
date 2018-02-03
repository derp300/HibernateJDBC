package Hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class HouseDAO {
    private SessionFactory sessionFactory;

    public HouseDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }

    HouseDAO() {}

    public void insert(final House house) {
        if (house.getId() != null) {
            throw new IllegalArgumentException("House with " + house.getId() + " already exist");
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

    @Transactional
    public void update(final House house) {
        session().update(house);
    }

    public void delete(final int houseId) {
        session().createQuery("DELETE House WHERE id = :id")
                .setInteger("id", houseId)
                .executeUpdate();
    }

    private Session session() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }
}
