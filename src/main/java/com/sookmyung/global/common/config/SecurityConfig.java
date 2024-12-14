package com.sookmyung.global.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  public static final String[] AUTH_WHITELIST = {
    "/",
    "/error",
    "/favicon.ico",
    "/auth/issue/code",
    "/auth/verify/email",
    "/member/validate-nickname"
  };

  public static final String[] AUTH_WHITELIST_WILDCARD = {
    "/webjars/**", "/webjars/**", "/auth/**", "/css/**", "/images/**", "/js/**", "/h2-console/**",
  };

  @Value("${spring.web.origin.client}")
  private String clientOrigin;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins(clientOrigin)
            .allowedOriginPatterns(clientOrigin)
            .allowedHeaders("*")
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name());
      }
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> {
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
        .headers(
            (headerConfig) ->
                headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

    http.authorizeHttpRequests(
        auth -> {
          auth.requestMatchers(AUTH_WHITELIST).permitAll();
          auth.requestMatchers(AUTH_WHITELIST_WILDCARD).permitAll();
          auth.requestMatchers("/auth/sign-up").hasAuthority("GUEST");
          auth.anyRequest().hasAnyAuthority("SOOKMYUNG_STUDENT", "INTERNATIONAL_STUDENT");
        });

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
