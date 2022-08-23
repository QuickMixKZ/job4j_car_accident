package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public List<Accident> getAccidents() {
        return store.getAccidents();
    }

    public void create(Accident accident) {
        store.create(accident);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }
}
