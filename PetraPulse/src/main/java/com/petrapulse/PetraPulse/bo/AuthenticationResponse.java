package com.petrapulse.PetraPulse.bo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Step 10
// Defining a class for the response body
public class AuthenticationResponse {

    /*The @JsonProperty annotation is used to specify the name of the JSON property when serializing or deserializing JSON data.
      When you serialize an object, you convert Java objects to JSON.
      When you deserialize an object, you convert JSON to Java objects.
    */
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

}
