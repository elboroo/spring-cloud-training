package pl.training.broker;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoPaymentRepository extends ReactiveMongoRepository<PaymentDocument, String> {
}
