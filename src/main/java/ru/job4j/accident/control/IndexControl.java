package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.Map;

/**
 * Класс IndexControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class IndexControl {

    private final AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<Integer, Accident> accidentMap = service.findAllAccident();
        model.addAttribute("accidentMap", accidentMap);
        return "index";
    }
}
