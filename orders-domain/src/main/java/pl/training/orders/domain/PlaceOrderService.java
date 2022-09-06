package pl.training.orders.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.orders.ports.DiscountCalculator;
import pl.training.orders.ports.OrderEvent;
import pl.training.orders.ports.OrdersEventPublisher;
import pl.training.orders.ports.PaymentService;

import java.math.BigDecimal;

import static java.util.Collections.emptyMap;
import static pl.training.commons.money.Currency.DEFAULT_CURRENCY;

@Log
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PlaceOrderService {

    private final PaymentService paymentService;
    private final DiscountCalculator discountCalculator;
    private final OrdersEventPublisher ordersEventPublisher;

    void place(OrderDomain orderDomain) {
        var discount = FastMoney.parse(discountCalculator.getValue());
        var totalValue = orderDomain.getValue().subtract(discount);
        var paymentValue = BigDecimal.valueOf(totalValue.getNumber().doubleValueExact());
        var payment = paymentService.pay(paymentValue, DEFAULT_CURRENCY, emptyMap());
        var newOrderEvent = new OrderEvent();
        newOrderEvent.setAmount(paymentValue);
        newOrderEvent.setCurrency(DEFAULT_CURRENCY);
        ordersEventPublisher.publish(newOrderEvent);
        log.info("New order: " + orderDomain);
        log.info("New order payment: " + payment);
        log.info("Order total value: " + totalValue);
    }

}
