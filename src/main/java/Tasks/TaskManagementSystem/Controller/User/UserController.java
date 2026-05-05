package Tasks.TaskManagementSystem.Controller.User;

import Tasks.TaskManagementSystem.Model.Users.User;
import Tasks.TaskManagementSystem.Model.Users.UserRequest;
import Tasks.TaskManagementSystem.Model.Users.UserRespose;
import Tasks.TaskManagementSystem.Service.Users.UserServe;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServe userServe;

    @PutMapping("/id/{id}")
    UserRespose updateUser(@PathVariable long id,@RequestBody UserRequest request){
        return userServe.updateUser(id,request);
    }
    @DeleteMapping("/id/{id}")
    void DeleteUser(@PathVariable long id){
        userServe.DeleteUser(id);
    }
    @GetMapping()
    List<UserRespose> GetAllUser(@RequestParam(required = false,defaultValue = "1") int page,
                                 @RequestParam(required = false,defaultValue = "20") int size,
                                 @RequestParam(required = false) String username,
                                 @RequestParam(required = false,defaultValue = "ASC") String sortdir,
                                 @RequestParam(required = false,defaultValue = "username")String sortby){
        try {
            return userServe.GetAllUser(page, size, username, sortdir,sortby);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    @GetMapping("/id/{id}")
    UserRespose getbyId(@PathVariable long id){
        return userServe.getbyId(id);
    }
    @GetMapping("/name/{username}")
    UserRespose getbyName(@PathVariable String username){
        return userServe.getbyName(username);
    }


}
