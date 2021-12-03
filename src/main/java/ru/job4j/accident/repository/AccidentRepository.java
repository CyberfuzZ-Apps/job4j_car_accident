package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

/**
 * Класс AccidentRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query(value = "select distinct a from Accident a join fetch a.rules")
    Collection<Accident> findAllAccidents();

    @Query(value = "select distinct a from Accident a join fetch a.rules where a.id = :id")
    Accident findAccidentById(@Param("id") int id);

}
