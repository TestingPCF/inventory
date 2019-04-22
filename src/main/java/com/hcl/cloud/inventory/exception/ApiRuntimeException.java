package com.hcl.cloud.inventory.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

public class ApiRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -1392554312174104762L;
	
	private int code;
	private int status;
    private String message;

    public ApiRuntimeException() {
        super();
    }

    public ApiRuntimeException(final int code, final int status, final String message) {
        super(message);
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public HttpStatus getHttpStatus()
    {
        return HttpStatus.valueOf(status);
    }

    public ErrorMessage getErrorResponse() {
        return new ErrorMessage(code, message);
    }

    @AllArgsConstructor
    @Data
    class ErrorMessage {
        private int code;
        private String message;
    }
}
