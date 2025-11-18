package br.com.systemit.rentalCar.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarroTest {

    @Test
    @DisplayName("Should calculate amount of rental")
    public void shouldCalculateAmountOfRental() {
        // 1. create scenario
        Car car = new Car("Toyota Corolla", 100.0);
        // 2. perform action
        double amount = car.calculateDailyRentalRate(2);
        // 3. verify result
        assertEquals(200.0, amount);
    }

    @Test
    @DisplayName("Should calculate amount of rental with discount")
    public void shouldCalculateAmountOfRentalWithDiscount() {
        // 1. create scenario
        Car car = new Car("Toyota Corolla", 100.0);
        int days = 5;
        // 2. perform action
        double amount = car.calculateDailyRentalRate(days);
        // 3. verify result
        assertEquals(450.0, amount);
    }

}
