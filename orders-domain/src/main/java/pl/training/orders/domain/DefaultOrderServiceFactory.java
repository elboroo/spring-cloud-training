package pl.training.orders.domain;

import org.mapstruct.factory.Mappers;
import pl.training.orders.ports.*;

public class DefaultOrderServiceFactory implements OrderServiceFactory {

    private static final OrderDomainMapper MAPPER = Mappers.getMapper(OrderDomainMapper.class);

    @Override
    public PlaceOrderUseCase create(PaymentService paymentService, ProductsProvider productsProvider, DiscountCalculator discountCalculator) {
        var placeOrderService = new PlaceOrderService(paymentService, discountCalculator);
        MAPPER.setProductsProvider(productsProvider);
        return new PlaceOrderUseCaseAdapter(placeOrderService, MAPPER);
    }

}
