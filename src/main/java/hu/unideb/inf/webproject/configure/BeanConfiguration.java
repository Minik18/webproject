package hu.unideb.inf.webproject.configure;

import hu.unideb.inf.webproject.repository.UserRepository;
import hu.unideb.inf.webproject.service.UserDAO;
import hu.unideb.inf.webproject.service.impl.UserDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDAO userDAO(UserRepository userRepository) {
        return new UserDAOImpl(userRepository);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDAOImpl(userRepository);
    }

}
