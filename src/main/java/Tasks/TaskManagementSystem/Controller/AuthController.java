package Tasks.TaskManagementSystem.Controller;

import Tasks.TaskManagementSystem.Model.Users.User;
import Tasks.TaskManagementSystem.Model.Users.UserRequest;
import Tasks.TaskManagementSystem.Model.Users.UserRespose;
import Tasks.TaskManagementSystem.Repo.UserRepo;
import Tasks.TaskManagementSystem.Security.JwtUtil;
import Tasks.TaskManagementSystem.Service.Users.UserServe;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServe userServe;
    @Autowired
    private UserRepo repo;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request){
        if(repo.findByUsername(request.getUsername()).isPresent()){
            return new ResponseEntity<>("Username already exists",HttpStatus.CONFLICT);
        }
        userServe.createUser(request);
         return  new ResponseEntity<>("User Register Successfully!",HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request){
       var userop=repo.findByUsername(request.getUsername());
       if(userop.isEmpty()){
           return new ResponseEntity<>("User Not Registered",HttpStatus.UNAUTHORIZED);}
       User user=userop.get();
       if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
           return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);}
       String token= jwtUtil.generateToken(request.getUsername());
           return  ResponseEntity.ok(Map.of("token",token));
    }
}
