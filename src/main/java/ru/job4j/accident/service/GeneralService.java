package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;

import java.util.Collection;

/**
 * Интерфейс GeneralService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface GeneralService {

    void saveOrUpdateAccident(Accident accident, String[] ruleIds);

    Accident getAccident(int id);

    Collection<Accident> findAllAccidents();

    Type getType(int id);

    Collection<Type> findAllTypes();

    Rule getRule(int id);

    Collection<Rule> findAllRules();

    void deleteAccident(int id);
}
