package com.online.retail.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    @Value("${spring.security.oauth2.resourceserver.jwt.audience}")
    private String audience; // = "https://online-retail-api";

    public AudienceValidator(
            @Value("${spring.security.oauth2.resourceserver.jwt.audience}")
            String audience
    ) {
        this.audience = audience;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        if(jwt.getAudience().contains(audience)) {
            return OAuth2TokenValidatorResult.success();
        }
        OAuth2Error error = new OAuth2Error(
                "invalid_token",
                "The required audience is missing",
                null
        );
        return OAuth2TokenValidatorResult.failure(error);
    }
}