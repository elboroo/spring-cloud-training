package pl.training.orders.adapters.products;

import feign.FeignException.FeignClientException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.training.orders.ports.GetProductFailedException;
import pl.training.orders.ports.Product;
import pl.training.orders.ports.ProductsProvider;

import java.util.Optional;

@Primary
@Component
@Log
@RequiredArgsConstructor
public class FeignProductsProviderAdapter implements ProductsProvider {

    private final ProductsApi productsApi;
    private final RestProductMapper mapper;

    //@CircuitBreaker(name = "products", fallbackMethod = "getByIdFallback")
    @Retry(name = "products")
    // @Retry(attempts = 5)
    // @Cacheable("products")
    @Override
    public Optional<Product> getById(Long id) {
        try {
            var productDto = productsApi.getById(id);
            return Optional.ofNullable(mapper.toPorts(productDto));
        } catch (FeignClientException exception) {
            log.warning(exception.getMessage());
            throw new GetProductFailedException();
        }
    }

    public Optional<Product> getByIdFallback(Long id, Throwable throwable) {
        log.info("Executing fallback method");
        return Optional.of(new Product(id, "Fake product", "200 PLN"));
    }

}
