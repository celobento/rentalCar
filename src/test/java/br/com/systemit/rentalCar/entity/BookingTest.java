package br.com.systemit.rentalCar.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.systemit.rentalCar.exceptions.BookingInvalidException;
import br.com.systemit.rentalCar.model.Booking;
import br.com.systemit.rentalCar.model.Car;
import br.com.systemit.rentalCar.model.Customer;

public class BookingTest {

    private Customer customer;
    private Car car;

    @BeforeEach
    public void setUp() {
        customer = new Customer("John Doe");
        car = new Car("Toyota Corolla", "sedan", 100.0, 2020);
    }

    @Test
    @DisplayName("Should create a booking")
    public void shouldCreateABooking() {
        // 1. create scenario
        int days = 2;
        Booking booking = new Booking(customer, car, days);
        // 2. perform action
        assertNotNull(booking.getCustomer());
    }

    @Test
    @DisplayName("Should calculate total amount")
    public void shouldCalculateTotalAmount() {
        // 1. create scenario
        int days = 2;
        Booking booking = new Booking(customer, car, days);
        // 2. perform action
        double totalAmount = booking.calculateTotalAmount();
        // 3. verify result
        assertEquals(200.0, totalAmount);
    }

    @Test
    @DisplayName("Should throw exception when days is less than 1")
    public void shouldThrowExceptionWhenDaysIsLessThan1() {
        // 1. create scenario
        int days = 0;
        // 3. verify result
        assertThrows(BookingInvalidException.class,
                () -> {
                    new Booking(customer, car, days).calculateTotalAmount();
                });
    }

    @Test
    public void shouldThrowExceptionWithNegativeDays() {
        var erro = Assertions.catchThrowable(() -> new Booking(customer, car, 0));
        Assertions.assertThat(erro)
                .isInstanceOf(BookingInvalidException.class)
                .hasMessage("The number of days must be greater than 0");
    }
}
