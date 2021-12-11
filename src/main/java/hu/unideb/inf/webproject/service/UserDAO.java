package hu.unideb.inf.webproject.service;

import hu.unideb.inf.webproject.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserDAO extends UserDetailsService {

    List<User> getAllUser();

    void saveUser(User user);

    void deleteUser(User user);

    User findByUsername(String username);
}
