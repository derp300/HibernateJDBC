package Hibernate;

import JDBC.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@Transactional
@Service("HouseService")
public class HouseService {
    @Autowired
    private HouseDAO houseDAO;
    
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public HouseService(final SessionFactory sessionFactory,
                             final HouseDAO houseDAO, final UserService userService) {
        this.userService = requireNonNull(userService);
        this.houseDAO = requireNonNull(houseDAO);
    }

    HouseService() {}

    public void save(final House house) {
    	houseDAO.insert(house);
    	System.out.println("Insert house with id " + house.id());
    }

    public Optional<House> get(final int houseId) {
    	System.out.println("Get house with id " + houseId);
        return houseDAO.get(houseId);
    }

    public Set<House> getAll() {
    	System.out.println("Get all houses");
        return houseDAO.getAll();
    }

    public void update(final House house) {
    	houseDAO.update(house);
        System.out.println("Update house with id " + house.id());
    }

    public void setAddress(final int houseId, final String address) {
            Optional<House> optionalHouse = houseDAO.get(houseId);
            if (!optionalHouse.isPresent()) {
                throw new IllegalArgumentException("Not found house with id " + houseId);
            }
            optionalHouse.get().setAddress(address);
            System.out.println("Update house address with id "+ houseId);
    }

    public void setCost(final int houseId, final int cost) {
            final Optional<House> optionalHouse = houseDAO.get(houseId);
            if (!optionalHouse.isPresent()) {
                throw new IllegalArgumentException("Not found house with id " + houseId);
            }
            optionalHouse.get().setCost(cost);
            System.out.println("Update house cost with id "+ houseId);
    }

    public void setOwner(final int houseId, final int ownerId) {
            final Optional<House> optionalHouse = houseDAO.get(houseId);
            if (!optionalHouse.isPresent()) {
                throw new IllegalArgumentException("Not found house with id " + houseId);
            }
            optionalHouse.get().setOwner(ownerId);
            System.out.println("Update house owner with id "+ houseId);
    }

    public void buyHouse(int userId, int houseId) {
        setOwner(houseId, userId);
        userService.drawMoney(userId, get(houseId).get().cost());
        System.out.println("User with id " + userId +" buy house with id "+ houseId);
    }

    public void delete(final int houseId) {
        houseDAO.delete(houseId);
        System.out.println("Delete house with id "+ houseId);
    }

    public void cleanTables() {
        houseDAO.cleanTables();
    }
}
