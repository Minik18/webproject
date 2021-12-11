package hu.unideb.inf.webproject.repository;

import hu.unideb.inf.webproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
