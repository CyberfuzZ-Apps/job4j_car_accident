package ru.job4j.accident.repository.memrepository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс AccidentMem - хранилище инцидентов.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class AccidentMem implements Store<Accident> {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger count = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(
                1,
                "Проезд на красный",
                "Автомобиль Porsche 911, регистрационный номер А000АА проехал перекресток "
                        + "на красный сигнал светофора.",
                "Москва, Ленина, 25", Type.of(1, "Две машины"),
                Set.of(Rule.of(1, "Статья 1"), Rule.of(2, "Статья 2"))));
        accidents.put(2, new Accident(
                2,
                "Не уступил пешеходу",
                "Автомобиль Audi Q7, регистрационный номер Б111ББ проехал пешеходный переход "
                        + "на большой скорости не уступив дорогу пешеходу.",
                "Москва, Герцена, 3", Type.of(2, "Машина и пешеход"),
                Set.of(Rule.of(2, "Статья 2"), Rule.of(3, "Статья 3"))));
    }

    @Override
    public void save(Accident accident) {
        accident.setId(count.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    @Override
    public void update(int id, Accident accident) {
        Accident rsl = get(id);
        if (rsl == null) {
            return;
        }
        accidents.replace(id, accident);
    }

    @Override
    public Accident get(int id) {
        return accidents.get(id);
    }

    @Override
    public void delete(int id) {
        accidents.remove(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
