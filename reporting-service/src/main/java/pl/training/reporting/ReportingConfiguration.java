package pl.training.reporting;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Log
@Configuration
public class ReportingConfiguration {

    @Bean
    public Consumer<OrderEventDto> ordersEvents() {
        return ordersEventDto -> {
            log.info("Orders event received: " + ordersEventDto);
        };
    }

}
