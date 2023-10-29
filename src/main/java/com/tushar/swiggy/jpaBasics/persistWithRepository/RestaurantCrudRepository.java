package com.tushar.swiggy.jpaBasics.persistWithRepository;

import com.tushar.swiggy.jpaBasics.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantCrudRepository extends CrudRepository<Restaurant, Integer> {
}
