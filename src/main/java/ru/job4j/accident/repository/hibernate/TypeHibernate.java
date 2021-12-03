package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
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
@Repository
public class TypeHibernate implements Store<Type> {

    private final SessionFactory sf;

    public TypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void save(Type type) {
        HbmUtils.transaction(sf, session -> session.save(type));
    }

    @Override
    public void update(Type type) {
        HbmUtils.transaction(sf, session -> {
            session.update(type);
            return type;
        });
    }

    @Override
    public Type get(int id) {
        return HbmUtils.transaction(sf, session -> session.get(Type.class, id));
    }

    @Override
    public void delete(int id) {
        HbmUtils.transaction(sf, session -> {
            Type type = session.get(Type.class, id);
            if (type != null) {
                session.delete(type);
            }
            return null;
        });
    }

    @Override
    public Collection<Type> findAll() {
        return HbmUtils.transaction(sf, session -> session.createQuery("from Type").list());
    }

}
