package ru.job4j.accident.repository;

import java.util.Collection;

/**
 * Интерфейс Store - определяет методы для работы с хранилищем
 * (CRUD - Create, Read, Update, Delete).
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store<T> {

    void save(T t);

    void update(T t);

    T get(int id);

    void delete(int id);

    Collection<T> findAll();

}
