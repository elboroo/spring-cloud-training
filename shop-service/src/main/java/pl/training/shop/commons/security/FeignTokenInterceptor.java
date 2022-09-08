package pl.training.shop.commons.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static pl.training.shop.commons.security.Token.TOKEN_PREFIX;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Token.getFromContext().ifPresent(token -> requestTemplate.header(AUTHORIZATION, TOKEN_PREFIX + token));
    }

}
