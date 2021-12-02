package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

/**
 * Класс AccidentRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
