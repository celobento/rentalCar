package br.com.systemit.rentalCar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.systemit.rentalCar.entity.CarEntity;

@DataJpaTest
@ActiveProfiles("test") // remember to put the same name: application-"test"
public class CarRepositoryTest {

    private CarEntity car;

    @BeforeEach
    void setUp() {
        car = new CarEntity("Chevette", "hatch", 150.0, 1980);
    }

    @Autowired
    CarRepository repository;

    @Test
    void shouldSaveACar() {
        repository.save(car);
        assertNotNull(car.getId());
    }

    @Test
    void shouldSearchCarById() {
        repository.save(car);
        Optional<CarEntity> foundCar = repository.findById(car.getId());
        assertThat(foundCar).isPresent();
        assertThat(foundCar.get().getName()).isEqualTo("Chevette");
    }

    @Test
    void ShouldUpdateACar() {
        CarEntity createdCar = repository.save(car);
        createdCar.setModel("sedan");
        CarEntity updatedCar = repository.save(createdCar);
        assertThat(updatedCar.getModel()).isEqualTo("sedan");

    }

    @Test
    void ShouldDeleteACar() {
        CarEntity createdCar = repository.save(car);
        repository.deleteById(createdCar.getId());
        Optional<CarEntity> foundCar = repository.findById(createdCar.getId());
        assertThat(foundCar).isNotPresent();
    }

}
