package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import java.util.*;

@Controller
public class AccidentController {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public AccidentController(AccidentService accidentService, AccidentTypeService accidentTypeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypeService.getAccidentsType());
        model.addAttribute("rules", ruleService.getRules());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Accident accident = accidentService.findAccidentById(id).get();
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentTypeService.getAccidentsType());
        Map<Rule, Boolean> rules = new TreeMap<>(Comparator.comparingInt(Rule::getId));
        for (Rule rule : ruleService.getRules()) {
            rules.put(rule, false);
        }
        for (Rule rule : accident.getRules()) {
            rules.put(rule, true);
        }
        model.addAttribute("rules", rules);
        return "edit";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam String[] rIds) {
        accident.setType(accidentTypeService.findAccidentTypeById(accident.getType().getId()).get());
        Set<Rule> rules = new HashSet<>();
        for (String stringId : rIds) {
            rules.add(ruleService.findRuleById(Integer.parseInt(stringId)).get());
        }
        accident.setRules(rules);
        accidentService.add(accident);
        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                       @RequestParam String[] rIds) {
        accident.setType(accidentTypeService.findAccidentTypeById(accident.getType().getId()).get());
        Set<Rule> rules = new HashSet<>();
        for (String stringId : rIds) {
            rules.add(ruleService.findRuleById(Integer.parseInt(stringId)).get());
        }
        accident.setRules(rules);
        accidentService.update(accident);
        return "redirect:/index";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Accident accident) {
        accidentService.delete(accident);
        return "redirect:/index";
    }

}
