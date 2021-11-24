package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс StartUI
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
public class StartUI {

    @Autowired /* Field injection is not recommended. Create constructor */
    private Store store;
    @Autowired /* Field injection is not recommended. Create constructor */
    private ConsoleInput consoleInput;

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
