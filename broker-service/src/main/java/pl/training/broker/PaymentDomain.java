
package pl.training.broker;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.javamoney.moneta.FastMoney;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Value
public class PaymentDomain {

    String id;
    FastMoney value;
    String status;

}
