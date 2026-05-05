package Tasks.TaskManagementSystem.Repo;

import Tasks.TaskManagementSystem.Model.Tasks.EnumStatus;
import Tasks.TaskManagementSystem.Model.Tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByStatus(EnumStatus status);
    Optional<Task> findByTittle(String tittle);
}
