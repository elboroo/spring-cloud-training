package pl.training.warehouse.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import pl.training.warehouse.ports.ProductEvent;
import pl.training.warehouse.ports.ProductsEventsPublisher;

@Component
@RequiredArgsConstructor
public class AsyncProductsEventsPublisherAdapter implements ProductsEventsPublisher {

    private static final String PRODUCTS_CHANNEL = "products-out-0";

    private final StreamBridge streamBridge;
    private final EventsProductMapper mapper;

    @Override
    public void publish(ProductEvent productEvent) {
        streamBridge.send(PRODUCTS_CHANNEL, mapper.toDto(productEvent));
    }

}
