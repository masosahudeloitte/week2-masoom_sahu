package com.deloitte.issueservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IssueNotFoundException.class)
    public ResponseEntity<?> handleIssueNotFound(IssueNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity.status(500).body("Something went wrong: " + ex.getMessage());
    }
}
