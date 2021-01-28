package ru.netology.hiberdao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.hiberdao.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class CommandLineApp implements CommandLineRunner {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    var names = List.of("Петя", "Иван", "Саша", "Петр", "Инокентий");
    var surnames = List.of("Иванов", "Петров", "Сидоров", "Сковородкин", "Рыжий");
    var cities = List.of("Москва", "Королев", "Мытищи");

    var random = new Random();
    IntStream.range(0, 50)
            .forEach(i -> {
              var person = Person.builder()
                      .name(names.get(random.nextInt(names.size())))
                      .surname(surnames.get(random.nextInt(surnames.size())))
                      .age(random.nextInt(30))
                      .phoneNumber(Integer.toString(random.nextInt(1000)))
                      .cityOfLiving(cities.get(random.nextInt(cities.size())))
                      .build();
              entityManager.persist(person);
            });
  }
}
