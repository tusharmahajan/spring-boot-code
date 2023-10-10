package com.tushar.swiggy.jpaBasics.persistWithHibernate;


import com.tushar.swiggy.jpaBasics.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class HibernateDemo implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        persistWithHibernate();
    }

    private void persistWithHibernate() {

        Session session = sessionFactory.openSession();

        System.out.println("Beginning hibernate transaction");
        session.getTransaction().begin();

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Pizza factory");
        restaurant.setCuisine("Italian");

        session.persist(restaurant);
        session.getTransaction().commit();

        session.getTransaction().begin();
        System.out.println(restaurant.getId());
        Restaurant restaurant1 = session.find(Restaurant.class, restaurant.getId());
        session.remove(restaurant1);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }

}
