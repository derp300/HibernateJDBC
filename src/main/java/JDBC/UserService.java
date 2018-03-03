package JDBC;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class UserService {
    private UserDAO userDAO;

    public void insert(User user) {
        userDAO.insert(user);
        System.out.println("Insert user with id " + user.getId());
    }
    
    public Optional<User> get(int userId) {
        System.out.println("Get user with id "+ userId);
        return userDAO.get(userId);
    }
    
    public Set<User> getAll() {
        System.out.println("Get all users");
        return userDAO.getAll();
    }
    
    public void update(User user) {
        userDAO.update(user);
        System.out.println("Update user with id " + user.getId());
    }
    
    public void delete(int userId) {
        userDAO.delete(userId);
        System.out.println("Delete user with id " + userId);
    }

    public UserService() {}

    public UserService(final UserDAO userDAO) {
        this.userDAO = requireNonNull(userDAO);
    }
    
    public void drawMoney(int userId, int money) {
        final Optional<User> optionalUser = get(userId);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("Not found user with id " + userId);
        }
        User user = optionalUser.get();
        user.setMoney(user.getMoney() - money);
        userDAO.update(user);
        System.out.println("Draw " + money + " money from user with id " + userId);
    }
}
