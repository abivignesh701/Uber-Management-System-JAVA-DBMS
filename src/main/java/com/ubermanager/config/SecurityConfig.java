package com.ubermanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ubermanager.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Public access
                .requestMatchers("/", "/login", "/register/**", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                // Allow unauthenticated creation of users, drivers, rides, payments, and ratings
                .requestMatchers("/api/users", "/api/drivers", "/api/rides", "/api/payments", "/api/ratings").permitAll()
                // User-specific routes
                .requestMatchers("/user/**").hasRole("USER")
                // Driver-specific routes
                .requestMatchers("/driver/**").hasRole("DRIVER")
                // Admin/Dashboard routes - accessible by both
                .requestMatchers("/dashboard", "/api/**", "/users", "/drivers", "/rides", "/payments", "/ratings").authenticated()
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/perform-login")
                .successHandler((request, response, authentication) -> {
                    // Custom success handler to redirect based on role
                    String role = authentication.getAuthorities().iterator().next().getAuthority();
                    if (role.equals("ROLE_USER")) {
                        response.sendRedirect("/user/dashboard");
                    } else if (role.equals("ROLE_DRIVER")) {
                        response.sendRedirect("/driver/dashboard");
                    } else {
                        response.sendRedirect("/dashboard");
                    }
                })
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Disable for simplicity, enable in production
        
        return http.build();
    }
}
