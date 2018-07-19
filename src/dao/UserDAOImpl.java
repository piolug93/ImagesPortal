package dao;

import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import util.ConnectionProvider;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String READ_USER_BY_LOGIN = "SELECT * FROM users WHERE login=:login;";
    private static final String READ_USER = "SELECT * FROM users WHERE id=:id;";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public User create(User newObject) {
        return null;
    }

    @Override
    public User read(Long primaryKey) {
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("id", primaryKey);
        resultUser = template.queryForObject(READ_USER, paramSource, BeanPropertyRowMapper.newInstance(User.class));
        return resultUser;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("login", login);
        resultUser = template.queryForObject(READ_USER_BY_LOGIN, paramSource, BeanPropertyRowMapper.newInstance(User.class));
        return resultUser;
    }
}
