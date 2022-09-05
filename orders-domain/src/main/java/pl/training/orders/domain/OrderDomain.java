package pl.training.orders.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.util.List;

import static pl.training.commons.money.Currency.DEFAULT_CURRENCY;

@Value
class OrderDomain {

    Long id;
    List<OrderEntryDomain> entries;

    FastMoney getValue() {
        return entries.stream()
                .map(OrderEntryDomain::getValue)
                .reduce(FastMoney.of(0, DEFAULT_CURRENCY), FastMoney::add);
    }

}
