package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import userModel.User;


public class Util {
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/myusertest?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private final String USERNAME ="root";
    private final String PASSWORD = "3339263";

private SessionFactory sessionFactory = null;





public SessionFactory getConnection(){

    try {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", DRIVER)
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password" , PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty( "hibernate.current_session_context_class", "thead")
                .addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    } catch (HibernateException e) {
        e.printStackTrace();
    }


    return sessionFactory;
}

    }
