package services;

import entity.Expression;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private List expressions = new ArrayList<>();
    private static final DBConnector db = new DBConnector();

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return db;
    }

    private SessionFactory getSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Expression.class).buildSessionFactory();
    }

    public void saveToDB(Expression expression) {
        try (SessionFactory factory = getSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(expression);
            session.getTransaction().commit();
        }
    }

    public List getFromDB(String key) {
        expressions.clear();
        try (SessionFactory factory = getSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            expressions = session.createQuery(String.format("from Expression where key IN (%s)", key)).getResultList();
            session.getTransaction().commit();
        }
        return expressions;
    }
}
