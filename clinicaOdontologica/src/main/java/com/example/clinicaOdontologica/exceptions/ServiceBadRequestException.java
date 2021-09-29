package com.example.clinicaOdontologica.exceptions;

public class ServiceBadRequestException extends Exception{
    public ServiceBadRequestException(String message) {
        super(message);
    }
}
