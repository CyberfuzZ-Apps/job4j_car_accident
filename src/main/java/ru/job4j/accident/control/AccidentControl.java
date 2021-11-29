package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

/**
 * Класс AccidentControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("id") int id, @ModelAttribute Accident accident) {
        if (id != 0) {
            service.updateAccident(id, accident);
        } else {
            service.addAccident(accident);
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.getAccident(id));
        return "accident/edit";
    }

}
