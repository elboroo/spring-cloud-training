package pl.training.payments.adapters.stream;

import lombok.Data;

@Data
public class PaymentDto {

    private String id;
    private String value;
    private String status;

}
