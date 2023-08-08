package com.tidavid1.Studywith.global.config;

import com.tidavid1.Studywith.domain.usertoken.config.CustomAccessDeniedHandler;
import com.tidavid1.Studywith.domain.usertoken.config.CustomAuthenticationEntryPoint;
import com.tidavid1.Studywith.domain.usertoken.config.JwtAuthenticationFilter;
import com.tidavid1.Studywith.domain.usertoken.config.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    private final JwtProvider jwtProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers("/api/signup", "/api/login", "/api/reissue")
                                        .permitAll()
                                        .anyRequest()
                                        .hasRole("USER"))
                .exceptionHandling(
                        httpSecurityExceptionHandlingConfigurer ->
                                httpSecurityExceptionHandlingConfigurer
                                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                                        .accessDeniedHandler(customAccessDeniedHandler))
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public WebSecurityCustomizer ignoringWebSecurityCustomizer(){
        return (web -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/favicon.ico", "/error"));
    }
}
