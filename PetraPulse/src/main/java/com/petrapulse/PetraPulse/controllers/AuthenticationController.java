package com.petrapulse.PetraPulse.controllers;

import com.petrapulse.PetraPulse.bo.AuthenticationRequest;
import com.petrapulse.PetraPulse.bo.AuthenticationResponse;
import com.petrapulse.PetraPulse.bo.RegisterRequest;
import com.petrapulse.PetraPulse.services.AuthenticationService;
import com.petrapulse.PetraPulse.services.UsersDetailsServiceImpl;
import com.petrapulse.PetraPulse.util.exceptions.BodyGuardException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Step 9 (End Points)
@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UsersDetailsServiceImpl usersDetailsService;
    @GetMapping("/signup")
    public String signup() {
       return "SignUpPage";
    }

    /*ResponseEntity is a class in Spring Framework that represents the entire HTTP response, including the status code, headers, and body,
     the body refers to the content or data that is sent back from the server to the client.*/
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> signup( RegisterRequest request) {
//        usersDetailsService.createPreValidation(request);
//        return ResponseEntity.ok(authenticationService.register(request));
//    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute RegisterRequest registerRequest, Model model) {
        try {
            usersDetailsService.createPreValidation(registerRequest);
            authenticationService.register(registerRequest);
            return "redirect:/";
        } catch (BodyGuardException e) {
            String[] violations = e.getMessage().split(",");
            for (String violation : violations) {
                handleViolation(violation, model);
            }
            return "SignUpPage";
        }
    }

    private void handleViolation(String violation, Model model) {
        if (violation.contains("Username already exists")) {
            model.addAttribute("usernameMessage", "Username already exists");
        } else if (violation.contains("Password should be minimum 8 characters with 1 upper case letter")) {
            model.addAttribute("passwordMessage", "Password should be minimum 8 characters with 1 upper case letter");
        } else if (violation.contains("Enter a valid email")) {
            model.addAttribute("emailMessage", "Enter a valid email");
        } else if (violation.contains("Date Of Birth Cannot Be Empty")) {
            model.addAttribute("dateOfBirthMessage", "Date Of Birth Cannot Be Empty");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}

