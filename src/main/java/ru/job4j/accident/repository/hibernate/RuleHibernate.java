package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

/**
 * Класс RuleHibernate - работа с хранилищем статей с помощью Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class RuleHibernate implements Store<Rule> {

    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void save(Rule rule) {
        HbmUtils.transaction(sf, session -> session.save(rule));
    }

    @Override
    public void update(Rule rule) {
        HbmUtils.transaction(sf, session -> {
            session.update(rule);
            return rule;
        });
    }

    @Override
    public Rule get(int id) {
        return HbmUtils.transaction(sf, session -> session.get(Rule.class, id));
    }

    @Override
    public void delete(int id) {
        HbmUtils.transaction(sf, session -> {
            Rule rule = session.get(Rule.class, id);
            if (rule != null) {
                session.delete(rule);
            }
            return null;
        });
    }

    @Override
    public Collection<Rule> findAll() {
        return HbmUtils.transaction(sf, session -> session.createQuery("from Rule").list());
    }

}
