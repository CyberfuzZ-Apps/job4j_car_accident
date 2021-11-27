package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.Map;

/**
 * Класс Store
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store {

    void add(Accident accident);

    Accident get(int id);

    Map<Integer, Accident> findAll();

}
