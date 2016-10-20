package ru.itis.dao;

import ru.itis.models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoImpl implements CarsDao {

    private Connection connection;

    //language=SQL
    private final String SQL_ALL_CARS = "SELECT * FROM cars";

    // language=SQL
    private final String SQL_FIND_CAR = "SELECT * FROM cars WHERE car_id = ?;";

    // language=SQL
    private final String SQL_ADD_CAR = "INSERT into cars (mileage, owner_id) values(?, ?)";

    // language=SQL
    private final String SQL_UPDATE_CAR = "UPDATE cars SET mileage = ? , owner_id = ? WHERE car_id = ?";

    // language=SQL
    private final String SQL_DELETE_CAR = "DELETE FROM cars WHERE car_id = ?";

    public CarsDaoImpl(Connection connection){
        this.connection = connection;
        }

    public List<Car> getAll() {
        try {
            List<Car> cars = new ArrayList<Car>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_ALL_CARS);
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("car_id"), resultSet.getInt("mileage"),
                        resultSet.getInt("owner_id"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
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
            preparedStatement.setInt(2, car.getOwnerId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CAR);
            preparedStatement.setInt(1, car.getMileage());
            preparedStatement.setInt(2, car.getOwnerId());
            preparedStatement.setInt(3, car.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CAR);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
