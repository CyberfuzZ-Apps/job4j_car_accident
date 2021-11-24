package ru.job4j.di;

import java.util.Scanner;

/**
 * Класс ConsoleInput
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }
}
