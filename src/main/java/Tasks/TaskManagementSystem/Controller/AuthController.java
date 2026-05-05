package Tasks.TaskManagementSystem.Controller;

import Tasks.TaskManagementSystem.Model.Users.UserRequest;
import Tasks.TaskManagementSystem.Model.Users.UserRespose;
import Tasks.TaskManagementSystem.Security.JwtUtil;
import Tasks.TaskManagementSystem.Service.Users.UserServe;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServe userServe;

    @PostMapping("/register")
    public String register(@RequestBody UserRequest request){
         userServe.createUser(request);
         return "Register Successfully!";
    }
    @PostMapping("/login")
    public String login(@RequestBody UserRequest request){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        return jwtUtil.generateToken(request.getUsername());
    }
}
