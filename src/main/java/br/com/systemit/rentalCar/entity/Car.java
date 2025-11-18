package br.com.systemit.rentalCar.entity;

public class Car {

    private String model;
    private double dailyRate;

    public Car(String model, double dailyRate) {
        this.model = model;
        this.dailyRate = dailyRate;
    }

    public double calculateDailyRentalRate(int days) {
        double discount = 0;
        if (days >= 5) {
            discount = 50.0;
        }
        return (dailyRate * days) - discount;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }
}
