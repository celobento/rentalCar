package br.com.systemit.rentalCar.model;

public class Car {

    private String name;
    private String model;
    private double dailyRate;
    private int year;

    public Car(String name, String model, double dailyRate, int year) {
        this.name = name;
        this.model = model;
        this.dailyRate = dailyRate;
        this.year = year;
    }

    public double calculateDailyRentalRate(int days) {
        double discount = 0;
        if (days >= 5) {
            discount = 50.0;
        }
        return (dailyRate * days) - discount;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
