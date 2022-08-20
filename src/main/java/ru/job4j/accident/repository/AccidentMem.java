package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(0,
                new Accident("Нарушение правил парковки",
                        "Машина припаркована на газоне",
                        "ул. Прохорова 24"));
        accidents.put(1,
                new Accident("Нарушение правил проезда перекрёстков",
                        "Проезд на запрещающий сигнал светофора",
                        "пересечение улиц Пушкина и Лермонтова"));
        accidents.put(2,
                new Accident("Выезд на встречную",
                        "Пересечение двойной спшлоной линии разметки с выездом на встречную полосу",
                        "пр. Победы"));
    }

    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

}
