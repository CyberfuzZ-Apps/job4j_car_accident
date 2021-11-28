package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;

/**
 * Класс Store
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store<T> {

    void add(T t);

    T get(int id);

    Collection<T> findAll();

}
