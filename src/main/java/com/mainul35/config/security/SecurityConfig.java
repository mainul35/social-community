package com.mainul35.config.security;

import com.mainul35.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDao userDaoImpl;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDao userDaoImpl, PasswordEncoder passwordEncoder) {
        this.userDaoImpl = userDaoImpl;
        this.passwordEncoder = passwordEncoder;
    }

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

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDaoImpl).passwordEncoder(passwordEncoder);
    }

//  ========================== Alternate of overriden one (Custom configuration method) ==============================
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDaoImpl).passwordEncoder(BCPasswordEncoder());
//    }

    @Bean
    public CustomAuthSuccessHandler authSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }
}
