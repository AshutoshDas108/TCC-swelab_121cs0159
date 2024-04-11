package com.tcc.config;

import com.tcc.config.jwt.JwtValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    /*
    Enabling spring security filters
     */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

//        http.cors(Customizer.withDefaults()) // Apply CORS configuration
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin/api/**").hasAnyRole("MANAGER")
//                        .requestMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll())
//                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class);


        http.
                 cors(cors-> cors.configurationSource(corsConfigSource()))
                  .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy
                (SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/api/**").hasAnyRole("MANAGER")
                        .requestMatchers("/api/**")
                        .authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class);


        return http.build();
    }

    /*
    Importing the password encoder
     */

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private CorsConfigurationSource corsConfigSource() {
        /*
        Inner class concept is used
         */
        return  new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                /*
                Specify the URLs for our front end
                 */
                cfg.setAllowedOrigins(Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:5173"
                        //deployed app link
                ));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                cfg.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
    }

}
