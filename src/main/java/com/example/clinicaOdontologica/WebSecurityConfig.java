package com.example.clinicaOdontologica;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                        .csrf().disable()
                        .authorizeRequests()
                        .antMatchers("/index.html").hasRole("USER")
                        .antMatchers("/turnos.html").hasRole("ADMIN")
                        .antMatchers("/pacientes.html").hasRole("ADMIN")
                        .antMatchers("/odontologos.html").hasRole("ADMIN")
                        .antMatchers("/alta-turnos.html").hasRole("ADMIN")
                        .antMatchers("/alta-pacientes.html").hasRole("ADMIN")
                        .antMatchers("/alta-odontologos.html").hasRole("ADMIN")
                        .anyRequest()
                        .permitAll()
                        .and()
                        .formLogin()
                        .and()
                        .logout();
        }
}
