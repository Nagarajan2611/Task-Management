package Tasks.TaskManagementSystem.Security;

import Tasks.TaskManagementSystem.Exception.TaskNotFoundException;
import Tasks.TaskManagementSystem.Model.Users.User;
import Tasks.TaskManagementSystem.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService  implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=userRepo.findByUsername(username).orElseThrow(()->new
                               TaskNotFoundException("Username "+username+" Not Founded"));
         return  org.springframework.security.core.userdetails.User.builder()
                 .username(user.getUsername())
                 .password(user.getPassword())
                 .authorities(user.getRole())
                 .build();

    }
}
