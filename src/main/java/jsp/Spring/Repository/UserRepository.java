package jsp.Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jsp.Spring.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
}
