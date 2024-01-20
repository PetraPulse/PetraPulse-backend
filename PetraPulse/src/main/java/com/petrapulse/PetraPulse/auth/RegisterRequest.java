package com.petrapulse.PetraPulse.auth;

import com.petrapulse.PetraPulse.enums.Roles;
import com.petrapulse.PetraPulse.models.RoleTypesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Step 11 (SignUpRequest)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String address;
    private int phoneNumber;
    private RoleTypesEntity role;
}
