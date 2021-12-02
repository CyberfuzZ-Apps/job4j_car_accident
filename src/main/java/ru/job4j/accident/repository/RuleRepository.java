package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

/**
 * Класс RuleRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
