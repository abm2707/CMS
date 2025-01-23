package com.demo.CMS.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.context.annotation.Bean;

import java.util.Date;

public class JWTHelper {

    private static final String SECRET = "secret";
    private static final long EXPIRATION_TIME = 10 * 60 * 1000; // 10 minutes

    public static String generateToken(String username, String issuer) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
