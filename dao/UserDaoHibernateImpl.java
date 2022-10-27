package dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import userModel.User;
import util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    private final SessionFactory sessionFactory = util.getConnection();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS myusertest.user" +
                    " (id mediumint not null auto_increment, name VARCHAR(65), " +
                    "lastname VARCHAR(65), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }


    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (HibernateException |IllegalArgumentException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }


    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = new ArrayList<>();
        try {
            userList = session.createQuery("SELECT a FROM User a", User.class).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE myusertest.user;").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
