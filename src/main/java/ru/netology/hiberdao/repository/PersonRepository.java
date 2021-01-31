package ru.netology.hiberdao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.hiberdao.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findAllByCityOfLiving(String cityOfLiving);

  List<Person> findAllByAgeLessThanOrderByAgeAsc(int age);

  Optional<List<Person>> findAllByNameAndSurname(String name, String surname);
}
