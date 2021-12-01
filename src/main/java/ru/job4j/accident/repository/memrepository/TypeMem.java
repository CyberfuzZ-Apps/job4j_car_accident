package ru.job4j.accident.repository.memrepository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс TypeMem
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class TypeMem implements Store<Type> {

    private final Map<Integer, Type> typeMap = new HashMap<>();

    public TypeMem() {
        typeMap.put(1, Type.of(1, "Две машины"));
        typeMap.put(2, Type.of(2, "Машина и пешеход"));
        typeMap.put(3, Type.of(3, "Машина и велосипед"));
    }

    @Override
    public void save(Type type) {
        typeMap.put(type.getId(), type);
    }

    @Override
    public void update(int id, Type type) {
        typeMap.replace(id, type);
    }

    @Override
    public Type get(int id) {
        return typeMap.get(id);
    }

    @Override
    public void delete(int id) {
        typeMap.remove(id);
    }

    @Override
    public Collection<Type> findAll() {
        return typeMap.values();
    }
}
