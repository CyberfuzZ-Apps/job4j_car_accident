package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
@Repository
public class AccidentHibernate implements Store<Accident> {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    @Transactional
    public void save(Accident accident) {
        Session session = currentSession();
        Set<Rule> rules = accident.getRules();
        for (Rule rule : rules) {
            session.refresh(rule);
        }
        session.persist(accident);
    }

    @Override
    @Transactional
    public void update(Accident accident) {
        currentSession().update(accident);
    }

    @Override
    @Transactional(readOnly = true)
    public Accident get(int id) {
        return currentSession().get(Accident.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        currentSession().delete(get(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Accident> findAll() {
        return currentSession().createQuery("from Accident").list();
    }

    public Session currentSession() {
        return sf.getCurrentSession();
    }

}
