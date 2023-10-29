package com.tushar.swiggy.jpaBasics.persistWithRepository;

import com.tushar.swiggy.jpaBasics.models.Restaurant;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RestaurantRepository {

    private final Session session;

    public RestaurantRepository(Session session){
        this.session = session;
    }

    public Optional<Restaurant> save(Restaurant restaurant){

        try {
            session.getTransaction().begin();
            if(Objects.isNull(restaurant.getId())) session.persist(restaurant);
            else session.merge(restaurant);
            session.getTransaction().commit();
            return Optional.of(restaurant);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Restaurant> find(Integer id){
        Restaurant restaurant = session.find(Restaurant.class , id);
        return  restaurant != null ? Optional.of(restaurant) : Optional.empty();
    }

    public List<Restaurant> findAll(){
        return session.createQuery("Select r from Restaurants r").getResultList();
    }
}
