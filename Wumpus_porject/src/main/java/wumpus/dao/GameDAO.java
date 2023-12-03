package wumpus.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import wumpus.config.SessionFactoryMaker;
import wumpus.entity.Game;

public class GameDAO {
    public static final String COL_USERNAME = "username";
    SessionFactory factory = SessionFactoryMaker.getFactory();

    public void save(Game game) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(game);
            tx.commit();
        }
    }

    public Game findById(Integer id) {
        Game game;
        try (Session session = factory.openSession()) {
            game = session.find(Game.class, id);
        }
        return game;
    }

    public List<Game> findByUsername(String username) {
        List<Game> games;
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Game> criteria = builder.createQuery(Game.class);
            Root<Game> gameRoot = criteria.from(Game.class);
            Predicate eqRestriction = builder.and(
                builder.equal(gameRoot.get(COL_USERNAME), username)
            );
            criteria.select(gameRoot).where(eqRestriction).orderBy(builder.asc(gameRoot.get(COL_USERNAME)));
            Query<Game> gameQuery = session.createQuery(criteria);
            games = gameQuery.list();
        }
        return games;
    }

}
