package com.anz.payments.data.scheduler.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
 public class ValidationError {
    private final String field;
    private final String message;
}