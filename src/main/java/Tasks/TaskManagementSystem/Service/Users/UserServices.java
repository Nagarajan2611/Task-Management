package Tasks.TaskManagementSystem.Service.Users;

import Tasks.TaskManagementSystem.Model.Users.User;
import Tasks.TaskManagementSystem.Model.Users.UserRequest;
import Tasks.TaskManagementSystem.Model.Users.UserRespose;

import java.util.List;

public interface UserServices {
    UserRespose createUser(UserRequest request);
    UserRespose updateUser(long id,UserRequest request);
    void DeleteUser(long id);
    List<UserRespose> GetAllUser(int page,int size,String username,String sortdir,String sortby);
    UserRespose getbyId(long id);
    UserRespose getbyName(String username);

}
