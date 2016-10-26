package ru.itis.services;

import ru.itis.dao.CarsDao;
import ru.itis.models.Car;

import java.util.List;


public class CarsServiceImpl implements CarsService {

    private CarsDao carsDao;

    public CarsServiceImpl(CarsDao carsDao){
        this.carsDao = carsDao;
    }
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
