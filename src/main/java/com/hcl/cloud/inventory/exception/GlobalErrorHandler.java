package com.hcl.cloud.inventory.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiRuntimeException.class)
    protected ResponseEntity<ApiRuntimeException.ErrorMessage> productAlreadyExists(
            ApiRuntimeException ex, WebRequest request) {
        log.error("Product already exist exception handler called....");

        return new ResponseEntity<ApiRuntimeException.ErrorMessage>
                (ex.getErrorResponse(), ex.getHttpStatus());
    }

}
