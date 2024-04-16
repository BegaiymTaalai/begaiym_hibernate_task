package peaksoft.util;

import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import peaksoft.model.User;

public class Util {
    public static SessionFactory sessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return sessionFactory;
    }
    // реализуйте настройку соеденения с БД
}
