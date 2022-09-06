package pl.training.orders.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.orders.ports.DiscountCalculator;
import pl.training.orders.ports.PaymentService;

import java.math.BigDecimal;

import static java.util.Collections.emptyMap;
import static pl.training.commons.money.Currency.DEFAULT_CURRENCY;

@Log
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PlaceOrderService {

    private final PaymentService paymentService;
    private final DiscountCalculator discountCalculator;

    void place(OrderDomain orderDomain) {
        var discount = FastMoney.parse(discountCalculator.getValue());
        var totalValue = orderDomain.getValue().subtract(discount);
        var paymentValue = BigDecimal.valueOf(totalValue.getNumber().doubleValueExact());
        var payment = paymentService.pay(paymentValue, DEFAULT_CURRENCY, emptyMap());
        log.info("New order: " + orderDomain);
        log.info("New order payment: " + payment);
        log.info("Order total value: " + totalValue);
    }

}
