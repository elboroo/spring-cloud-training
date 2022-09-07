package pl.training.broker;

import reactor.core.publisher.Mono;

public interface PaymentRepository {

    Mono<PaymentDomain> save(PaymentDomain paymentDomain);

}
