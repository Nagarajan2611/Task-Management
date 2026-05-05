package Tasks.TaskManagementSystem.Service.Tasks;

import Tasks.TaskManagementSystem.Model.Tasks.Task;
import Tasks.TaskManagementSystem.Model.Tasks.TaskRequest;
import Tasks.TaskManagementSystem.Model.Tasks.TaskResponse;

import java.util.List;

public interface TaskServices {

    TaskResponse createtask(TaskRequest taskRequest);
    TaskResponse update(long id,TaskRequest taskRequest);
    String deleteTask(long id);
    List<TaskResponse> getAllTasks(int page, int size, String sortby, String sortdir, Task task);
    TaskResponse getById(long id);
    List<TaskResponse> getBystatus(String status);
}
