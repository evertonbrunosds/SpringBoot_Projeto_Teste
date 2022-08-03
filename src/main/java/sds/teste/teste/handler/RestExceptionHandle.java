package sds.teste.teste.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import sds.teste.teste.model.errors.ErrorMessager;
import sds.teste.teste.model.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(final ResourceNotFoundException ex) {
        final ErrorMessager error = new ErrorMessager(
            "Not Found",
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
