package com.petrapulse.PetraPulse.configs;

import com.petrapulse.PetraPulse.repositories.UserDetailsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// Step 5 Creating a config class, so we can manage all the beans within our app such as UserDetailsService
@Configuration
@RequiredArgsConstructor
public class ApplicationConfigs {
    private final UserDetailsJpaRepository userDetailsJpaRepository;

    // Step 5
    // Here we implement the loadUserByUsername method from the UserDetailsService but instead of overriding we used lambda
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userDetailsJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }


    // Step 8

    //DaoAuthenticationProvider is a specific implementation of AuthenticationProvider provided by Spring Security.
    /*DaoAuthenticationProvider requires two important components:
      UserDetailsService: It is responsible for retrieving user details (such as username, password, and authorities) from a data store. The userDetailsService() method is used to configure this.
      PasswordEncoder: It is used to encode and verify user passwords. The passwordEncoder() method configures a BCryptPasswordEncoder bean, a popular password encoder in Spring Security.*/
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }

}
