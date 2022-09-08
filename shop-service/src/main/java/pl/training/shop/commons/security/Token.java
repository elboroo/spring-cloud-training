package pl.training.shop.commons.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Token {

    public static final String TOKEN_PREFIX = "bearer ";

    @SuppressWarnings("unchecked")
    public static Optional<String> getFromContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
        var token = principal.getKeycloakSecurityContext().getTokenString();
        return Optional.ofNullable(token);
    }

}
