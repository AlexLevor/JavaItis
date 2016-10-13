package ru.itis.models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Owner {
    private int owner_id;
    private String city;
    private int age;
    private String name;

    public Owner(int owner_id,
             String city,
             int age,
             String name){
        this.owner_id = owner_id;
        this.city = city;
        this.age = age;
        this.name = name;
    }

}
