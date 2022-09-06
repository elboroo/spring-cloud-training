package pl.training.shop.orders;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import pl.training.orders.ports.DiscountCalculator;

@RefreshScope
@Service
@Log
public class ConstantDiscountCalculator implements DiscountCalculator {

    @Getter
    private String value;

    @Value("${discountValue}")
    public void setValue(String value) {
        log.info("Updating discount value");
        this.value = value;
    }

}
