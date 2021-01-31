package ru.netology.hiberdao.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.netology.hiberdao.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

  @Query("select p from persons p where p.cityOfLiving = :cityOfLiving")
  List<Person> findAllByCityOfLiving(@Param("cityOfLiving") String cityOfLiving);

  @Query("select p from persons p where p.age < :age")
  List<Person> findAllByAgeLess(@Param("age") int age, Sort sort);

  @Query("select p from persons p where p.name = :name and p.surname = :surname")
  Optional<List<Person>> findAllByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
