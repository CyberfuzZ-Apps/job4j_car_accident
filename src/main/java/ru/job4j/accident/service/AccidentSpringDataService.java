package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import java.util.*;

/**
 * Класс AccidentSpringDataService - сервис для обработки инцидентов
 * в хранилище с помощью Spring Data JPA.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
/* @Service */
@Transactional
public class AccidentSpringDataService implements GeneralService {

    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final TypeRepository typeRepository;

    public AccidentSpringDataService(
            AccidentRepository accidentRepository,
            RuleRepository ruleRepository,
            TypeRepository typeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
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
        accidentRepository.save(accident);
    }

    @Override
    public Accident getAccident(int id) {
        return accidentRepository.findAccidentById(id);
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidentRepository.findAllAccidents();
    }

    @Override
    public Type getType(int id) {
        Optional<Type> optionalType = typeRepository.findById(id);
        return optionalType.orElse(null);
    }

    @Override
    public Collection<Type> findAllTypes() {
        Iterable<Type> types = typeRepository.findAll();
        return (Collection<Type>) types;
    }

    @Override
    public Rule getRule(int id) {
        Optional<Rule> optionalRule = ruleRepository.findById(id);
        return optionalRule.orElse(null);
    }

    @Override
    public Collection<Rule> findAllRules() {
        Iterable<Rule> rules = ruleRepository.findAll();
        return (Collection<Rule>) rules;
    }

    @Override
    public void deleteAccident(int id) {
        Accident accident = getAccident(id);
        if (accident != null) {
            accidentRepository.delete(accident);
        }
    }

}
