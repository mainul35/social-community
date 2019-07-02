package com.mainul35.config.security;

import com.mainul35.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDao userDaoImpl;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/temp/**").permitAll()
                .antMatchers("/", "/register").permitAll()
                .antMatchers("/profile**")
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .usernameParameter("email")
                .passwordParameter("password")
//                .defaultSuccessUrl("/")
                .successHandler(authSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDaoImpl).passwordEncoder(BCPasswordEncoder());
    }

    @Bean
    public CustomAuthSuccessHandler authSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean(name = "passwordEncoder")
    PasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}