    package com.online.retail.api_gateway.config;

    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.convert.converter.Converter;
    import org.springframework.security.authentication.AbstractAuthenticationToken;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.oauth2.jwt.Jwt;
    import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
    import reactor.core.publisher.Mono;

    import java.util.List;
    import java.util.stream.Collectors;

    @Configuration
    public class JwtAuthConverter
            implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

        @Override
        public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {

            List<String> roles =
                    jwt.getClaimAsStringList(
                            "https://online-retail-api/roles"
                    );
            if (roles == null) {
                roles = List.of();
            }

            List<GrantedAuthority> authorities =
                    roles.stream()
                            .map(role -> "ROLE_" + role)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            return Mono.just(
                    new JwtAuthenticationToken(jwt, authorities)
            );
        }
    }