package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

/**
 * Класс TypeHibernate - работа с хранилищем типов происшествий с
 * помощью Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
/* @Repository */
public class TypeHibernate implements Store<Type> {

    private final SessionFactory sf;

    public TypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    @Transactional
    public void save(Type type) {
        currentSession().save(type);
    }

    @Override
    @Transactional
    public void update(Type type) {
        currentSession().update(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Type get(int id) {
        return currentSession().get(Type.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        currentSession().delete(get(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Type> findAll() {
        return currentSession().createQuery("from Type").list();
    }

    public Session currentSession() {
        return sf.getCurrentSession();
    }
}
