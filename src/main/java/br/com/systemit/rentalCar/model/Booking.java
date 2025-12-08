package br.com.systemit.rentalCar.model;

import br.com.systemit.rentalCar.exceptions.BookingInvalidException;

public class Booking {

    private Customer customer;
    private Car car;
    private int days;

    public Booking(Customer customer, Car car, int days) {
        if (days < 1) {
            throw new BookingInvalidException("The number of days must be greater than 0");
        }
        this.customer = customer;
        this.car = car;
        this.days = days;
    }

    public double calculateTotalAmount() {
        return car.calculateDailyRentalRate(days);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

}
