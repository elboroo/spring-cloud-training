package pl.training.orders.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.orders.ports.PaymentService;

import java.math.BigDecimal;

import static java.util.Collections.emptyMap;
import static pl.training.commons.money.Currency.DEFAULT_CURRENCY;

@Log
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PlaceOrderService {

    private final PaymentService paymentService;

    void place(OrderDomain orderDomain) {
        var totalValue = orderDomain.getValue();
        var paymentValue = BigDecimal.valueOf(totalValue.getNumber().doubleValueExact());
        var payment = paymentService.pay(paymentValue, DEFAULT_CURRENCY, emptyMap());
        log.info("New order: " + orderDomain);
        log.info("New order payment: " + payment);
    }

}
