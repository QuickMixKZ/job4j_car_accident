package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public List<Accident> getAccidents() {
        return store.getAccidents();
    }

    public void add(Accident accident) {
        store.add(accident);
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public Accident findAccidentById(int id) {
        return store.findAccidentById(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return store.findAccidentTypeById(id);
    }

    public List<AccidentType> getAccidentsType() {
        return store.getAccidentsType();
    }

    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    public List<Rule> getRules() {
        return store.getRules();
    }
}
