package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.Store;

import java.util.Map;

/**
 * Класс AccidentService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class AccidentService {

    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public void addAccident(Accident accident) {
        store.add(accident);
    }

    public Accident getAccident(int id) {
        return store.get(id);
    }

    public Map<Integer, Accident> findAllAccident() {
        return store.findAll();
    }
}
