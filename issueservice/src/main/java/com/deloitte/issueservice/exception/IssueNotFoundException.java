package com.deloitte.issueservice.exception;


public class IssueNotFoundException extends RuntimeException {
    public IssueNotFoundException(String message) {
        super(message);
    }
}