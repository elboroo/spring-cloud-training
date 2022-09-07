package pl.training.broker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.*;

@Adapter
@RequiredArgsConstructor
public class PaymentHandler {

    private final PaymentService paymentService;
    private final PaymentMapper mapper;

    public Mono<ServerResponse> process(ServerRequest serverRequest) {
        var paymentDomain = serverRequest.bodyToMono(PaymentDto.class).map(mapper::toDomain);
        var paymentDto = paymentService.process(paymentDomain).map(mapper::toDto);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(paymentDto, PaymentDto.class);
    }

    public Mono<ServerResponse> getPayments(ServerRequest serverRequest) {
        var paymentDtos = paymentService.getPaymentsStream().map(mapper::toDto);
        return ServerResponse.ok()
                //.contentType(APPLICATION_STREAM_JSON)
                .contentType(APPLICATION_NDJSON)
                .body(paymentDtos, PaymentDto.class);
    }

}
