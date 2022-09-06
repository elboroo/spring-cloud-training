package pl.training.orders.adapters.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "WAREHOUSE-SERVICE", name = "warehouse")
public interface ProductsApi {

    @GetMapping("products/{id}")
    ProductDto getById(@PathVariable Long id);

}
