package com.tushar.swiggy.springSecurityBasics;

import com.tushar.swiggy.springSecurityBasics.models.Users;
import com.tushar.swiggy.springSecurityBasics.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    UserCrudRepository userCrudRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody Map<String, String> credentials) throws NoSuchAlgorithmException {
        String username = credentials.get("username");
        String jwtToken = JWTUtility.generateToken(username);
        String password = HashingUtility.generateHashPassword(credentials.get("password"));
        Users users = new Users(username, password);

        userCrudRepository.save(users);
        return jwtToken;
    }
}
