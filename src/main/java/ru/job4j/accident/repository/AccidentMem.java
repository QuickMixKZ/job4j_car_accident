package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger id = new AtomicInteger();

    public AccidentMem() {
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Нарушение правил парковки",
                        "Машина припаркована на газоне",
                        "ул. Прохорова 24"));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Нарушение правил проезда перекрёстков",
                        "Проезд на запрещающий сигнал светофора",
                        "пересечение улиц Пушкина и Лермонтова"));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Выезд на встречную",
                        "Пересечение двойной спшлоной линии разметки с выездом на встречную полосу",
                        "пр. Победы"));
    }

    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public void create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

}
