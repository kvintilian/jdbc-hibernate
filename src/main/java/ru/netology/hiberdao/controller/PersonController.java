package ru.netology.hiberdao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hiberdao.entity.Person;
import ru.netology.hiberdao.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonRepository personRepository;

  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping("/by-city")
  public List<Person> getPersonsByCity(@RequestParam("city") String city) {
    return personRepository.getPersonsByCity(city);
  }
}
