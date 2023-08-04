package com.rishu.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 1. authorize all requests
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // 2. enable http basic authentication

        http.httpBasic(Customizer.withDefaults());

        // 3. disable csrf
        http.csrf().disable();

        
        

        


        




        return http.build();
    }



}
