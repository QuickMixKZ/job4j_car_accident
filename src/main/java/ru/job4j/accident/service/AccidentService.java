package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentRepository store;

    public AccidentService(AccidentRepository store) {
        this.store = store;
    }

    public List<Accident> getAccidents() {
        return (List<Accident>) store.findAll();
    }

    public void add(Accident accident) {
        store.save(accident);
    }

    public void update(Accident accident) {
        store.save(accident);
    }

    public Optional<Accident> findAccidentById(int id) {
        return store.findById(id);
    }

    public void delete(Accident accident) {
        store.delete(accident);
    }

}
