package br.com.systemit.rentalCar.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.systemit.rentalCar.entity.CarEntity;
import br.com.systemit.rentalCar.exceptions.EntityNotFoundException;
import br.com.systemit.rentalCar.service.CarService;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarService carService;

    @Test
    void shouldSaveACar() throws Exception {
        var car = new CarEntity("Toyota Corolla", "sedan", 100.0, 2020);

        Mockito.when(carService.save(Mockito.any(CarEntity.class))).thenReturn(car);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/cars")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(car)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(car.getId()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(car.getName()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.model").value(car.getModel()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.year").value(car.getYear()));
    }

    @Test
    void shouldFindACarById() throws Exception {
        Long id = 1L;
        var car = new CarEntity("Toyota Corolla", "sedan", 100.0, 2020);
        car.setId(id);
        Mockito.when(carService.findById(id)).thenReturn(car);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/cars/{id}", car.getId())
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(car.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(car.getName()));
    }

    @Test
    void shouldReturnNotFoundWhenCarNotFound() throws Exception {
        Long id = 1L;
        Mockito.when(carService.findById(id)).thenThrow(new EntityNotFoundException("Car not found"));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/cars/{id}", id)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldReturnListOfCars() throws Exception {
        var cars = List.of(
                new CarEntity("Toyota Corolla", "sedan", 100.0, 2020),
                new CarEntity("Civic", "sedan", 100.0, 2022));
        Mockito.when(carService.findAll()).thenReturn(cars);
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/cars")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(cars.size()));
    }

    @Test
    void shouldUpdateACar() throws Exception {
        var car = new CarEntity("Toyota Corolla", "sedan", 100.0, 2020);
        car.setId(1L);

        Mockito.when(carService.update(Mockito.anyLong(), Mockito.any(CarEntity.class))).thenReturn(car);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.put("/cars/{id}", car.getId())
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(car)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteACar() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(carService).delete(Mockito.anyLong());
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete("/cars/{id}", id)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenCarNotFoundToDelete() throws Exception {

        Mockito.doThrow(new EntityNotFoundException("Car not found")).when(carService).delete(Mockito.anyLong());
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete("/cars/{id}", 1L)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}
