package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Класс SpringDI
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class SpringDI {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Store.class);
        context.register(ConsoleInput.class);
        context.register(StartUI.class);
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ConsoleInput consoleInput = context.getBean(ConsoleInput.class);
        ui.add(consoleInput.getInput());
        ui.add(consoleInput.getInput());
        ui.print();
    }
}
