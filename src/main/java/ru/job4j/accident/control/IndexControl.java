package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Класс IndexControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", List.of("First", "Second", "Third"));
        return "index";
    }
}
