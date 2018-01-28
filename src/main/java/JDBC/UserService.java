package JDBC;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@Service("UserService")
public class UserService {
    private final UserDAO userDAO;

    @Transactional
    public void insert(User user) {
        userDAO.insert(user);
        System.out.println("Insert user with id " + user.getId());
    }
    
    @Transactional
    public Optional<User> get(int userId) {
    	System.out.println("Get user with id "+ userId);
        return userDAO.get(userId);
    }
    
    @Transactional
    public Set<User> getAll() {
    	System.out.println("Get all users");
        return userDAO.getAll();
    }
    
    @Transactional
    public void update(User user) {
        userDAO.update(user);
        System.out.println("Update user with id " + user.getId());
    }
    
    @Transactional
    public void delete(int userId) {
        userDAO.delete(userId);
        System.out.println("Delete user with id " + userId);
    }

    public UserService(final UserDAO userDAO) {
        this.userDAO = requireNonNull(userDAO);
    }
    
    @Transactional
    public void drawMoney(int userId, int money) {
    	User user = userDAO.get(userId).get();
        user.setMoney(user.getMoney() - money);
        userDAO.update(user);
        System.out.println("Draw " + money + " money from user with id " + userId);
    }
}
