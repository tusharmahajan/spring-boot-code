package com.tushar.swiggy.springSecurityBasics;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashingUtility {

    public static String generateHashPassword(String password, String salt) throws NoSuchAlgorithmException {

        byte[] saltBytes = Base64.getDecoder().decode(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(saltBytes);

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPassword);
    }

    public static String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
