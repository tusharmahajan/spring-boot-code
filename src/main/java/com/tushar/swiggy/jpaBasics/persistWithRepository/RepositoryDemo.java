package com.tushar.swiggy.jpaBasics.persistWithRepository;

import com.tushar.swiggy.jpaBasics.models.FoodItem;
import com.tushar.swiggy.jpaBasics.models.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RepositoryDemo implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RestaurantCrudRepository restaurantCrudRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Session session = sessionFactory.openSession();
//        oneToManyMappingEg();
//        manyToManyMappingEg();

//        RestaurantRepository restaurantRepository = new RestaurantRepository(session);
//        Restaurant restaurant = new Restaurant();
//        restaurant.setCuisine("german");
//        restaurant.setName("croissant");
//        restaurant.setRating(5);
//        restaurantCrudRepository.save(restaurant);
//        restaurantCrudRepository.save(restaurant);

//        restaurantRepository.save(restaurant);
//        session.close();
    }

//    private void oneToManyMappingEg() {
//        Restaurant restaurant = new Restaurant();
//        FoodItem dal = new FoodItem();
//        dal.setCuisine("indian");
//        dal.setName("dal makhani");
//        dal.setRestaurant(restaurant);
//
//        FoodItem pizza = new FoodItem();
//        pizza.setName("margherita");
//        pizza.setCuisine("italian");
//        pizza.setRestaurant(restaurant);
//
//        restaurant.setName("dhabha");
//        restaurant.setRating(5);
//        restaurant.setFoodItems(Arrays.asList(dal, pizza));
//
//        restaurantCrudRepository.save(restaurant);
//    }


    private void manyToManyMappingEg() {
        FoodItem pizza = new FoodItem("Margherita", "italian");
        FoodItem burger = new FoodItem("mcaloo", "american");

        Restaurant restaurant = new Restaurant("reefer", 144003, 5, Arrays.asList(pizza, burger));
        restaurantCrudRepository.save(restaurant);
    }

}
