package com.petrapulse.PetraPulse.configs;

import com.petrapulse.PetraPulse.services.JWTService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Step1 Create filter to check and validate the token
@Component
// this annotation will create contractor only for fields marked with final or NonNull annotation
@RequiredArgsConstructor                    // this to make sure that the filter will be executed once with each request
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    // There is UserDetailsService from spring security, but we want to implement our own because we are extracting users from our DB
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) // contains the chain of filters that will be executed on the request
                                              // by filters we mean operations like authentication, logging, modification of request parameters, or checking for specific headers.
            throws ServletException, IOException {

            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String username;

            /*If the condition is true (no valid JWT)(Authorization:Bearer jwt), the request goes through the remaining filters and eventually reaches the
             application logic.
             */

                // Authorization:Bearer 3535fgahdhvajhdewe636frge2uy
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // this means go to the next filter in the filterChain
                filterChain.doFilter(request, response);
                // exit the process when we went through all filters before reaching the app logic for because the user is unauthenticated.
                return;
            }
            /* If the condition is false (valid JWT present), the filter might perform additional authentication-related tasks before allowing the request
               to proceed through the filter chain and reach the application logic.*/

           // Step 2.1 extract the token and send it to the JWTService
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);

            //step 4 continue the validation by checking if the user exists and is not authenticated
                                  // central component in spring security that provides a way to obtain security context which holds details about currently authenticated user and their role
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // get the user from the database
                // the loadUserByUsername implementation is inside the ApplicationConfigs userDetailsService Bean
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // Step 6
                if(jwtService.isTokenValid(jwt,userDetails)){
                    //UsernamePasswordAuthenticationToken is a class provided by Spring Security to represent an authentication token which is piece of information that is used to authenticate and verify the identity of a user during the authentication process
                    UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    /* WebAuthenticationDetailsSource is a class provided by Spring Security that is responsible for creating instances of WebAuthenticationDetails.
                    *  WebAuthenticationDetails is another class in Spring Security that represents web-specific details of an authentication request.
                    *  Web-Specific Details: Examples Remote Address, session ID, Request Details.
                    * */
                    authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
                    //By passing the authenticationToken to this method, you are informing Spring Security that the current request is associated with an authenticated user.
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
    }
}
