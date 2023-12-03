package wumpus.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import wumpus.entity.Game;
import wumpus.entity.Player;

public class SessionFactoryMaker {

    private SessionFactoryMaker() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static SessionFactory factory;

    private static void configureFactory() {
        try {
            factory = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Game.class)
                .configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static org.hibernate.SessionFactory getFactory() {
        if (factory == null) {
            configureFactory();
        }

        return factory;
    }

}
