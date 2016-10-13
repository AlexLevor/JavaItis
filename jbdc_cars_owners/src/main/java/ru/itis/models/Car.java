package ru.itis.models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Car {
    private int car_id;
    private int mileage;
    private int owner_id;

    public Car(int car_id, int mileage, int owner_id){
        this.car_id = car_id;
        this.mileage = mileage;
        this.owner_id = owner_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }
}
