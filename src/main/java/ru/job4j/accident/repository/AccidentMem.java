package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс AccidentMem - хранилище инцидентов.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class AccidentMem implements Store {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(
                1,
                "Проезд на красный",
                "Автомобиль Porsche 911, регистрационный номер А000АА проехал перекресток "
                        + "на красный сигнал светофора.",
                "Москва, Ленина, 25"));
        accidents.put(2, new Accident(
                2,
                "Не уступил пешеходу",
                "Автомобиль Audi Q7, регистрационный номер Б111ББ проехал пешеходный переход "
                        + "на большой скорости не уступив дорогу пешеходу.",
                "Москва, Герцена, 3"));
    }

    @Override
    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident get(int id) {
        return accidents.get(id);
    }

    @Override
    public Map<Integer, Accident> findAll() {
        return accidents;
    }
}
