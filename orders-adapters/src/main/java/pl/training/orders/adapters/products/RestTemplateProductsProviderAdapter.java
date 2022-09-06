package pl.training.orders.adapters.products;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.orders.ports.GetProductFailedException;
import pl.training.orders.ports.Product;
import pl.training.orders.ports.ProductsProvider;

import java.util.Optional;

@Primary
@Component
@Log
@RequiredArgsConstructor
public class RestTemplateProductsProviderAdapter implements ProductsProvider {

    private final RestTemplate restTemplate;
    private final RestProductMapper mapper;
    @Value("${api.products}")
    @Setter
    private String productsEndpoint;

    @Override
    public Optional<Product> getById(Long id) {
        try {
            var productDto = restTemplate.getForObject(productsEndpoint + id, ProductDto.class);
            return Optional.ofNullable(mapper.toPorts(productDto));
        } catch (RestClientException exception) {
            log.warning(exception.getMessage());
            throw new GetProductFailedException();
        }
    }

}
