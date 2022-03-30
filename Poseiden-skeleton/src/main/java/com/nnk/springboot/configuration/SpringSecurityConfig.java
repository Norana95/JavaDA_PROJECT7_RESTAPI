package com.nnk.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    // méthode avec la classe AuthenticationManagerBuilder  pour gérer l'ensemble de règles d'authentification.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("user");
    }

    @Override
    //  méthode à la  classe HTTPSecurity  pour pousser toutes les requêtes HTTP à travers la chaîne de filtrage de sécurité et configurez la page de connexion par défaut avec la   formLogin()  méthode.
    // httpsecurity est juste pour les requettes http entrante
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/list").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/")
                .defaultSuccessUrl("/bidList/list", true);// Transition destination after success
        //logout process
        http
                .logout()
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true);

    }

}
