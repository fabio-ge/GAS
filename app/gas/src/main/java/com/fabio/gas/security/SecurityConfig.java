package com.fabio.gas.security;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
        .formLogin(form -> form.loginPage("/login")
                               .defaultSuccessUrl("/home", true)
                               .permitAll())
        .logout(LogoutConfigurer::permitAll);
        
        http.authorizeHttpRequests(request -> request.requestMatchers("/", "/css/*", "/registration", "/login").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) throws SQLException{
        return new JdbcUserDetailsManager(dataSource);    
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
