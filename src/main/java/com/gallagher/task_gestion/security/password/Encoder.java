package com.gallagher.task_gestion.security.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Encoder {

    @Bean
    public BCryptPasswordEncoder getBcyrptEncoder(){
        return new BCryptPasswordEncoder();
    }

}
