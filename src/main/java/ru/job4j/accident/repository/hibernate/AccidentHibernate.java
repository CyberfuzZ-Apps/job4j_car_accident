package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.Set;

/**
 * Класс AccidentHibernate - работа с хранилищем инцидентов с помощью Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
/* @Repository */
public class AccidentHibernate implements Store<Accident> {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void save(Accident accident) {
        HbmUtils.transaction(sf, session -> {
            Set<Rule> rules = accident.getRules();
            for (Rule rule : rules) {
                session.refresh(rule);
            }
            session.persist(accident);
            return accident;
        });
    }

    @Override
    public void update(Accident accident) {
        HbmUtils.transaction(sf, session -> {
            session.update(accident);
            return accident;
        });
    }

    @Override
    public Accident get(int id) {
        return (Accident) HbmUtils.transaction(sf, session -> session.createQuery(
                        "select distinct a from Accident a join fetch a.rules where a.id = :id")
                .setParameter("id", id).uniqueResult());
    }

    @Override
    public void delete(int id) {
        HbmUtils.transaction(sf, session -> {
            Accident accident = (Accident) session.createQuery(
                            "select distinct a from Accident a join fetch a.rules where a.id = :id")
                    .setParameter("id", id).uniqueResult();
            if (accident != null) {
                session.delete(accident);
            }
            return null;
        });
    }

    @Override
    public Collection<Accident> findAll() {
        return HbmUtils.transaction(sf, session -> session.createQuery(
                "select distinct a from Accident a join fetch a.rules").list());
    }

}
