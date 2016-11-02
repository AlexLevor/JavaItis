package ru.itis.dao;

import ru.itis.models.Owner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class OwnersDaoImpl implements OwnersDao {

    private Connection connection;
    //language=SQL
    private final String SQL_ALL_OWNERS = "SELECT * FROM owners";

    private final String SQL_FIND_OWNER_ID = "SELECT * FROM owners WHERE owner_id = ?";

    private final String SQL_FIND_OWNER_LOGIN = "SELECT * FROM owners WHERE login = ?";

    private final String SQL_UPDATE_OWNER = "UPDATE owners SET city = ? , age = ? , name = ? WHERE owner_id = ?";

    private final String SQL_ADD_OWNER = "INSERT into owners (city, age, name, login) values(?, ?, ?, ?, ?);";

    private final String SQL_DELETE_OWNER = "DELETE FROM owners WHERE owner_id = ?";

    //language=SQL
    private final String SQL_SET_TOKEN = "UPDATE owners SET token = ? WHERE login = ? AND password = ?";

    //language=SQL
    private final String SQL_GET_TOKEN = "SELECT token FROM owners WHERE login = ?";



    public OwnersDaoImpl(DataSource connection) {
        try {
            this.connection = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Owner> getAll() {

        try {
            List<Owner> owners = new ArrayList<Owner>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_ALL_OWNERS);
            while (resultSet.next()) {
                Owner owner = new Owner(resultSet.getInt("owner_id"),resultSet.getString("city"),
                        resultSet.getInt("age"), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("password"));
                owners.add(owner);
            }
            return owners;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Owner findId(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OWNER_ID);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            return new Owner(result.getString("city"), result.getInt("age"), result.getString("name"), result.getString("login"));

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    public Owner findLogin(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OWNER_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            return new Owner(result.getString("city"), result.getInt("age"), result.getString("name"), result.getString("login"));

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
            preparedStatement.setString(4, owner.getLogin());
            preparedStatement.setString(5, owner.getPassword());


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

    public void setToken(String login, String password, String token) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_TOKEN);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getToken(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TOKEN);
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getString("token");
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }
}
