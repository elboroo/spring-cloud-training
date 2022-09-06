package pl.training.orders.adapters.products;

import org.mapstruct.Mapper;
import pl.training.orders.ports.Product;

import static pl.training.commons.money.Currency.CURRENCY_SEPARATOR;
import static pl.training.commons.money.Currency.DEFAULT_CURRENCY;

@Mapper(componentModel = "spring")
public interface RestProductMapper {

    default Product toPorts(ProductDto productDto) {
        var price = productDto.getPrice() + CURRENCY_SEPARATOR + DEFAULT_CURRENCY;
        return new Product(productDto.getId(), productDto.getName(), price);
    }

}
