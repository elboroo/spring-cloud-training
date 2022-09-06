package pl.training.warehouse.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
class ProductDomain {

    Long id;
    String name;
    FastMoney price;

}
