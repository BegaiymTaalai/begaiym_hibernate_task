package peaksoft.dao;

import org.hibernate.*;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IS NOT EXISTS users").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(
                    "DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(User users) {
        SessionFactory sessionFactory = Util.sessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(users);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(id);
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            int cleanTheList = session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
