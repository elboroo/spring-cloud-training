package pl.training.warehouse.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.warehouse.ports.InvalidateProductsUseCase;

@RequestMapping("products/active")
@RestController
@RequiredArgsConstructor
public class InvalidateProductsUseCaseController {

    private final InvalidateProductsUseCase invalidateProductsUseCase;

    @DeleteMapping
    public ResponseEntity<Void> invalidate() {
        invalidateProductsUseCase.invalidate();
        return ResponseEntity.noContent().build();
    }

}
