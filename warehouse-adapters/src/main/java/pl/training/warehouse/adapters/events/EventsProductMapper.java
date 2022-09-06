package pl.training.warehouse.adapters.events;

import org.mapstruct.Mapper;
import pl.training.warehouse.ports.ProductEvent;

@Mapper(componentModel = "spring")
public interface EventsProductMapper {

    ProductEventDto toDto(ProductEvent productEvent);

}
