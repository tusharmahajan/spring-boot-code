package com.tushar.swiggy.springSecurityBasics;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashingUtility {

    public static String generateHashPassword(String password) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);


        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);


        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String newHashedPassword = new String(hashedPassword);
        return newHashedPassword;
    }
}
