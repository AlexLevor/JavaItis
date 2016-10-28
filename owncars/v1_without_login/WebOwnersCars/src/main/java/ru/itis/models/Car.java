package ru.itis.models;

import com.google.common.base.MoreObjects;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class Car {
    private int car_id;
    private int mileage;
    private int owner_id;

    public Car( int mileage, int owner_id){

        this.mileage = mileage;
        this.owner_id = owner_id;
    }
    public Car(int car_id, int mileage, int owner_id){

        this.mileage = mileage;
        this.owner_id = owner_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getMileage() {
        return mileage;
    }

    public int getOwner_id() {
        return owner_id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("car_id", this.car_id)
                .add("mileage", this.mileage)
                .add("owner_id", this.owner_id)
                .toString();
    }

}
