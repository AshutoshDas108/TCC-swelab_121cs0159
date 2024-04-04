package com.tcc.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.ScrollPosition;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECREAT_KEY.getBytes());

    public static String generateToken(Authentication auth){

        String jwt = Jwts.builder()
                .setIssuer("Ashutosh Das")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .signWith(key)
                .compact();

        return jwt;


    }

    public static String getEmailFromJwt(String jwt){
        /*
        Jwt token generated is in the format:
        Bearer(ActualToken)
        we need to remove the word Bearer
         */
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }

}
