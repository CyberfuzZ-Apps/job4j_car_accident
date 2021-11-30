package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс RuleMem
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class RuleMem implements Store<Rule> {

    private final Map<Integer, Rule> ruleMap = new HashMap<>();

    public RuleMem() {
        ruleMap.put(1, Rule.of(1, "Статья 1"));
        ruleMap.put(2, Rule.of(2, "Статья 2"));
        ruleMap.put(3, Rule.of(3, "Статья 3"));
    }

    @Override
    public void save(Rule rule) {
        ruleMap.put(rule.getId(), rule);
    }

    @Override
    public boolean update(int id, Rule rule) {
        return ruleMap.replace(id, rule) != null;
    }

    @Override
    public Rule get(int id) {
        return ruleMap.get(id);
    }

    @Override
    public Collection<Rule> findAll() {
        return ruleMap.values();
    }
}
