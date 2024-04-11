package com.tcc.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstants.JWT_HEADER);
        System.out.println( "do filter chain jwt " + jwt);


        if(jwt != null){

               /*
              IMPORTANT TO REMOVE BEARER WORD FROM JWT TOKEN
               */
            jwt = jwt.substring(7);
            System.out.println("do filter chain bearer removed" + jwt);

            try{
                System.out.println("inside try 1");
                SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECREAT_KEY.getBytes());
                System.out.println("inside try 2");
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                System.out.println("inside try 3");

                String email = String.valueOf(claims.get("email"));
                System.out.println("email" + email);
                String authorities = String.valueOf(claims.get("authorities"));

//                String email = JwtProvider.getEmailFromJwt(jwt);
//                System.out.println(email);
//                String authorities = String.valueOf(claims.get("authorities"));
//                System.out.println(authorities);


                /*
                string data : ROLE_CUSTOMER, ROLE_ADMIN (as a string)
                Converting string into list of granted authorities
                 */
                List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorityList);

                SecurityContextHolder.getContext().setAuthentication(authentication);


                /*
                Extract the email from jwt token
                 */
                  //WHEN ROLES ARE NOT INVOLVED
//                String email = JwtProvider.getEmailFromJwt(jwt);
//
//                List<GrantedAuthority> authorities = new ArrayList<>();
//
//                Authentication authentication = new UsernamePasswordAuthenticationToken(email,
//                        null, authorities);
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e){

                System.out.println("inside catch");
                throw new BadCredentialsException("Invalid Token...");

            }
        }

        /*
        Forward the request and response to the next series of filter
         */
        filterChain.doFilter(request,response);

    }
}
