package ru.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.models.Owner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class OwnersDaoImpl implements OwnersDao {

    //language=SQL
    private final String SQL_ALL_OWNERS = "SELECT * FROM owners";

    private final String SQL_FIND_OWNER_ID = "SELECT * FROM owners WHERE owner_id = :owner_id";

    private final String SQL_FIND_OWNER_LOGIN = "SELECT * FROM owners WHERE login = :login";

    private final String SQL_ADD_OWNER = "INSERT into owners (city, age, name, login, password) values(:city,:age,:name,:login,:password)";

    private final String SQL_UPDATE_OWNER = "UPDATE owners SET city = :city , age = :age , name = :name WHERE owner_id = :owner_id";


    private final String SQL_DELETE_OWNER = "DELETE FROM owners WHERE owner_id = :owner_id";

    //language=SQL
    private final String SQL_SET_TOKEN = "UPDATE owners SET token = :token WHERE login = :login AND password = :password";

    //language=SQL
    private final String SQL_GET_TOKEN = "SELECT token FROM owners WHERE login = ?";

    private final String SQL_ALL_OWNER_OF_AGE = "SELECT * FROM owners WHERE age = :age";


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OwnersDaoImpl(DataSource connection) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(connection);
    }

    public List<Owner> getAll() {
        return this.namedParameterJdbcTemplate.query(SQL_ADD_OWNER, new RowMapper<Owner>() {
            public Owner mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Owner(resultSet.getInt("owner_id"),resultSet.getString("city"),
                        resultSet.getInt("age"), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("password"));
            }
        });

    }

    public Owner findId(int id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("owner_id", id);


        return this.namedParameterJdbcTemplate.queryForObject(SQL_FIND_OWNER_ID, param,  new RowMapper<Owner>() {
            public Owner mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Owner(resultSet.getString("city"),resultSet.getInt("age"),resultSet.getString("name"), resultSet.getString("login"));
            }
        });
    }
    public Owner findLogin(String login) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("login", login);

        return this.namedParameterJdbcTemplate.queryForObject(SQL_FIND_OWNER_LOGIN, param, new RowMapper<Owner>() {
            public Owner mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Owner(resultSet.getInt("id"), resultSet.getString("city"), resultSet.getInt("age"), resultSet.getString("name"), resultSet.getString("login"));
            }
        });
    }
    public void add(Owner owner) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("city", owner.getCity());
        param.put("age", owner.getAge());
        param.put("name", owner.getName());
        param.put("login", owner.getLogin());
        param.put("password", owner.getPassword());

        this.namedParameterJdbcTemplate.update(SQL_ADD_OWNER, param);
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
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("owner_id", id);
        this.namedParameterJdbcTemplate.update(SQL_DELETE_OWNER, param);
    }

    public void setToken(String login, String password, String token) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("login", login);
        param.put("password", password);
        param.put("token", token);
        this.namedParameterJdbcTemplate.update(SQL_SET_TOKEN, param);

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
    public List<Owner> getAllOwnersOfAge(int age) {
        try {
            List<Owner> ownerList = new ArrayList<Owner>();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_OWNER_OF_AGE);
            preparedStatement.setInt(1, age);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Owner owner = new Owner(resultSet.getString("city"), resultSet.getInt("age"),resultSet.getString("name"));
                ownerList.add(owner);
            }
            return ownerList;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
