package com.online.retail.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

@Configuration
public class JwtConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public ReactiveJwtDecoder jwtDecoder(
            AudienceValidator audienceValidator) {

        NimbusReactiveJwtDecoder jwtDecoder =
                (NimbusReactiveJwtDecoder)
                        ReactiveJwtDecoders
                                .fromIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> withIssuer =
                JwtValidators.createDefaultWithIssuer(issuerUri);

        OAuth2TokenValidator<Jwt> validator =
                new DelegatingOAuth2TokenValidator<>(
                        withIssuer,
                        audienceValidator
                );

        jwtDecoder.setJwtValidator(validator);

        return jwtDecoder;
    }
}