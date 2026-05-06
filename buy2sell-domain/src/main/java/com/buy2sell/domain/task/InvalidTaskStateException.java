package com.buy2sell.domain.task;

public class InvalidTaskStateException extends DomainException {

    public InvalidTaskStateException(String message) {
        super(message);
    }
}
