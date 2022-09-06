package pl.training.orders.adapters.products;

import feign.FeignException;
import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("products")
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

}
