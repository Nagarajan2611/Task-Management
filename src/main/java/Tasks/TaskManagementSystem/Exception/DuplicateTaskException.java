package Tasks.TaskManagementSystem.Exception;

public class DuplicateTaskException extends RuntimeException{
    public DuplicateTaskException(String message){
        super(message);
    }
}
