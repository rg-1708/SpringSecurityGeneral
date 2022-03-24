package portfolio.rg.handsonch6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.rg.handsonch6.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String u);
}