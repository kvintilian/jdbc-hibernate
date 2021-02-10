package ru.netology.hiberdao.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hiberdao.entity.Person;
import ru.netology.hiberdao.repository.PersonRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pers")
public class PersonSecController {

  private final PersonRepository personRepository;

  public PersonSecController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping("/by-city")
  @Secured("ROLE_READ")
  public List<Person> getPersonsByCity(@RequestParam("city") String city) {
    return personRepository.findAllByCityOfLiving(city);
  }

  @GetMapping("/by-age-less")
  @RolesAllowed("ROLE_WRITE")
  public List<Person> getPersonsByAgeAsLower(@RequestParam("age") int age) {
    return personRepository.findAllByAgeLessThanOrderByAgeAsc(age);
  }

  @GetMapping("/by-id")
  @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
  public Optional<Person> findById(Long id) {
    return personRepository.findById(id);
  }

  @GetMapping("/count")
  @PreAuthorize("#username == authentication.principal.username")
  public String count(@RequestParam("username") String username) {
    return String.format("Hello, %s! Persons count = %d", username, personRepository.count());
  }
}
