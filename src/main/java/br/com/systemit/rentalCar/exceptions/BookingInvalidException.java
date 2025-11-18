package br.com.systemit.rentalCar.exceptions;

public class BookingInvalidException extends RuntimeException {
    public BookingInvalidException(String message) {
        super(message);
    }
}
