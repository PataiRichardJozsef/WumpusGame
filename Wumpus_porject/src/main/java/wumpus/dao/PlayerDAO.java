package wumpus.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import wumpus.config.SessionFactoryMaker;
import wumpus.entity.Player;

public class PlayerDAO {

    SessionFactory factory = SessionFactoryMaker.getFactory();

    public void save(Player player) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(player);
            tx.commit();
        }
    }

    public Player findByUsername(String username) {
        Player player = null;
        try (Session session = factory.openSession()) {
            player = session.find(Player.class, username);
        }
        return player;
    }

    public void update(Player player) {
        var existingPlayer = findByUsername(player.getUsername());
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            if (existingPlayer != null) {
                session.delete(existingPlayer);
            }
            save(player);
        }
    }

    public List<Player> findTop(Integer numberOfPlayers) {
        List<Player> topPlayers;
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Player> criteria = builder.createQuery(Player.class);
            Root<Player> playerRoot = criteria.from(Player.class);
            criteria.select(playerRoot).orderBy(builder.desc(playerRoot.get("numberOfWins")));
            Query<Player> playerQuery = session.createQuery(criteria);
            playerQuery.setMaxResults(numberOfPlayers);
            topPlayers = playerQuery.list();
        }

        return topPlayers;
    }

}
