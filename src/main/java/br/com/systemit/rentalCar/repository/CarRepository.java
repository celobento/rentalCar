package br.com.systemit.rentalCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systemit.rentalCar.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findByModel(String model);

}