package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

/**
 * Класс RuleHibernate - работа с хранилищем статей с помощью Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
/* @Repository */
public class RuleHibernate implements Store<Rule> {

    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    @Transactional
    public void save(Rule rule) {
        currentSession().save(rule);
    }

    @Override
    @Transactional
    public void update(Rule rule) {
        currentSession().update(rule);
    }

    @Override
    @Transactional(readOnly = true)
    public Rule get(int id) {
        return currentSession().get(Rule.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        currentSession().delete(get(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Rule> findAll() {
        return currentSession().createQuery("from Rule").list();
    }

    public Session currentSession() {
        return sf.getCurrentSession();
    }
}
