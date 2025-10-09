package se.apiva.demospringboot.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This method is triggered if @Valid on a RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation Failed");
        pd.setDetail("One or more fields are invalid");

        var error = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> Map.of(
                        "field", fe.getField(),
                        "rejectedValue", (fe.getRejectedValue() != null ? fe.getRejectedValue() : "null"),
                        "message", fe.getDefaultMessage()
                )).toList();

        pd.setProperty("errors", error);

        return pd;
    }

    // This is triggered when @Validated and method level constraint are failing
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Constraint violation");
        pd.setDetail("Request parameters/path variables are invalid.");
        var errors = ex.getConstraintViolations().stream()
                .map(cv -> Map.of(
                        "property", cv.getPropertyPath().toString(),
                        "invalidValue", String.valueOf(cv.getInvalidValue()),
                        "message", cv.getMessage()))
                .toList();
        pd.setProperty("errors", errors);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }

}
