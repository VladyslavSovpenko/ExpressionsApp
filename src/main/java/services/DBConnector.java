package services;

import entity.Expression;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBConnector {
    private static final DBConnector db = new DBConnector();

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return db;
    }

    private SessionFactory getSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Expression.class)
                .buildSessionFactory();
    }

    public void saveToDB(Expression expression) {
        try (SessionFactory factory = getSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(expression);
            session.getTransaction().commit();
        }
    }
    public void getFromDB(){
        try (SessionFactory factory = getSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<Expression> expressions = session.createQuery("from ExpressionWithKey where key")
                            .getResultList();
            session.getTransaction().commit();
        }
    }
}
