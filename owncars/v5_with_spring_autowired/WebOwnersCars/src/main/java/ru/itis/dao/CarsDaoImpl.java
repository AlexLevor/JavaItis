package ru.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.configurations.Beans;
import ru.itis.models.Car;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexLevor on 26.10.2016.
 */
@Repository
public class CarsDaoImpl implements CarsDao {

    private final String SQL_ALL_CARS = "SELECT * FROM cars";

    private final String SQL_ALL_CARS_OF_ONE = "SELECT * FROM cars WHERE owner_id = :owner_id";

    private final String SQL_FIND_CAR = "SELECT * FROM cars WHERE car_id = :car_id";

    private final String SQL_ADD_CAR = "INSERT into cars (mileage, owner_id) values(:mileage, :owner_id)";

    private final String SQL_UPDATE_CAR = "UPDATE cars SET mileage = :mileage , owner_id = :owner_id WHERE car_id = :car_id";

    private final String SQL_DELETE_CAR = "DELETE FROM cars WHERE car_id = :car_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CarsDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Car> getAllCarsOfOne(int id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("owner_id", id);
        return namedParameterJdbcTemplate.query(SQL_ALL_CARS_OF_ONE, param, new RowMapper<Car>() {
            public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Car(resultSet.getInt("car_id"),resultSet.getInt("mileage"),resultSet.getInt("owner_id"));
            }
        });
    }
    public List<Car> getAll() {
        return namedParameterJdbcTemplate.query(SQL_ALL_CARS, new RowMapper<Car>() {
            public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Car(resultSet.getInt("car_id"),resultSet.getInt("mileage"),resultSet.getInt("owner_id"));
            }
        });
    }


    public Car find(int id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("car_id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_CAR, param, new RowMapper<Car>() {
            public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Car(resultSet.getInt("car_id"),resultSet.getInt("mileage"),resultSet.getInt("owner_id"));
            }
        });
    }

    public void add(Car car) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("car_id", car.getCar_id());
        param.put("mileage", car.getMileage());
        param.put("owner_id", car.getOwner_id());

        this.namedParameterJdbcTemplate.update(SQL_ADD_CAR, param);
    }

    public void update(Car car) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("car_id", car.getCar_id());
        param.put("mileage", car.getMileage());
        param.put("owner_id", car.getOwner_id());

        this.namedParameterJdbcTemplate.update(SQL_UPDATE_CAR, param);
    }

    public void delete(int id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("car_id", id);

        this.namedParameterJdbcTemplate.update(SQL_DELETE_CAR, param);
    }

}
