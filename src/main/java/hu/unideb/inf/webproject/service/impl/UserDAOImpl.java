package hu.unideb.inf.webproject.service.impl;

import hu.unideb.inf.webproject.model.User;
import hu.unideb.inf.webproject.repository.UserRepository;
import hu.unideb.inf.webproject.service.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getAllUser().stream()
                .filter(m -> username.equals(m.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No username found for " + username));
        return new UserDetailImpl(user);
    }
}
