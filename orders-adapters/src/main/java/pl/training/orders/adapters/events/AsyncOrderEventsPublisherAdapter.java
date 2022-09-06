package pl.training.orders.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import pl.training.orders.ports.OrderEvent;
import pl.training.orders.ports.OrdersEventPublisher;

@Component
@RequiredArgsConstructor
public class AsyncOrderEventsPublisherAdapter implements OrdersEventPublisher {

    private static final String ORDERS_CHANNEL = "orders-out-0";

    private final StreamBridge streamBridge;

    @Override
    public void publish(OrderEvent orderEvent) {
        streamBridge.send(ORDERS_CHANNEL, new OrderEventDto(orderEvent.getAmount(), orderEvent.getCurrency()));
    }

}
