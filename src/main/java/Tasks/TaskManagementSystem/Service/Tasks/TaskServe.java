package Tasks.TaskManagementSystem.Service.Tasks;

import Tasks.TaskManagementSystem.Config.Specification.TaskSpecification;
import Tasks.TaskManagementSystem.Exception.DuplicateTaskException;
import Tasks.TaskManagementSystem.Exception.TaskNotFoundException;
import Tasks.TaskManagementSystem.Model.Tasks.EnumStatus;
import Tasks.TaskManagementSystem.Model.Tasks.Task;
import Tasks.TaskManagementSystem.Model.Tasks.TaskRequest;
import Tasks.TaskManagementSystem.Model.Tasks.TaskResponse;
import Tasks.TaskManagementSystem.Model.Users.User;
import Tasks.TaskManagementSystem.Repo.TaskRepo;
import Tasks.TaskManagementSystem.Repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServe implements TaskServices {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public TaskResponse createtask(TaskRequest taskRequest) {
        if (taskRepo.findByTittle(taskRequest.getTittle()).isPresent()) {
            throw new DuplicateTaskException("This tittle is already exist");
        }
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findById(taskRequest.getUserid()).orElseThrow(()->new TaskNotFoundException("User Not Found"));
        Task task = mapper.map(taskRequest, Task.class);
        task.setDate(LocalDate.now());
        task.setUser(user);
        taskRepo.save(task);
        return mapper.map(task, TaskResponse.class);
    }

    @Override
    public TaskResponse update(long id, TaskRequest taskRequest) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(" The id " + id + " is Not Founded"));
        task.setTittle(taskRequest.getTittle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(EnumStatus.valueOf(taskRequest.getStatus()));
        taskRepo.save(task);
        return mapper.map(task, TaskResponse.class);
    }

    @Override
    public String deleteTask(long id) {
        if (taskRepo.findById(id).isPresent()) {
            taskRepo.deleteById(id);
            return "Delete by the id is " + id;
        } else {
            throw new TaskNotFoundException("The id " + id + " is Not Founded!!");
        }
    }

    @Override
    public List<TaskResponse> getAllTasks(int page, int size,
                                          String sortby, String sortdir,
                                          Task task) {
        Sort sort = null;
        if (sortdir.equalsIgnoreCase("DEC")) {
            sort = Sort.by(sortby).descending();
        } else {
            sort = Sort.by(sortby).ascending();
        }
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Task> taskPage;
        if(task==null){
            taskPage=taskRepo.findAll(pageRequest);
        }else {
            Specification<Task> spec = new TaskSpecification(task);
            taskPage = taskRepo.findAll(spec, pageRequest);
         }
            return taskPage.getContent()
                .stream().map(task1 -> mapper.map(task1, TaskResponse.class))
                .toList();
    }

    @Override
    public TaskResponse getById(long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("The id " + id + " is Not Founded!"));
        return mapper.map(task, TaskResponse.class);
    }

    @Override
    public List<TaskResponse> getBystatus(String status) {
        EnumStatus enumStatus;
        try {
             enumStatus = EnumStatus.valueOf(status.toUpperCase());
            }
        catch (Exception e){
              throw new TaskNotFoundException("The Status "+status+" is Not Founded");
        }
        List<Task> task = taskRepo.findByStatus(enumStatus);
            return task.stream().map(task1 -> mapper.map(task1,TaskResponse.class)).toList();
        }
}
