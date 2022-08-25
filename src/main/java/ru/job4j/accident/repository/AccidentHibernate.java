package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {

    private final SessionFactory sessionFactory;

    public AccidentHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Accident> getAccidents() {
        return tx(session -> session.createQuery("from Accident").getResultList());
    }

    public List<AccidentType> getAccidentsType() {
        return tx(session -> session.createQuery("from AccidentType").getResultList());
    }

    public void add(Accident accident) {
        tx(session -> session.save(accident));
    }

    public void update(Accident accident) {
        tx(session -> {
            session.update(accident);
            return true;
        });
    }

    public Accident findAccidentById(int id) {
        return (Accident) tx(session -> session.createQuery(
                "from Accident a join fetch a.type at join fetch  a.rules r WHERE a.id = :id")
                .setParameter("id", id)
                .getSingleResult());
    }

    public AccidentType findAccidentTypeById(int id) {
        return tx(session -> session.get(AccidentType.class, id));
    }

    public Rule findRuleById(int id) {
        return tx(session -> session.get(Rule.class, id));
    }

    public List<Rule> getRules() {
        return tx(session -> session.createQuery("from Rule").getResultList());
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
