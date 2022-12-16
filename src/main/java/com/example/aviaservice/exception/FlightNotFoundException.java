package com.example.aviaservice.exception;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException() {
        super();
    }

    public FlightNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ExceptionConstant.FLIGHT_NOT_FOUND;
    }

}
