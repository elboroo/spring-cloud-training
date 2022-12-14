package pl.training.broker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Value
public class PaymentDto {

    @JsonProperty(access = READ_ONLY)
    String id;
    String value;
    @JsonProperty(access = READ_ONLY)
    String status;

}
