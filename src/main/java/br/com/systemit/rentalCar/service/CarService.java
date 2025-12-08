package br.com.systemit.rentalCar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.systemit.rentalCar.entity.CarEntity;
import br.com.systemit.rentalCar.exceptions.EntityNotFoundException;
import br.com.systemit.rentalCar.repository.CarRepository;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarEntity save(CarEntity car) {
        if (car.getDailyRate() <= 0) {
            throw new IllegalArgumentException("Daily rate must be greater than 0");
        }
        return carRepository.save(car);
    }

    public CarEntity update(Long id, CarEntity car) {
        var existingCar = carRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        existingCar.setName(car.getName());
        existingCar.setModel(car.getModel());
        existingCar.setDailyRate(car.getDailyRate());
        existingCar.setYear(car.getYear());
        return carRepository.save(existingCar);
    }

    public void delete(Long id) {
        var existingCar = carRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        carRepository.delete(existingCar);
    }

    public CarEntity findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
    }

    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }
}
