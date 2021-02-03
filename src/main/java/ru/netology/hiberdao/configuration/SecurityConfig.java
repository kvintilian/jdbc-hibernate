package ru.netology.hiberdao.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/persons/save**", "/persons/delete-by-id", "/persons/find-all").authenticated().and().formLogin()
            .and().authorizeRequests().anyRequest().permitAll();
//    http.formLogin().and().authorizeRequests().antMatchers("/by-city").permitAll();
//            .and().authorizeRequests().anyRequest().authenticated();
//    http.authorizeRequests().antMatchers("/by-city").authenticated().and().formLogin();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
//    web.ignoring().antMatchers("/by-age-less**");
  }
}
