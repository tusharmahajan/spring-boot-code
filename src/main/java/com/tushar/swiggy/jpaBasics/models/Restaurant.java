package com.tushar.swiggy.jpaBasics.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Restaurant")
@NoArgsConstructor
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    public String name;

    @Column(name = "pincode")
    public int pincode;

    @Column(name = "rating")
    public int rating;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
//    public List<FoodItem> foodItems;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "restaurant_food_item",
            joinColumns = @JoinColumn(name = "restaurant_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_item_id" , referencedColumnName = "id")
    )
    public List<FoodItem> foodItems;


    public Restaurant(String name, int pincode, int rating, List<FoodItem> foodItems) {
        this.name = name;
        this.pincode = pincode;
        this.rating = rating;
        this.foodItems = foodItems;
    }
}
