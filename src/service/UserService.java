package service;

import dao.DAOFactory;
import dao.UserDAO;
import model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

public class UserService {
    public User getUserById(long id) {
        UserDAO userDAO = getUserDAO();
        User user = userDAO.read(id);
        return user;
    }

    public User getUserByLogin(String login) {
        UserDAO userDAO = getUserDAO();
        User user = userDAO.getUserByLogin(login);
        return user;
    }

    private UserDAO getUserDAO() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        return userDAO;
    }

    public void createUser(String login, String email, String password) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        String md5Password = encryptPassword(password);
        user.setPassword(md5Password);
        user.setRegistredDate(new Timestamp(new Date().getTime()));
        user.setActivated(true);
        user.setBlocked(false);
        user.setLastIp(null);
        UserDAO userDAO = getUserDAO();
        userDAO.create(user);
    }

    private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }
}
