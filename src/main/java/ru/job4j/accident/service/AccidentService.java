package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс AccidentService - сервис для обработки инцидентов в хранилище.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class AccidentService implements GeneralService {

    private final Store<Accident> accidentStore;
    private final Store<Type> typeStore;
    private final Store<Rule> ruleStore;

    public AccidentService(
            @Qualifier("accidentHibernate") Store<Accident> accidentStore,
            @Qualifier("typeHibernate") Store<Type> typeStore,
            @Qualifier("ruleHibernate") Store<Rule> ruleStore) {
        this.accidentStore = accidentStore;
        this.typeStore = typeStore;
        this.ruleStore = ruleStore;
    }

    @Override
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
            accidentStore.update(accident);
        }
    }

    @Override
    public Accident getAccident(int id) {
        return accidentStore.get(id);
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidentStore.findAll();
    }

    @Override
    public Type getType(int id) {
        return typeStore.get(id);
    }

    @Override
    public Collection<Type> findAllTypes() {
        return typeStore.findAll();
    }

    @Override
    public Rule getRule(int id) {
        return ruleStore.get(id);
    }

    @Override
    public Collection<Rule> findAllRules() {
        return ruleStore.findAll();
    }

    @Override
    public void deleteAccident(int id) {
        accidentStore.delete(id);
    }
}
