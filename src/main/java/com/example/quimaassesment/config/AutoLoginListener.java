package com.example.quimaassesment.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AutoLoginListener implements ApplicationListener<ContextRefreshedEvent> {

    private final UserDetailsService userDetailsService;

    public AutoLoginListener(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Autentica  o usuário "admin"
        UserDetails admin = userDetailsService.loadUserByUsername("admin");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(admin, admin.getPassword(), admin.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
