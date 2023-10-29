package com.tushar.swiggy.jpaBasics.persistWithRepository;

import com.tushar.swiggy.jpaBasics.models.Address;
import com.tushar.swiggy.jpaBasics.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component
public class PersonCrudDemo implements ApplicationRunner {

    @Autowired
    PersonCrudRepository personCrudRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Person person = new Person();
        Address address = new Address("garha", "jal", 144021);
        person.setAddress(address);
        person.setGrade(12);
        person.setName("Rahul");
        personCrudRepository.save(person);

    }
}
