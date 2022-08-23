package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private AtomicInteger id = new AtomicInteger();

    public AccidentMem() {
        fillAccidentTypes();
        fillAccidents();
    }

    private void fillAccidentTypes() {
        accidentTypes.put(1, AccidentType.of(1, "Одна машина"));
        accidentTypes.put(2, AccidentType.of(2, "Две машины"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и человек"));
        accidentTypes.put(4, AccidentType.of(4, "Машина и велосипед"));
    }

    private void fillAccidents() {
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Нарушение правил парковки",
                        "Машина припаркована на газоне",
                        "ул. Прохорова 24", accidentTypes.get(1)));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Нарушение правил проезда перекрёстков",
                        "Проезд на запрещающий сигнал светофора",
                        "пересечение улиц Пушкина и Лермонтова", accidentTypes.get(1)));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Выезд на встречную",
                        "Пересечение двойной спшлоной линии разметки с выездом на встречную полосу",
                        "пр. Победы", accidentTypes.get(1)));
    }

    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public List<AccidentType> getAccidentsType() {
        return new ArrayList<>(accidentTypes.values());
    }

    public void create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

}
