package Tasks.TaskManagementSystem.Repo;

import Tasks.TaskManagementSystem.Model.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

}
