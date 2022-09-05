package pl.training.orders.ports;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface PaymentService {

    Optional<Payment> pay(BigDecimal value, String currency, Map<String, String> properties);

}
