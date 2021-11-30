package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Type;

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

    private TypeMem() {
        typeMap.put(1, Type.of(1, "Две машины"));
        typeMap.put(2, Type.of(2, "Машина и пешеход"));
        typeMap.put(3, Type.of(3, "Машина и велосипед"));
    }

    private static final class Lazy {
        private static final TypeMem INST = new TypeMem();
    }

    public static TypeMem instOf() {
        return Lazy.INST;
    }

    @Override
    public void save(Type type) {
        typeMap.put(type.getId(), type);
    }

    @Override
    public boolean update(int id, Type type) {
        return typeMap.replace(id, type) != null;
    }

    @Override
    public Type get(int id) {
        return typeMap.get(id);
    }

    @Override
    public Collection<Type> findAll() {
        return typeMap.values();
    }

}
