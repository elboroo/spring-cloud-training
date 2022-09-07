package pl.training.broker;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Adapter
@RequiredArgsConstructor
public class MongoPaymentRepositoryAdapter implements PaymentRepository {

    private final MongoPaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    @Override
    public Mono<PaymentDomain> save(PaymentDomain paymentDomain) {
        var document = mapper.toDocument(paymentDomain);
        return paymentRepository.save(document).map(mapper::toDomain);
    }

}
