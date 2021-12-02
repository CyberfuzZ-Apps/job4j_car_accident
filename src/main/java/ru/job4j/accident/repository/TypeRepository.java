package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Type;

/**
 * Класс TypeRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface TypeRepository extends CrudRepository<Type, Integer> {
}
