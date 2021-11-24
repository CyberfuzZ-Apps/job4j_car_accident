package ru.job4j.di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Store
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
@Scope("prototype")
public class Store {

    private List<String> data = new ArrayList<>();

    public void add(String value) {
        data.add(value);
    }

    public List<String> getAll() {
        return data;
    }
}
