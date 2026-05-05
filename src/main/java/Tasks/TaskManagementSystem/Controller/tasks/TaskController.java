package Tasks.TaskManagementSystem.Controller.tasks;

import Tasks.TaskManagementSystem.Model.Tasks.Task;
import Tasks.TaskManagementSystem.Model.Tasks.TaskRequest;
import Tasks.TaskManagementSystem.Model.Tasks.TaskResponse;
import Tasks.TaskManagementSystem.Service.Tasks.TaskServe;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskServe taskServe;
    @PostMapping
    TaskResponse createtask(@RequestBody TaskRequest taskRequest){
        return taskServe.createtask(taskRequest);
    }
    @PutMapping("/id/{id}")
    TaskResponse update(@PathVariable long id,@RequestBody TaskRequest taskRequest){
        return taskServe.update(id,taskRequest);
    }
    @DeleteMapping("/id/{id}")
    String deleteTask(@PathVariable long id){
        return taskServe.deleteTask(id);
    }
    @GetMapping
    List<TaskResponse> getAllTasks(@RequestParam(required = false,defaultValue = "1") int page,
                                   @RequestParam(required = false,defaultValue = "20") int size,
                                   @RequestParam(required = false,defaultValue = "id") String sortby,
                                   @RequestParam(required = false,defaultValue = "ASC") String sortdir,
                                   @RequestBody(required = false) Task task){
        return taskServe.getAllTasks(page, size, sortby, sortdir, task);
    }
    @GetMapping("/id/{id}")
    TaskResponse getById(@PathVariable long id){
        return taskServe.getById(id);
    }
    @GetMapping("/status/{status}")
    List<TaskResponse> getBystatus(@PathVariable String status){
        return taskServe.getBystatus(status);
    }
}
