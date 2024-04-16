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
            session.createQuery("DROP User", User.class).getResultList();
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
            User users = new User();
            users.setName(name);
            users.setLastName(lastName);
            users.setAge((age));
            session.persist(users);
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
            session.delete(id);
            session.getTransaction().commit();
        } catch (Exception e) {
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
            users = session.createQuery("FROM User", User.class).getResultList();
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
            session.clear();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
