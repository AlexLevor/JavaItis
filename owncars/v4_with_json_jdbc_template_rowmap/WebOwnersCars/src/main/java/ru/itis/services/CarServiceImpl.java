package ru.itis.services;

import ru.itis.dao.CarsDao;
import ru.itis.models.Car;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class CarServiceImpl implements CarService {
    private CarsDao carsDao;

    public CarServiceImpl(CarsDao carsDao){
        this.carsDao = carsDao;
    }

    public List<Car> getAllCarsOfOne(int id) {
        return this.carsDao.getAllCarsOfOne(id);    }

    public List<Car> getAllCars() {
        return this.carsDao.getAll();
    }

    public Car findCar(int id) {
        return carsDao.find(id);
    }

    public void updateCar(Car car) {
        this.carsDao.update(car);
    }

    public void addCar(Car car) {
        this.carsDao.add(car);
    }

    public void deleteCar(int id) {
        this.carsDao.delete(id);
    }
}
