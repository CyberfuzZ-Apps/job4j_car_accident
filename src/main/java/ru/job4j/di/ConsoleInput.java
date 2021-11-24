package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Класс ConsoleInput
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }
}
