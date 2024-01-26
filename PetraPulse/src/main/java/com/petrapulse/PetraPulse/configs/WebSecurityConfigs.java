package com.petrapulse.PetraPulse.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Step 7 we created a filter/ checked the token if valid or not/ extracted the user, and we authenticated him (updated the security context) but now we need
// to use the filter in order to make all of the above work
@Configuration
@RequiredArgsConstructor
//it indicates that the class will provide configuration for securing the web part of the application.
@EnableWebSecurity
public class WebSecurityConfigs {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/signup","/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()//A session is a period of interaction between a user and a web application. It starts when a user accesses the application and ends when the user logs out or the session expires.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //In a stateless approach, each request is independent, and the server does not store any session-related information between requests. This is common in RESTful APIs using JWT for authentication.
                .and()
                .authenticationProvider(authenticationProvider)// Authentication providers are responsible for validating and authenticating user credentials (Username/Password).
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //execute jwtAuthFilter before the UsernamePasswordAuthenticationFilter (which is the login page from spring security).
        return http.build();
    }
}
