package br.com.systemit.rentalCar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.systemit.rentalCar.entity.CarEntity;
import br.com.systemit.rentalCar.exceptions.EntityNotFoundException;
import br.com.systemit.rentalCar.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    CarEntity car;

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        car = new CarEntity("Chevette", "hatch", 150.0, 1980);
    }

    @Test
    void ShouldSaveACar() {
        car.setId(1L);

        Mockito
                .when(carRepository.save(Mockito.any(CarEntity.class)))
                .thenReturn(car);

        var savedCar = carService.save(car);

        assertNotNull(savedCar);
        assertEquals("hatch", savedCar.getModel());

    }

    @Test
    void shouldThrowExceptionOnSaveCarWithInvalidDailyRate() {
        car.setDailyRate(0.0);
        var erro = catchThrowable(() -> carService.save(car));
        assertThat(erro).isInstanceOf(IllegalArgumentException.class);
        Mockito.verify(carRepository, Mockito.never()).save(Mockito.any(CarEntity.class));
    }

    @Test
    void shouldUpdateACar() {
        Mockito
                .when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(car));

        Mockito
                .when(carRepository.save(Mockito.any(CarEntity.class)))
                .thenReturn(car);

        car.setId(1L);

        var updatedCar = carService.update(car.getId(), car);
        assertNotNull(updatedCar);
        assertEquals("hatch", updatedCar.getModel());
    }

    @Test
    void shouldThrowExceptionOnUpdateCarNotFound() {
        Long id = 1L;
        Mockito
                .when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carService.update(id, car));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(carRepository, Mockito.never()).save(Mockito.any(CarEntity.class));
    }

    @Test
    void shouldThrowExceptionOnDeleteCarNotFound() {
        Long id = 1L;
        Mockito
                .when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carService.delete(id));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        Mockito
                .verify(carRepository, Mockito.never())
                .delete(Mockito.any(CarEntity.class));
    }

    @Test
    void shouldDeleteACar() {
        Long id = 1L;
        Mockito
                .when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(car));
        carService.delete(id);
        Mockito.verify(carRepository, Mockito.times(1)).delete(Mockito.any(CarEntity.class));
    }

    @Test
    void shouldSearchACarById() {
        Long id = 1L;
        Mockito
                .when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(car));
        var car = carService.findById(id);
        assertNotNull(car);
        assertEquals("hatch", car.getModel());
    }

    @Test
    void ShouldReturnAllCars() {
        Mockito
                .when(carRepository.findAll())
                .thenReturn(List.of(car));
        var cars = carService.findAll();
        assertNotNull(cars);
        assertEquals(1, cars.size());
        assertEquals("hatch", cars.get(0).getModel());
        Mockito.verify(carRepository,
                Mockito.times(1)).findAll();
    }

}
