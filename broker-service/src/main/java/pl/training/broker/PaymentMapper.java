package pl.training.broker;

import org.mapstruct.Mapper;
import pl.training.commons.money.FastMoneyMapper;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface PaymentMapper {

    PaymentDomain toDomain(PaymentDocument paymentDocument);

    PaymentDocument toDocument(PaymentDomain paymentDomain);

    PaymentDto toDto(PaymentDomain paymentDomain);

    PaymentDomain toDomain(PaymentDto paymentDto);

}
