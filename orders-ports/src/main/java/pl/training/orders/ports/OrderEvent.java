package pl.training.orders.ports;

import lombok.Data;

import java.math.BigDecimal;

import static pl.training.orders.ports.OrderEvent.OrderEventType.CREATED;

@Data
public class OrderEvent {

    private BigDecimal amount;
    private String currency;
    private OrderEventType type = CREATED;

    enum OrderEventType {

        CREATED

    }

}
