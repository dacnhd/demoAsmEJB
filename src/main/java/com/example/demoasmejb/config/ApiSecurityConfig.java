package com.example.demoasmejb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String PROTECTED_URLS = "/api/**";

    @Autowired
    AuthenticationProvider provider;

    // check token, login theo cach nao(theo provider)
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher(PROTECTED_URLS)
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/authentication").permitAll()
                .antMatchers("/api/public").permitAll();
    }

    @Bean
    AuthenticationFilter authenticationFilter() throws Exception{
        final AuthenticationFilter filter = new AuthenticationFilter(new AntPathRequestMatcher(PROTECTED_URLS));
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }
}
