package daif.aymane.showsManagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;
import daif.aymane.showsManagement.models.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/v1/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            // having json content via request.getInputStream(), then map it into User object
            AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    appUser.getUsername(), appUser.getPassword(), new ArrayList<>()
            );
            return authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET.getBytes());
        String userName = springUser.getUsername();

        //generate jwt
        String jwtToken = JWT.create().withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .withClaim("roles", springUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);
        response.addHeader("access-control-expose-headers", SecurityConstants.HEADER_NAME);
        response.addHeader(SecurityConstants.HEADER_NAME, SecurityConstants.TOKEN_PREFIX + jwtToken);
    }
}
