package com.db.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.introspection-uri}")
    String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
    String clientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz.requestMatchers(HttpMethod.GET, "/product/*")
                        .hasAuthority("SCOPE_SELLER")
                        .requestMatchers(HttpMethod.POST, "/product/*")
                        .hasAuthority("SCOPE_SELLER")
                        .requestMatchers(HttpMethod.GET, "/auction/*")
                        .hasAuthority("SCOPE_BUYER")
                        .requestMatchers(HttpMethod.POST, "/auction/*")
                        .hasAuthority("SCOPE_BUYER")
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken
                        (token -> token.introspectionUri(this.introspectionUri)
                                .introspectionClientCredentials(this.clientId, this.clientSecret)));
        return http.build();
    }
}