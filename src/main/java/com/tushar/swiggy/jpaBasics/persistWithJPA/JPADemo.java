package com.tushar.swiggy.jpaBasics.persistWithJPA;

import com.tushar.swiggy.jpaBasics.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class JPADemo {

    public static void main(String[] args) {
        persistWithJPA();
    }
    public static void persistWithJPA(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

        // Persistence Context
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Starting transaction");
        entityManager.getTransaction().begin();

        Restaurant restaurant = new Restaurant(); // new state is also called transient state
        restaurant.setName("Sagar Ratna 1");
        restaurant.setRating(4);

        entityManager.persist(restaurant); // managed state
        entityManager.detach(restaurant);

        restaurant.setCuisine("Italian");

        entityManager.getTransaction().commit();
        System.out.println("Restaurant ID: " + restaurant.getId());

        Restaurant res = entityManager.find(Restaurant.class, restaurant.getId());
//        System.out.println("Fetched restaurant id: " + res.getId());
        @SuppressWarnings(value = "unchecked")
        List<Restaurant> restaurantList = entityManager.createQuery("Select r from Restaurant r").getResultList();

        for(Restaurant entity : restaurantList){
            System.out.println(entity.getId());
        }
        entityManager.getTransaction().begin();
        entityManager.remove(res);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
