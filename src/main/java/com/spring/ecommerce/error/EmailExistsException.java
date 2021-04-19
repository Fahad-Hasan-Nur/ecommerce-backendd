package com.spring.ecommerce.error;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException() {
        super("Email already registered...!!" );
    }

}
