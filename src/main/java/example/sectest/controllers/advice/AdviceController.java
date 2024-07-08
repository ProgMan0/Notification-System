package example.sectest.controllers.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception) {
        Map<String, Object> response = new HashMap<>();

        response.put("time", Instant.now());
        response.put("details", exception.getMessage());

        return ResponseEntity.badRequest()
                .body(response);
    }
}
