package com.petrapulse.PetraPulse.controllers;

import com.petrapulse.PetraPulse.bo.AuthenticationRequest;
import com.petrapulse.PetraPulse.bo.AuthenticationResponse;
import com.petrapulse.PetraPulse.bo.RegisterRequest;
import com.petrapulse.PetraPulse.entities.AppUsersEntity;
import com.petrapulse.PetraPulse.repositories.UserDetailsJpaRepository;
import com.petrapulse.PetraPulse.services.AuthenticationService;
import com.petrapulse.PetraPulse.services.UsersDetailsServiceImpl;
import com.petrapulse.PetraPulse.util.exceptions.BodyGuardException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

// Step 9 (End Points)
@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UsersDetailsServiceImpl usersDetailsService;
    private final UserDetailsJpaRepository userDetailsJpaRepository;


//    @GetMapping("/")
//    public String getHome(Principal p, Model m) {
//        if (p != null) {
//            String username = p.getName();
//            AppUsersEntity applicationUser = userDetailsJpaRepository.findByUsername(username);
//            m.addAttribute("username", username);
//        }
//        return "HomePage.html";
//    }
//@GetMapping("/")
//public String getHome(Principal principal, Model model) {
//    if (principal != null) {
//        String username = principal.getName();
//        Optional<AppUsersEntity> userOptional = userDetailsJpaRepository.findByUsername(username);
//        if (userOptional.isPresent()) {
//            AppUsersEntity applicationUser = userOptional.get();
//            model.addAttribute("username", username);
//        }
//    }
//    return "HomePage.html";
//}


    @GetMapping("/")
    public String getHome(Principal p, Model m) {
        if (p != null) {
            String username = p.getName();
            Optional<AppUsersEntity> userOptional = userDetailsJpaRepository.findByUsername(username);
            m.addAttribute("username", username);
        }
        return "HomePage.html";
    }




    @GetMapping("/signup")
    public String signup() {
       return "SignUpPage";
    }

    /*ResponseEntity is a class in Spring Framework that represents the entire HTTP response, including the status code, headers, and body,
     the body refers to the content or data that is sent back from the server to the client.*/
    @PostMapping("/signup")
    public String signup(@ModelAttribute RegisterRequest registerRequest, Model model) {
        try {
            usersDetailsService.createPreValidation(registerRequest);
            authenticationService.register(registerRequest);
            return "redirect:/";
        } catch (BodyGuardException e) {
            String[] violations = e.getMessage().split(",");
            for (String violation : violations) {
                handleSignupViolation(violation, model);
            }
            return "SignUpPage";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "LoginPage";
    }
//    @PostMapping("/login")
//    public String login(@ModelAttribute AuthenticationRequest request,Model model) {
//        try {
//            usersDetailsService.createPostValidation(request);
//            authenticationService.authenticate(request);
//            return "redirect:/";
//        } catch (BodyGuardException e) {
//            handleLoginViolation(e.getMessage(), model);
//            return "LoginPage";
//        }
//    }
@PostMapping("/login")
public String login(@ModelAttribute AuthenticationRequest request, Model model) {
    try {
        usersDetailsService.createPostValidation(request);
        authenticationService.authenticate(request);
        return "redirect:/";
    } catch (BodyGuardException e) {
        handleLoginViolation(e.getMessage(), model);
        return "LoginPage";
    }
}

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Perform logout actions here
        authenticationService.logout(request, response);
        return "redirect:/login"; // Redirect to login page after logout
    }
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    private void handleSignupViolation(String violation, Model model) {
        if (violation.contains("Username already exists")) {
            model.addAttribute("signupUsernameMessage", "Username already exists");
        } else if (violation.contains("Password should be minimum 8 characters with 1 upper case letter")) {
            model.addAttribute("signupPasswordMessage", "Password should be minimum 8 characters with 1 upper case letter");
        }else if (violation.contains("Passwords do not match")) {
                model.addAttribute("confirmPasswordMessage", "Passwords do not match");
        }else if (violation.contains("Enter a valid email")) {
            model.addAttribute("emailMessage", "Enter a valid email");
        } else if (violation.contains("Date Of Birth Cannot Be Empty")) {
            model.addAttribute("dateOfBirthMessage", "Date Of Birth Cannot Be Empty");
        }else if (violation.contains("Date Of Birth Cannot Be in the future")) {
            model.addAttribute("dateOfBirthMessage2", "Date Of Birth Cannot Be in the future");
        }
    }

    private void handleLoginViolation(String violation, Model model) {
        if (violation.contains("Username not found")) {
            model.addAttribute("loginUsernameMessage", "Username not found");
        } else if (violation.contains("Incorrect Password")) {
            model.addAttribute("loginPasswordMessage", "Incorrect Password");
        }
    }

}

