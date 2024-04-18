package peaksoft.dao;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.type.TrueFalseConverter;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.sessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery("DROP User", User.class).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.sessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            SessionFactory sessionFactory = Util.sessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = Util.sessionFactory().openSession()) {
            users = session.createNativeQuery("SELECT * FROM users", User.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.sessionFactory().openSession();
            session.beginTransaction();
            session.createNativeQuery(("DELETE FROM User"), User.class).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}

