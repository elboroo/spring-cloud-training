package pl.training.warehouse.ports;

public interface ProductsEventsPublisher {

    void publish(ProductEvent productEvent);

}
