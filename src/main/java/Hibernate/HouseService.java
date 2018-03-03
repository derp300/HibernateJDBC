package Hibernate;

import JDBC.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class HouseService {
    private HouseDAO houseDAO;
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public HouseService(final HouseDAO houseDAO, final UserService userService) {
        this.userService = requireNonNull(userService);
        this.houseDAO = requireNonNull(houseDAO);
    }

    HouseService() {}

    public void save(final House house) {
        houseDAO.insert(house);
        System.out.println("Insert house with id " + house.getId());
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
        System.out.println("Update house with id " + house.getId());
    }

    public void setAddress(final House house, final String address) {
            house.setAddress(address);
            update(house);
            System.out.println("Update house address with id "+ house.getId());
    }

    public void setCost(final House house, final int cost) {
            house.setCost(cost);
            update(house);
            System.out.println("Update house cost with id "+ house.getId());
    }

    private void setOwner(final int houseId, final int ownerId) {
            final Optional<House> optionalHouse = houseDAO.get(houseId);
            if (!optionalHouse.isPresent()) {
                throw new IllegalArgumentException("Not found house with id " + houseId);
            }
            optionalHouse.get().setOwnerId(ownerId);
            System.out.println("Update house owner with id "+ houseId);
    }

    @Transactional
    public void buyHouse(int userId, int houseId) {
        setOwner(houseId, userId);
        final Optional<House> optionalHouse = get(houseId);
        if (!optionalHouse.isPresent()) {
            throw new IllegalArgumentException("Not found house with id " + houseId);
        }
        userService.drawMoney(userId, optionalHouse.get().getCost());
        System.out.println("User with id " + userId +" buy house with id "+ houseId);
    }

    public void delete(final int houseId) {
        houseDAO.delete(houseId);
        System.out.println("Delete house with id "+ houseId);
    }
}
