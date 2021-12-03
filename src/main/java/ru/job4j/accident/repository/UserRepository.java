package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

/**
 * Класс UserRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
