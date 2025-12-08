package br.com.systemit.rentalCar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_id")
    private Long id;

    @Column(name = "dsc_name")
    private String name;

    @Column(name = "dsc_model")
    private String model;

    @Column(name = "num_daily_date")
    private double dailyRate;

    @Column(name = "num_year")
    private int year;

    public CarEntity(String name, String model, double dailyRate, int year) {
        this.name = name;
        this.model = model;
        this.dailyRate = dailyRate;
        this.year = year;
    }

}
