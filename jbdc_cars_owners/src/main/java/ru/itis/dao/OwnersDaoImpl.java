package ru.itis.dao;

import ru.itis.models.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class OwnersDaoImpl implements OwnersDao {

    private Connection connection;

    private final String SQL_ALL_OWNERS = "SELECT * FROM owners";

    private final String SQL_FIND_OWNER = "SELECT * FROM owners WHERE owner_id = ?;";

    private final String SQL_UPDATE_OWNER = "UPDATE owners SET city = ? , age = ? , name = ? WHERE owner_id = ?";

    private final String SQL_ADD_OWNER = "INSERT into owners (city, age, name) values(?, ?, ?);";

    private final String SQL_DELETE_OWNER = "DELETE FROM owners WHERE owner_id = ?";

    public OwnersDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Owner> getAll() {

        try {
            List<Owner> owners = new ArrayList<Owner>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_ALL_OWNERS);
            while (resultSet.next()) {
                Owner owner = new Owner(resultSet.getString("city"),
                        resultSet.getInt("age"), resultSet.getString("name"));
                owners.add(owner);
            }
            return owners;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Owner find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OWNER);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            return new Owner(result.getString("city"), result.getInt("age"), result.getString("name"));

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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_OWNER);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
