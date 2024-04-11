package com.tcc.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.ScrollPosition;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECREAT_KEY.getBytes());

    public static String generateToken(Authentication auth){

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder()
                .setIssuer("Ashutosh Das")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))


                /*
                Since We want to identify users based on their token ... we need
                to set some claims which are nothing but the information that we
                want the token to carry so that we can extract it whenever needed

                Here we are setting claims as email --> since we are interested in extracting
                email from token then operate on users data based on that
                 */
                .claim("email", auth.getName())

                /*
             jwt tokens can not store the authorities in
             grantedAuthority format so, we need to convert it
             to string using the populateAuthorities() function
             and again convert the string back to GrantedAuthority
             in CustomUserDetailService class
              */
                .claim("authorities", roles)


                .signWith(key)
                .compact();

        System.out.println("generated token " + jwt);

        return jwt;


    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String > auths = new HashSet<>();

        for (GrantedAuthority grantedAuthority: authorities){

            auths.add(grantedAuthority.getAuthority());
        }
        return String.join(",",auths);
    }

    public static String getEmailFromJwt(String jwt){
        /*
        Jwt token generated is in the format:
        Bearer(ActualToken)
        we need to remove the word Bearer
         */
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }

}
