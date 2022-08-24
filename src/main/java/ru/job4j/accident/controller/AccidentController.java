package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import java.util.*;

@Controller
public class AccidentController {

    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAccidentsType());
        model.addAttribute("rules", accidentService.getRules());
        return "create";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id) {
        Accident accident = accidentService.findAccidentById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentService.getAccidentsType());
        Map<Rule, Boolean> rules = new TreeMap<>(Comparator.comparingInt(Rule::getId));
        for (Rule rule : accidentService.getRules()) {
            rules.put(rule, false);
        }
        for (Rule rule : accident.getRules()) {
            rules.put(rule, true);
        }
        model.addAttribute("rules", rules);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam String[] rIds) {
        accident.setType(accidentService.findAccidentTypeById(accident.getType().getId()));
        Set<Rule> rules = new HashSet<>();
        for (String stringId : rIds) {
            rules.add(accidentService.findRuleById(Integer.parseInt(stringId)));
        }
        accident.setRules(rules);
        accidentService.save(accident);
        return "redirect:/index";
    }

}
