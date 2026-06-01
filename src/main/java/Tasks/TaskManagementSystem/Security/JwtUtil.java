package Tasks.TaskManagementSystem.Security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET="myverysecuresecretkeymyverysecuresecretkeymyverysecuresecretkey";
    private final long Exp=1000*60*60*24;
    private final Key key=Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Exp))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean ValidateToken(String token, String username){
            try{
               extractUsername(token);
               return true;
            }catch(JwtException e){
                return false;
            }
    }
}
