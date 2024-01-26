package com.petrapulse.PetraPulse.controllers;

import com.petrapulse.PetraPulse.bo.AuthenticationRequest;
import com.petrapulse.PetraPulse.bo.AuthenticationResponse;
import com.petrapulse.PetraPulse.bo.RegisterRequest;
import com.petrapulse.PetraPulse.services.AuthenticationService;
import com.petrapulse.PetraPulse.services.UsersDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Step 9 (End Points)
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UsersDetailsServiceImpl usersDetailsService;

    /*ResponseEntity is a class in Spring Framework that represents the entire HTTP response, including the status code, headers, and body, the body refers to the content or data that is sent back from the server to the client.*/
//    @GetMapping("/signup")
//    public String signup() {
//       return "SignUpPage";
//    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup( @RequestBody RegisterRequest request) {
//        usersDetailsService.createPreValidation(request);
        return ResponseEntity.ok(authenticationService.register(request));
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

