package uz.ilm.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.ilm.security.conf.WebSecurityConfig;
import uz.ilm.security.model.Roles;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component("AccessHandler")
public class AccessHandler extends OncePerRequestFilter {

    String username = "";

    public boolean hasPermission(Roles role) {
        Collection<? extends GrantedAuthority> collection = WebSecurityConfig.inMemoryUserDetailsManager.loadUserByUsername(username).getAuthorities();
        AtomicReference<String> userRole = new AtomicReference<>("");
        collection.stream().forEach(grantedAuthority -> userRole.set(grantedAuthority.getAuthority()));
        return userRole.toString().replaceAll("ROLE_", "").equals(role.name());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            username = new String(Base64.getDecoder().decode(request.getHeader("Authorization").replaceAll("Basic ", ""))).split(":")[0];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
