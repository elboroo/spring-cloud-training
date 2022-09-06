package pl.training.reporting;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
public class OrderEventDto {

    private BigDecimal amount;
    private String currency;

}
