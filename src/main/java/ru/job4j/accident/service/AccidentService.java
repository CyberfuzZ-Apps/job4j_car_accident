package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;
import ru.job4j.accident.repository.TypeMem;

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

    public void saveOrUpdateAccident(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(getType(typeId));
        if (accident.getId() == 0) {
            store.save(accident);
        } else {
            store.update(accident.getId(), accident);
        }
    }

    public Accident getAccident(int id) {
        return store.get(id);
    }

    public Collection<Accident> findAllAccidents() {
        return store.findAll();
    }

    public Type getType(int id) {
        return TypeMem.instOf().get(id);
    }

    public Collection<Type> findAllTypes() {
        return TypeMem.instOf().findAll();
    }
}
