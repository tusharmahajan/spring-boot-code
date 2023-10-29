package com.tushar.swiggy.jpaBasics.persistWithRepository;

import com.tushar.swiggy.jpaBasics.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCrudRepository extends CrudRepository<Person, Integer> {
}
