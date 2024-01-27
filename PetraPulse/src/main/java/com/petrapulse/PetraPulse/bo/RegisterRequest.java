package com.petrapulse.PetraPulse.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petrapulse.PetraPulse.entities.RoleTypesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// Step 11 (SignUpRequest)
@Data
@AllArgsConstructor
@NoArgsConstructor
/*is used to configure how Jackson includes (or excludes) properties during the serialization process from Java objects to JSON.
In the context of @JsonInclude(Include.NON_NULL), it specifies that properties with null values should be executed in the generated JSON output.*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String country;
    // We used @DateTimeFormat(pattern = "yyyy-MM-dd") so the date will be sent in a date format and not as a string to the client
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private RoleTypesEntity role;
}
