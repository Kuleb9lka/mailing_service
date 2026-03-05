package com.mailing_service.exception.handler;

import com.mailing_service.dto.ExceptionResponseDto;
import com.mailing_service.enums.ExceptionStatus;
import com.mailing_service.exception.SendingMailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SendingMailException.class)
    public ResponseEntity<ExceptionResponseDto> handleSendingMailException(SendingMailException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(ExceptionStatus.SENDING_MAIL_ERROR.name(), exception.getMessage()));
    }
}
