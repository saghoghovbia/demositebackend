package com.tsluxurycars.tsluxurycars.config;

import com.tsluxurycars.tsluxurycars.repository.AuthUserRepository;
import com.tsluxurycars.tsluxurycars.service.AuthUserDetailsService;
import com.tsluxurycars.tsluxurycars.security.AuthenticationFilter;
import com.tsluxurycars.tsluxurycars.security.AuthorizationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.tsluxurycars.tsluxurycars.constants.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthUserDetailsService authUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthUserRepository authUserRepository;

    public SecurityConfig(AuthUserDetailsService authUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthUserRepository authUserRepository) {
        this.authUserDetailsService = authUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authUserRepository = authUserRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), this.authUserRepository))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setExposedHeaders(Arrays.asList("token", "role", "userID"));
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
