package ru.itis.dao;

import ru.itis.models.Owner;

import java.sql.*;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class OwnersDaoImpl implements OwnersDao {

    private Connection connection;

    // language=SQL
    private final String SQL_FIND_OWNER = "SELECT * FROM owners WHERE owner_id = ?;";

    // language=SQL
    private final String SQL_UPDATE_OWNER = "UPDATE owners SET city = ? , age = ? , name = ? WHERE owner_id = ?";

    // language=SQL
    private final String SQL_ADD_OWNER = "INSERT into owners (city, age, name) values(?, ?, ?);";

    public OwnersDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Owner> getAll() {
        return null;
    }

    public Owner find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OWNER);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            return new Owner(result.getInt("owner_id"), result.getString("city"), result.getInt("age"), result.getString("name"));

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(Owner owner) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_OWNER);
            preparedStatement.setString(1, owner.getCity());
            preparedStatement.setInt(2, owner.getAge());
            preparedStatement.setString(3, owner.getName());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update(Owner owner) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_OWNER);
            preparedStatement.setString(1, owner.getCity());
            preparedStatement.setInt(2, owner.getAge());
            preparedStatement.setString(3, owner.getName());
            preparedStatement.setInt(4, owner.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(int id) {

    }
}