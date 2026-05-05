package Tasks.TaskManagementSystem.Service.Users;

import Tasks.TaskManagementSystem.Config.Specification.UserSpcific;
import Tasks.TaskManagementSystem.Exception.TaskNotFoundException;
import Tasks.TaskManagementSystem.Model.Users.User;
import Tasks.TaskManagementSystem.Model.Users.UserRequest;
import Tasks.TaskManagementSystem.Model.Users.UserRespose;
import Tasks.TaskManagementSystem.Repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServe implements UserServices{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRespose createUser(UserRequest request) {

        User user=mapper.map(request,User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        repo.save(user);
        return mapper.map(user,UserRespose.class);
    }
    public UserRespose updateUser(long id, UserRequest request) {
        User user=repo.findById(id).orElseThrow(()->new TaskNotFoundException("User id "+id+" is Not Founded"));
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        return mapper.map(user,UserRespose.class);
    }
    public void DeleteUser(long id) {
        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
        }
        else{
            throw new TaskNotFoundException("User id "+id+" is Not Founded!!");
        }
    }
    public List<UserRespose> GetAllUser(int page,int size,String username,String sortdir,String sortby) {
        Sort sort=null;
        if(sortdir.equalsIgnoreCase("Dec")){
            sort=Sort.by(sortby).descending();
        }
        else{
            sort=Sort.by(sortby).ascending();
        }
        PageRequest pageRequest=PageRequest.of(page-1,size,sort);
        Specification<User> specif=new UserSpcific(username);
        Page<User> page1=repo.findAll(specif,pageRequest);
        return page1.getContent().stream()
                .map(user -> mapper.map(user,UserRespose.class))
                .toList();
    }
    public UserRespose getbyId(long id) {
        User user=repo.findById(id).orElseThrow(()->new TaskNotFoundException("User id "+id+" is Not Founded!"));
        return mapper.map(user,UserRespose.class);
    }
    public UserRespose getbyName(String username) {
        if(repo.findByUsername(username).isPresent()){
       Optional<User> user=repo.findByUsername(username);
        return mapper.map(user,UserRespose.class);
    }else{
            throw new TaskNotFoundException("User Name "+username+" is Not Founded");
        }
    }
    public List<User> GetAllUser() {
        return  repo.findAll();
    }
}
