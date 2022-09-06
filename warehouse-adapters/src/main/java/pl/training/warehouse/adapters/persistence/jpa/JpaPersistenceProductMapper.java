package pl.training.warehouse.adapters.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.commons.money.Currency;
import pl.training.warehouse.ports.Product;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, Currency.class})
public interface JpaPersistenceProductMapper {

    @Mapping(source = "price", target = "value")
    ProductEntity toEntity(Product product);

    @Mapping(source = "value", target = "price")
    Product toDomain(ProductEntity entity);

}
