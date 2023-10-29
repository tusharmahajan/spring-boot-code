package com.tushar.swiggy.jpaBasics.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "food_item")
@NoArgsConstructor
public class FoodItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "cuisine")
    public String cuisine;

//    @ManyToOne
//    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
//    public Restaurant restaurant;

    @ManyToMany(mappedBy = "foodItems")
    public List<Restaurant> restaurant;

    public FoodItem(String name, String cuisine) {
        this.name = name;
        this.cuisine = cuisine;
    }
}
