package ru.itis.models;

import com.google.common.base.MoreObjects;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class Owner {

    private int id;
    private String city;
    private int age;
    private String name;
    private String login;
    private String password;

    public Owner(String city,
                 int age,
                 String name,
                 String login) {
        this.city = city;
        this.age = age;
        this.name = name;
        this.login = login;
    }
    public Owner(int id,
                 String city,
                 int age,
                 String name,
                 String login,
                 String password){
        this.id = id;
        this.city = city;
        this.age = age;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Owner(String city,
                 int age,
                 String name){
        this.city = city;
        this.age = age;
        this.name = name;
    }


    public Owner(int id, String city, int age, String name, String login) {
        this.id = id;
        this.city = city;
        this.age = age;
        this.name = name;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.id)
                .add("city", this.city)
                .add("age", this.age)
                .add("name", this.name)
                .add("login", this.login)
                .toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
