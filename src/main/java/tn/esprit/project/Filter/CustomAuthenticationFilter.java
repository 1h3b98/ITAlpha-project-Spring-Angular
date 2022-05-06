package tn.esprit.project.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//        log.info("username is {}",username);
//        log.info("password is {}",password);
//        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
//        return authenticationManager.authenticate(authenticationToken);
        tn.esprit.project.Entities.User user=null;
        try {
            user=new ObjectMapper().readValue(request.getInputStream(), tn.esprit.project.Entities.User.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    }

   @Override
   @CrossOrigin(origins = "*")
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//       User user= (User) authentication.getPrincipal();
//        Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
//        String jwt= JWT.create().withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000))
//                .withIssuer(request.getRequestURL().toString())
//                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .sign(algorithm);
//        String refresh_token= JWT.create().withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 *1000))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);

        //   response.setHeader("access_token",access_token);
        //  response.setHeader("refresh_token",refresh_token);
//
//        Map<String,String > tokens=new HashMap<>();
//        tokens.put("access_token",jwt);
//        tokens.put("refresh_token",refresh_token);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
       org.springframework.security.core.userdetails.User springuser= (User) authentication.getPrincipal();
       List<String> roles=new ArrayList<>();
       springuser.getAuthorities().forEach(au->{roles.add(au.getAuthority());
       });
       String jwt =JWT.create().withSubject(springuser.getUsername())
                       .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
                               .withExpiresAt(new Date(System.currentTimeMillis()+10*24*60*60*1000))
               .sign(Algorithm.HMAC256("amineboujouna"));
       response.addHeader("Authorization",jwt);
    }



}
