package id.kostfinder.app.exception;

import id.kostfinder.app.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GenericResponse> handleGeneralException(GeneralException ex) {
        GenericResponse response = GenericResponse.builder()
                .code(ex.getCode())
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class) // Catch all unhandled exceptions
    public ResponseEntity<GenericResponse> handleGlobalException(Exception ex) {
        GenericResponse response = GenericResponse.builder()
                .code(500)
                .success(false)
                .message("An unexpected error occurred: " + ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
