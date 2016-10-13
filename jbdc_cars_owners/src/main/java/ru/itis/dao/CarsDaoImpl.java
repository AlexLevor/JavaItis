package ru.itis.dao;

import ru.itis.models.Car;
import ru.itis.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoImpl implements CarsDao {

    private Connection connection;

    // language=SQL
    private final String SQL_FIND_CAR = "SELECT * FROM cars WHERE car_id = ?;";

    // language=SQL
    private final String SQL_ADD_CAR = "INSERT into cars (mileage, owner_id) values(?, ?);";

    public CarsDaoImpl(Connection connection){
        this.connection = connection;
        }

    public List<Car> getAll() {
        return null;
    }

    public Car find(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CAR);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            return new Car(result.getInt("car_id"), result.getInt("mileage"), result.getInt("owner_id"));

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CAR);
            preparedStatement.setInt(1, car.getMileage());
            preparedStatement.setInt(2, car.getOwner_id());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update(Car car) {

    }

    public void delete(int id) {

    }
}
