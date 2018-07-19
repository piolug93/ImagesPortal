package dao;

import model.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String READ_USER_BY_LOGIN = "SELECT * FROM user WHERE login=:login;";
    private static final String READ_USER_BY_ID = "SELECT * FROM user WHERE id=:id;";

    @Override
    public User create(User newObject) {
        return null;
    }

    @Override
    public User read(Long primaryKey) {
        return null;
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
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
