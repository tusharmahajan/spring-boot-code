package com.tushar.swiggy.springSecurityBasics;

import com.tushar.swiggy.springSecurityBasics.models.Users;
import com.tushar.swiggy.springSecurityBasics.repository.UserJPARepository;
import com.tushar.swiggy.springSecurityBasics.utilities.HashingUtility;
import com.tushar.swiggy.springSecurityBasics.utilities.JWTUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    UserJPARepository userJPARepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, String> credentials) throws NoSuchAlgorithmException {
        String username = credentials.get("username");
        String jwtToken = JWTUtility.generateToken(username);
        String generatedSalt = HashingUtility.generateSalt();
        String password = HashingUtility.generateHashPassword(credentials.get("password"), generatedSalt);
        Users users = new Users(username, password, generatedSalt);

        userJPARepository.save(users);
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) throws NoSuchAlgorithmException {
        String username = credentials.get("username");
        Users users = userJPARepository.getUserByUserName(username);
        String storedPassword = users.getPassword();
        String storedSalt = users.getSalt();

        String generatedHashedPassword = HashingUtility.generateHashPassword(credentials.get("password"), storedSalt);

        if(!generatedHashedPassword.equals(storedPassword)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect password");
        }
        String jwtToken = JWTUtility.generateToken(username);
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummyMethod(@RequestBody Map<String, String> tokenMap) {

        String token = tokenMap.get("jwt_token");
        Claims decodedData = JWTUtility.extractBody(token);
        Date expiryTime = decodedData.getExpiration();
        String userName = decodedData.getSubject();

        boolean isExpired = expiryTime.before(Date.from(Instant.now()));

        if(isExpired){
            return ResponseEntity.status(403).body("Token is expired");
        }

        Users users = userJPARepository.getUserByUserName(userName);

        if(users == null){
            return ResponseEntity.status(403).body("User not present");
        }
        return ResponseEntity.ok("Token is valid");
    }
}
