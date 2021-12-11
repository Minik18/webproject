package hu.unideb.inf.webproject.repository.init;

import hu.unideb.inf.webproject.model.User;
import hu.unideb.inf.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
@Profile("init")
public class UserRepositoryInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private List<User> users;

    @Autowired
    public UserRepositoryInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        users = List.of(
                new User("admin", passwordEncoder.encode("admin") , "admin@gmail.com", "ROLE_ADMIN"),
                new User("user", passwordEncoder.encode("password"),"user@gmail.com", "ROLE_USER")
        );
    }

    @PostConstruct
    private void initSetup() {
        users.forEach(userRepository::save);
    }

}
