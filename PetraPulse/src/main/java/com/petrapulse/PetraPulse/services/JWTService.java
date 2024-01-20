package com.petrapulse.PetraPulse.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

// Step 2.2 extract the unique user info from the claims inside token
@Service
public class JWTService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                // step1 since the claims are in base64 form we need to decode it and verify the token using secret key and parser will allow us to do that
                .parserBuilder()
                // Step2 Set the signing key for JWT verification
                .setSigningKey(getSignInKey())
                // Step3 Build the JwtParser using the configured settings
                .build()
                //Step4 take a JWT string, separate it into its components and work with the individual parts.
                .parseClaimsJws(jwtToken)
                // Step5 Get the body of the JWS, which contains the claims
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

      // general to extract different types of claims   // Function is a functional interface. It represents a function that takes one argument and produces a result.
                                                        //In this case, the function takes a Claims object and returns a value of type T.
    public <T> T extractSpecificClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        //  we send the getSubject method to here from extractEmail (claimsResolver) and we apply it on the claims
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String jwtToken){
                                           // Subject is the user unique info that we use to extract him/her (username)
        return extractSpecificClaim(jwtToken, Claims::getSubject);
    }

    // Step 3 generate an access token for a user based on their username
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // A refresh token is a special type of token that is used to obtain a new access token without requiring the user to re-enter their credentials.
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            //The extraClaims parameter allows you to include additional custom claims in the JWT token. These can be any key-value pairs that you want to associate with the token. For example, you might include user roles or permissions.
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expirationDate) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    // Other Methods needed to manage Tokens
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    // we issue an expiration date as an extra layer of security because if an unauthorized party gains access to a token that will cause a problem
    private Date extractExpiration(String jwtToken) {
        return extractSpecificClaim(jwtToken, Claims::getExpiration);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }
}
