package ru.netology.hiberdao.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("12345")).roles("READ");
    auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("12345")).roles("WRITE");
    auth.inMemoryAuthentication().withUser("traitor").password(passwordEncoder.encode("12345")).roles("DELETE");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests().antMatchers("/persons/count").permitAll()
            .and()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin();
  }
}
