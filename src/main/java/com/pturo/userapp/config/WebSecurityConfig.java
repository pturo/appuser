package com.pturo.userapp.config;

import com.pturo.userapp.model.Users;
import com.pturo.userapp.repository.UsersRepository;
import com.pturo.userapp.service.UsersDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UsersDetailsServiceImpl userDetailsService;
    private UsersRepository usersRepository;

    @Autowired
    public WebSecurityConfig(UsersDetailsServiceImpl userDetailsService, UsersRepository usersRepository) {
        this.userDetailsService = userDetailsService;
        this.usersRepository = usersRepository;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/userTest").hasRole("USER")
                .antMatchers("/adminTest").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/allTest").permitAll()
                .antMatchers("/all").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        Users user = new Users("user", passwordEncoder().encode("user"), "ROLE_USER");
        Users admin = new Users("admin", passwordEncoder().encode("admin"), "ROLE_ADMIN");

        usersRepository.save(user);
        usersRepository.save(admin);
    }
}
