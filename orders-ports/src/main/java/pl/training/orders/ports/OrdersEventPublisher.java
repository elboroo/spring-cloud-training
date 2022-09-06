package pl.training.orders.ports;

public interface OrdersEventPublisher {

    void publish(OrderEvent event);

}
