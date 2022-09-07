package pl.training.payments.adapters.stream;

import org.mapstruct.Mapper;
import pl.training.commons.money.FastMoneyMapper;
import pl.training.payments.ports.Payment;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface StreamPaymentMapper {

    PaymentDto toDto(Payment payment);

    Payment toPorts(PaymentDto paymentDto);

}
