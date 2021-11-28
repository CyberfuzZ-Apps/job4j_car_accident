package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

/**
 * Класс AccidentService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class AccidentService {

    private final Store<Accident> store;

    public AccidentService(Store<Accident> store) {
        this.store = store;
    }

    public void addAccident(Accident accident) {
        store.add(accident);
    }

    public Accident getAccident(int id) {
        return store.get(id);
    }

    public Collection<Accident> findAllAccidents() {
        return store.findAll();
    }
}
