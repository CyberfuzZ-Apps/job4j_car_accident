package ru.job4j.di;

/**
 * Класс Main
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        context.reg(Store.class);
        context.reg(ConsoleInput.class);
        context.reg(StartUI.class);

        StartUI ui = context.get(StartUI.class);
        ConsoleInput consoleInput = context.get(ConsoleInput.class);

        ui.add(consoleInput.getInput());
        ui.add(consoleInput.getInput());
        ui.print();
    }
}
