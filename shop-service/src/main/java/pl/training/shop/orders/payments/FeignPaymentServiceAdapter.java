package pl.training.shop.orders.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.orders.ports.Payment;
import pl.training.orders.ports.PaymentService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static pl.training.commons.money.Currency.CURRENCY_SEPARATOR;

@Component
@RequiredArgsConstructor
public class FeignPaymentServiceAdapter implements PaymentService {

    private final PaymentsApi paymentsApi;

    @Override
    public Optional<Payment> pay(BigDecimal value, String currency, Map<String, String> properties) {
        var paymentRequestDto = new PaymentRequestDto(1L,value + CURRENCY_SEPARATOR + currency);
        var paymentDto = paymentsApi.process(paymentRequestDto);
        return Optional.of(new Payment(paymentDto.getId(), paymentDto.getStatus()));
    }

}
