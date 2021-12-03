package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.GeneralService;

import java.util.Collection;

/**
 * Класс IndexControl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
public class IndexControl {

    private final GeneralService service;

    public IndexControl(
            @Qualifier("accidentSpringDataService")
                    GeneralService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> accidents = service.findAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }

}
