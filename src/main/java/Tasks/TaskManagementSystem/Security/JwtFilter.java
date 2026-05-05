//package Tasks.TaskManagementSystem.Security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//     @Autowired
//    private JwtUtil jwtUtil;
//     @Autowired
//    private CustomUserDetailService service;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//                                                    throws ServletException, IOException {
//        String header=request.getHeader("Authorization");
//        System.out.println("req_get_servlet_path "+request.getServletPath());
//        if(header!=null&&header.startsWith("Bearer")){
//           String token=header.substring(7);
//           String username= jwtUtil.extractUsername(token);
//           UserDetails userDetails=service.loadUserByUsername(username);
//            System.out.println("header " + header);
//            System.out.println("token " + token);
//            System.out.println("username " + username);
//                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                        userDetails,null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        filterChain.doFilter(request,response);
//    }
//}
