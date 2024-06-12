package com.learning.demo.spring.Config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;

public class PasswordEncoder {

    private static final int STRENGTH = 10;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());

    private PasswordEncoder() {
        // Private constructor to prevent instantiation
    }

    public static String encode(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public static boolean matches(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }
}
