package pl.training.payments.adapters.stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.training.payments.ports.Payment;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
@RequiredArgsConstructor
@Log
public class StreamPaymentsBroker {

    private static final String BROKER_SERVICE_NAME = "BROKER-SERVICE";
    private static final String PAYMENTS_ENDPOINT_URI = "/payments";

    private final DiscoveryClient discoveryClient;
    private final StreamPaymentMapper mapper;

    @PostConstruct
    public void init() {
        WebClient.builder().build()
                .get()
                .uri(getBrokerUri())
                .retrieve()
                .bodyToFlux(PaymentDto.class)
                .subscribe(paymentDto -> log.info("Payment update: " + paymentDto), throwable -> log.warning(throwable.toString()));
    }

    public void process(Payment payment) {
        WebClient.builder().build()
                .post()
                .uri(getBrokerUri())
                .bodyValue(mapper.toDto(payment))
                .retrieve()
                .bodyToMono(PaymentDto.class)
                .subscribe(paymentDto -> log.info("Payment processed: " + paymentDto), throwable -> log.warning(throwable.toString()));
    }

    private URI getBrokerUri() {
        var instances = discoveryClient.getInstances(BROKER_SERVICE_NAME);
        var instance = instances.stream().findFirst()
                .orElseThrow(IllegalStateException::new);
        return instance.getUri().resolve(PAYMENTS_ENDPOINT_URI);

    }

}
