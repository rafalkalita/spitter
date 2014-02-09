package com.rafalkalita.spitter.mvc.config;

import com.rafalkalita.spitter.security.UserRepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Rafal Kalita
 */

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = UserRepositoryUserDetailsService.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage("/home")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/loginfailed")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                //.deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/resources/**", "/spitters*").permitAll()
                .anyRequest().authenticated();
    }

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
