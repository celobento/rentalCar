package br.com.systemit.rentalCar.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import br.com.systemit.rentalCar.entity.CarEntity;

@DataJpaTest
@ActiveProfiles("tests")
public class CarRepositorySQLTest {

    @Autowired
    CarRepository repository;

    @Test
    @Sql("/sql/cars.sql")
    void shouldSearchCarByModel() {
        List<CarEntity> cars = repository.findByModel("suv");
        assertEquals(2, cars.size());
    }

    @Test
    @Sql("/sql/cars.sql")
    void shouldSearchCarByModelSUV() {
        List<CarEntity> cars = repository.findByModel("suv");
        var car = cars.stream().findFirst().orElseThrow(() -> new RuntimeException("Car not found"));
        assertEquals(2, cars.size());
        assertEquals("suv", car.getModel());
    }

}
