package pl.training.shop;

import lombok.extern.java.Log;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.shop.products.ProductEventDto;

import java.util.function.Consumer;

@Log
@EnableCaching
@EnableFeignClients(basePackages = "pl.training")
@Configuration
public class ShopConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:4200");
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("products");
    }

    @Bean
    public Consumer<ProductEventDto> productsEvents(CacheManager cacheManager) {
        return productEventDto -> {
            log.info("Products event received: " + productEventDto);
            cacheManager.getCache("products").clear();
        };
    }

}
