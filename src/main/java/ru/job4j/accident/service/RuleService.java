package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {

    private final RuleRepository store;

    public RuleService(RuleRepository store) {
        this.store = store;
    }

    public List<Rule> getRules() {
        return (List<Rule>) store.findAll();
    }

    public Optional<Rule> findRuleById(int id) {
        return store.findById(id);
    }
}
