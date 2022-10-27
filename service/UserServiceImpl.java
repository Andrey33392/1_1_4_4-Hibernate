package service;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import userModel.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoHibernateImpl();

    @Override
    public void creatUsersTable() {

        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        userDao.dropUsersTable();
        System.out.println("Delete table");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDao.saveUser(name, lastName, age);
        System.out.println("User name -" + name + " added to database");
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("User with id " + id + " delete");
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();

        users.forEach(System.out::println);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Clean Table");

    }
}
