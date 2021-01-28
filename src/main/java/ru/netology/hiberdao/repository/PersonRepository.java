package ru.netology.hiberdao.repository;

import org.springframework.stereotype.Repository;
import ru.netology.hiberdao.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;

@Repository
public class PersonRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Person> getPersonsByCity(String city) {
    return entityManager.createQuery(
            "SELECT p FROM Person p WHERE upper(p.cityOfLiving) = :city", Person.class)
            .setParameter("city", city.toUpperCase(Locale.ROOT))
            .getResultList();
  }
}
