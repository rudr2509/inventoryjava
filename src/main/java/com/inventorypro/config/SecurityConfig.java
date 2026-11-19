package com.inventorypro.config;

import com.inventorypro.repository.AppUserRepository;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
    @Bean UserDetailsService userDetailsService(AppUserRepository users){
        return username -> users.findByUsername(username)
          .map(u -> User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole()).build())
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(a -> a.requestMatchers("/login","/css/**").permitAll()
          .requestMatchers("/admin/**","/products/**","/suppliers/**","/requests/*/review").hasRole("ADMIN")
          .anyRequest().authenticated())
          .formLogin(f -> f.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
          .logout(l -> l.logoutSuccessUrl("/login?logout")).build();
    }
}
