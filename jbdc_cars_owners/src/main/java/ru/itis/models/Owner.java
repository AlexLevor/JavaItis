package ru.itis.models;

import com.google.common.base.MoreObjects;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Owner {
    private int id;
    private String city;
    private int age;
    private String name;

    public Owner(String city,
             int age,
             String name){
        this.city = city;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.id)
                .add("city", this.city)
                .add("age", this.age)
                .add("name", this.name)
                .toString();
    }
}
