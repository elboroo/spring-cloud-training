package pl.training.shop.orders.payments;

import lombok.Value;

@Value
public class PaymentRequestDto {

    Long requestId;
    String value;

}
