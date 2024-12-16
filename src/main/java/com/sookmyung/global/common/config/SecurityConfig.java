package com.sookmyung.global.common.config;

import static com.sookmyung.global.common.security.SecurityConstant.AUTH_WHITELIST;
import static com.sookmyung.global.common.security.SecurityConstant.AUTH_WHITELIST_FOR_GUEST;
import static com.sookmyung.global.common.security.SecurityConstant.AUTH_WHITELIST_WILDCARD;
import static com.sookmyung.global.common.security.SecurityConstant.AUTH_WHITELIST_WILDCARD_ONLY_GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sookmyung.global.common.security.filter.*;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*") // 모든 도메인 허용
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
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
        .authorizeRequests(
            auth -> {
              auth.requestMatchers(AUTH_WHITELIST).permitAll();
              auth.requestMatchers(AUTH_WHITELIST_WILDCARD).permitAll();
              auth.requestMatchers(HttpMethod.GET, AUTH_WHITELIST_WILDCARD_ONLY_GET).permitAll();
              auth.requestMatchers(AUTH_WHITELIST_FOR_GUEST).hasAuthority("GUEST");
              auth.anyRequest().hasAnyAuthority("SOOKMYUNG_STUDENT", "INTERNATIONAL_STUDENT");
            })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
