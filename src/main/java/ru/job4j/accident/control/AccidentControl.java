package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

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
        model.addAttribute("types", service.findAllTypes());
        model.addAttribute("rules", service.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.saveOrUpdateAccident(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("rules", service.findAllRules());
        model.addAttribute("types", service.findAllTypes());
        model.addAttribute("accident", service.getAccident(id));
        return "accident/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        service.deleteAccident(id);
        return "redirect:/";
    }

}
