package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

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
    public String create(Model model) {
        model.addAttribute("types", getAccidentTypes());
        return "accident/create";
    }

    private List<AccidentType> getAccidentTypes() {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        return types;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        List<AccidentType> typeList = getAccidentTypes();
        for (AccidentType type : typeList) {
            if (type.getId() == accident.getType().getId()) {
                accident.setType(type);
                break;
            }
        }
        service.saveOrUpdateAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", getAccidentTypes());
        model.addAttribute("accident", service.getAccident(id));
        return "accident/edit";
    }

}
