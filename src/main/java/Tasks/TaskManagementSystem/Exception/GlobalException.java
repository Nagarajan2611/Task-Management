package Tasks.TaskManagementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> TaskNotFoundException(TaskNotFoundException tnfex, WebRequest wr){
        ErrorResponse er=new ErrorResponse(tnfex.getMessage(),
                                           wr.getDescription(false),
                                  "404 Not Founded ");
        return new ResponseEntity<>(er, HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateTaskException.class)
    public  ResponseEntity<ErrorResponse> DuplicateTaskException(DuplicateTaskException dtex,WebRequest wr){

        ErrorResponse response=new ErrorResponse(dtex.getMessage(),
                                                 wr.getDescription(false),
                                        "Already exists..Duplicate");
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
}
