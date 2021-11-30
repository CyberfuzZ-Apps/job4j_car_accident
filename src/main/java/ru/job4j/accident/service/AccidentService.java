package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс AccidentService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class AccidentService {

    private final Store<Accident> accidentStore;
    private final Store<Type> typeStore;
    private final Store<Rule> ruleStore;

    public AccidentService(Store<Accident> accidentStore, Store<Type> typeStore, Store<Rule> ruleStore) {
        this.accidentStore = accidentStore;
        this.typeStore = typeStore;
        this.ruleStore = ruleStore;
    }

    public void saveOrUpdateAccident(Accident accident, String[] ruleIds) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ruleIds) {
            rules.add(getRule(Integer.parseInt(id)));
        }
        accident.setRules(rules);
        int typeId = accident.getType().getId();
        accident.setType(getType(typeId));
        if (accident.getId() == 0) {
            accidentStore.save(accident);
        } else {
            accidentStore.update(accident.getId(), accident);
        }
    }

    public Accident getAccident(int id) {
        return accidentStore.get(id);
    }

    public Collection<Accident> findAllAccidents() {
        return accidentStore.findAll();
    }

    public Type getType(int id) {
        return typeStore.get(id);
    }

    public Collection<Type> findAllTypes() {
        return typeStore.findAll();
    }

    public Rule getRule(int id) {
        return ruleStore.get(id);
    }

    public Collection<Rule> findAllRules() {
        return ruleStore.findAll();
    }
}
