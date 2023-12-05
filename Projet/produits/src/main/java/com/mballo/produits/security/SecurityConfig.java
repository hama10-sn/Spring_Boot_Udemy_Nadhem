package com.mballo.produits.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = passwordEncoder();

//        {noop} signifie qu'on utilise pas l'encode du password
        /*auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("mballo").password("{noop}123").roles("AGENT", "USER");
        auth.inMemoryAuthentication().withUser("user1").password("{noop}123").roles("USER");*/

//        Utilisation de l'encodage du password
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("mballo").password(passwordEncoder.encode("123")).roles("AGENT", "USER");
        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/showCreate").hasAnyRole("ADMIN", "AGENT");
        http.authorizeRequests().antMatchers("/saveProduit").hasAnyRole("ADMIN", "AGENT");
        http.authorizeRequests().antMatchers("/ListeProduits").hasAnyRole("ADMIN", "AGENT", "USER");
        http.authorizeRequests().antMatchers("/supprimerProduit", "/modifierProduit", "/updateProduit").hasRole("ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
        http.exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
