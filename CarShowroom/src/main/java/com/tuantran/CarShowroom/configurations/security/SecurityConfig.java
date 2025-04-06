package com.tuantran.CarShowroom.configurations.security;

import com.tuantran.CarShowroom.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tuantran.CarShowroom.configurations.security.filter.JwtAuthFilter;
import com.tuantran.CarShowroom.service.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private PasswordUtils passwordUtils;

    private final String WHITE_LIST_ENDPOINTS[] = {
            "/",
            "/api/v1/authentication/token",
            "/api/v1/authentication/welcome",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/swagger-ui/oauth2-re",
    };

    private final String PUBLIC_ENDPOINTS[] = {
            "/api/v1/users/**",
            "/api/v1/types/**",
            "/api/v1/brands/**",
            "/api/v1/segments/**",
            "/api/v1/features/**",
            "/api/v1/feature-values/**",
            "/api/v1/cars/**",
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(); // Ensure UserService implements UserDetailsService
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // WARNING: CHECK HERE If you encounter an HTTP status 403 (Forbidden) with an empty response body
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_ENDPOINTS).permitAll()
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
//                        .requestMatchers(PUBLIC_ENDPOINTS).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/auth/user/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/auth/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated() // Protect all other endpoints
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
                )
                .authenticationProvider(authenticationProvider()) // Custom authentication provider
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(this.passwordUtils.passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
