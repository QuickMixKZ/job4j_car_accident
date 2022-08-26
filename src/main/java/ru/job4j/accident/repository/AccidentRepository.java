package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @EntityGraph(
            attributePaths = {"rules", "type"})
    Optional<Accident> findById(Integer integer);

    @Override
    @EntityGraph(
            attributePaths = {"rules", "type"})
    Iterable<Accident> findAll();
}
