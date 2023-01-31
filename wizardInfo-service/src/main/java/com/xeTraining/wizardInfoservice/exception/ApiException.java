package com.xeTraining.wizardInfoservice.exception;


import com.xeTraining.wizardInfoservice.exception.server.InvalidWizardException;
import com.xeTraining.wizardInfoservice.exception.server.ServerErrorException;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
class ApiException {
    @Autowired
    private Tracer tracer;
    private static final Logger log = LoggerFactory.getLogger(ApiException.class);

    public String getTraceId() {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            traceID = span.context().traceId();
        }
        return traceID;
    }

    @ExceptionHandler(InvalidWizardException.class)
    public Map<String, Object> validationError(InvalidWizardException e) {
        Map<String, Object> message = new HashMap<>();
        String invalidWizardTraceId = getTraceId();
        message.put("code:", HttpStatus.BAD_REQUEST.value());
        message.put("message:", e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("NOK", 1, LocalDate.now(), invalidWizardTraceId, message);
        log.info("InvalidWizardExceptionTraceId: {}", e);
        log.info(String.valueOf(errorResponse.errorResponse()));
        return errorResponse.errorResponse();
    }

    @ExceptionHandler(ServerErrorException.class)
    public Map<String, Object> handleServerErrorException(ServerErrorException e) {
        Map<String, Object> message = new HashMap<>();
        String serverError = getTraceId();
        message.put("code:", HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.put("message:", e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("NOK", 1, LocalDate.now(), serverError, message);
        log.info("ServerErrorExceptionTraceId: {}", e);
        log.info(String.valueOf(errorResponse.errorResponse()));
        return errorResponse.errorResponse();
    }
}