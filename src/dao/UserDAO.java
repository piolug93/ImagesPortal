package dao;

import model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {

    List<User> getAll();
    User getUserByLogin(String login);
    User getUserByEmail(String email);
}
