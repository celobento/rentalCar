package br.com.systemit.rentalCar.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.systemit.rentalCar.model.Customer;

public class CustomerTest {

    @Test
    @DisplayName("Should create a customer")
    public void shouldCreateACustomer() {
        // 1. create scenario
        Customer customer = new Customer("John Doe");
        // 2. perform action
        String name = customer.getName();
        // 3. verify result
        assertThat(name).isEqualTo("John Doe");
        assertEquals("John Doe", name);
    }

    @Test
    @DisplayName("Should create a customer and name not null")
    public void shouldCreateACustomerAndNameNotNull() {
        // 1. create scenario
        Customer customer = new Customer("John Doe");
        // 2. perform action
        assertNotNull(customer.getName());
    }

    @Test
    @DisplayName("verify Name starts with Capital Letter")
    public void verifyNameStartsWithCapitalLetter() {
        // 1. create scenario
        Customer customer = new Customer("John doe");
        // 2. perform action
        assertTrue(customer.getName().startsWith("John"));
    }

}
