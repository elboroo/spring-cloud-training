package pl.training.orders.adapters.events;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderEventDto {

    BigDecimal amount;
    String currency;

}
