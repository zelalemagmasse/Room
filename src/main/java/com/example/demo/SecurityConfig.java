package com.example.demo;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SSUDS userDetailService;

    @Autowired
    private UserClassRepository userRepository;
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUDS(userRepository);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()

                .antMatchers("/addroom").hasAnyAuthority("DAVE")
                .antMatchers("/").hasAnyAuthority("USER")
                .antMatchers("/register").hasAnyAuthority("USER")
                .antMatchers("/h2-console/**").hasAnyAuthority("USER")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();

        http.headers().frameOptions().disable();
        http.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder theEncoder= encoder();
        auth.inMemoryAuthentication().withUser("DaveWolf").password(encoder().encode("beastmaster")).authorities("DAVE","USER")

                .and().withUser("ordinaryuser").password(encoder().encode("ordinaryuser")).authorities("USER")
                .and().passwordEncoder(theEncoder);
        auth.userDetailsService(userDetailsServiceBean());
    }
}
