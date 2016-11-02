package ru.itis.services;

import ru.itis.models.Car;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public interface CarService {
    List<Car> getAllCars();
    Car findCar(int id);
    void updateCar(Car car);
    void addCar(Car car);
    void deleteCar(int id);
}
