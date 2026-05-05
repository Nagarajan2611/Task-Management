package Tasks.TaskManagementSystem.Config.Specification;

import Tasks.TaskManagementSystem.Model.Tasks.EnumStatus;
import Tasks.TaskManagementSystem.Model.Tasks.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecification implements Specification<Task> {

    private String tittle;
    private String description;
    private LocalDate date;
    private String status;

    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskSpecification(Task task) {
        this.tittle=task.getTittle();
        this.date=task.getDate();
        this.description=task.getDescription();
        this.status= String.valueOf(task.getStatus());
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Task> root,
                                           CriteriaQuery<?> query,
                                           CriteriaBuilder criteriaBuilder) {
        if(task==null){
            return criteriaBuilder.conjunction();
        }
        List<Predicate> predicates = new ArrayList<>();
        if ((tittle != null) && (!tittle.isEmpty())) {
            predicates.add(criteriaBuilder.equal(root.get("tittle"), tittle));
        }
        if (date != null) {
            predicates.add(criteriaBuilder.equal(root.get("date"), date));
        }
        if((description!=null)&&(!description.isEmpty())){
            predicates.add(criteriaBuilder.equal(root.get("description"),description));
        }
        if((status!=null)&&(!status.isEmpty())){
            predicates.add(criteriaBuilder.equal(root.get("status"),status));
        }
        if(predicates.isEmpty()){
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
