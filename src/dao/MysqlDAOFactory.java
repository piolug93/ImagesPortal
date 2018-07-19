package dao;

public class MysqlDAOFactory extends DAOFactory{
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
