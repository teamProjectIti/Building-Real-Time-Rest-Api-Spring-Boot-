package com.example.SpringBoot.Config;


import com.example.SpringBoot.Config.Enums.UserRoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated() // Require authentication for all requests
                )
                .httpBasic(Customizer.withDefaults()); // Use HTTP Basic authentication
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails User= org.springframework.security.core.userdetails.User.builder()
                .username("Beshoy")
                .password(passwordEncoder().encode("123"))
                .roles(UserRoleEnum.Admin.name())
                .build();
        UserDetails Admin= org.springframework.security.core.userdetails.User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("Admin"))
                .roles(UserRoleEnum.Admin.name())
                .build();

        return  new InMemoryUserDetailsManager(User,Admin);



    }
}
