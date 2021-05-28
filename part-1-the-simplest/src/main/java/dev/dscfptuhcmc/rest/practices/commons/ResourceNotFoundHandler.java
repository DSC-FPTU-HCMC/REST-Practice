package dev.dscfptuhcmc.rest.practices.commons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author quangdatpham
 */
@ControllerAdvice
public class ResourceNotFoundHandler {

      @ExceptionHandler(ResourceNotFoundException.class)
      @ResponseStatus(HttpStatus.NOT_FOUND)
      String resourceNotFoundHandler(ResourceNotFoundException ex) {
        return ex.getMessage();
      }
}
