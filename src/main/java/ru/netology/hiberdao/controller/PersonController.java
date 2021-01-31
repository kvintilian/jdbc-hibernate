package ru.netology.hiberdao.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.netology.hiberdao.entity.Person;
import ru.netology.hiberdao.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonRepository personRepository;

  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping("/by-city")
  public List<Person> getPersonsByCity(@RequestParam("city") String city) {
    return personRepository.findAllByCityOfLiving(city);
  }

  @GetMapping("/by-age-less")
  public List<Person> getPersonsByAgeAsLower(@RequestParam("age") int age) {
    return personRepository.findAllByAgeLess(age, Sort.by("age"));
  }

  @GetMapping("/by-fullname")
  public Optional<List<Person>> getPersonsByFullName(@RequestParam("name") String name, @RequestParam("surname") String surname) {
    return personRepository.findAllByNameAndSurname(name, surname);
  }

  @PostMapping("/save")
  public Person save(@RequestBody Person person) {
    return personRepository.save(person);
  }

  @PostMapping("/save-all")
  public List<Person> saveAll(@RequestBody List<Person> persons) {
    return personRepository.saveAll(persons);
  }

  @GetMapping("/by-id")
  public Optional<Person> findById(Long id) {
    return personRepository.findById(id);
  }

  @GetMapping("/exist-by-id")
  public boolean existsById(Long id) {
    return personRepository.existsById(id);
  }

  @GetMapping("/find-all")
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @GetMapping("/find-all-by-id-list")
  public List<Person> findAllById(List<Long> idList) {
    return personRepository.findAllById(idList);
  }

  @GetMapping("/count")
  public Long count() {
    return personRepository.count();
  }

  @PostMapping("/delete-by-id")
  public void deleteById(Long id) {
    personRepository.deleteById(id);
  }

  @PostMapping("/deleye-by-person")
  public void setDeletePerson(@RequestBody Person person) {
    personRepository.delete(person);
  }

  @PostMapping("/delete-list")
  public void deleteAllPersons(List<Person> personList) {
    personRepository.deleteAll(personList);
  }

  @PostMapping("/delete-all")
  public void deleteAll() {
    personRepository.deleteAll();
  }
}
