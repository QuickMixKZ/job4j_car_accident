package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentTypeService {

    private final AccidentTypeRepository store;

    public AccidentTypeService(AccidentTypeRepository store) {
        this.store = store;
    }

    public Optional<AccidentType> findAccidentTypeById(int id) {
        return store.findById(id);
    }

    public List<AccidentType> getAccidentsType() {
        return (List<AccidentType>) store.findAll();
    }
}
