package pl.training.warehouse.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.warehouse.ports.InvalidateProductsUseCase;
import pl.training.warehouse.ports.ProductEvent;
import pl.training.warehouse.ports.ProductsEventsPublisher;

@Log
@RequiredArgsConstructor
public class InvalidateProductsService implements InvalidateProductsUseCase {

    private final ProductsEventsPublisher productsEventsPublisher;

    @Override
    public void invalidate() {
        log.info("Refreshing products");
        productsEventsPublisher.publish(new ProductEvent("Products refreshed"));
    }

}
