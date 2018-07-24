package dao;

import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String READ_USER_BY_LOGIN = "SELECT * FROM users WHERE login=:login;";
    private static final String READ_USER_BY_EMAIL = "SELECT * FROM users WHERE email=:email;";
    private static final String READ_USER = "SELECT * FROM users WHERE id=:id;";
    private static final String CREATE_USER = "INSERT INTO `users` (`login`, `password`, `email`, `registredDate`, `activated`, `blocked`, `lastIp`) VALUES (:login , :password, :email, :registredDate, :activated, :blocked, :lastIp);";
    private static final String SET_ROLE = "INSERT INTO user_role(idUser) VALUES (:id);";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public User create(User user) {
        User resultUser = new User(user);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        int update = template.update(CREATE_USER, paramSource, holder);
        if(update > 0) {
            resultUser.setId(holder.getKey().longValue());
            setRole(resultUser);
        }
        return resultUser;
    }

    private void setRole(User user) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        template.update(SET_ROLE, paramSource);
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

    public User getUserByEmail(String email) {
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("email", email);
        resultUser = template.queryForObject(READ_USER_BY_EMAIL, paramSource, BeanPropertyRowMapper.newInstance(User.class));
        return resultUser;
    }
}
