package pl.training.broker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final String CONFIRMED_STATUS = "CONFIRMED";

    private final PaymentRepository paymentRepository;
    private final Sinks.Many<PaymentDomain> paymentsStream = Sinks.many().replay().all(10);

    public Flux<PaymentDomain> getPaymentsStream() {
        return paymentsStream.asFlux();
    }

    public Mono<PaymentDomain> process(Mono<PaymentDomain> payments) {
        return payments.map(this::process)
                .flatMap(paymentRepository::save)
                .doOnNext(paymentsStream::tryEmitNext);
    }

    private PaymentDomain process(PaymentDomain payment) {
        return PaymentDomain.builder()
                .value(payment.getValue())
                .status(CONFIRMED_STATUS)
                .build();
    }

}
